package de.telran.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class UdpGatewaySender implements Runnable{
    private final String host;
    private final IServerMap serverMap;
    private final int udpToGatewayPort;
    private final int periodMillis;


    public UdpGatewaySender(@Value("${de.telran.spring.resources.application.properties.gateway.host}")
                                    String host,
                            IServerMap serverMap,
                            @Value("${de.telran.spring.resources.application.properties.udp.balancer.port}")
                            int udpToGatewayPort,
                            @Value("${de.telran.spring.resources.application.properties.periodMillis}")
                            int periodMillis) {
        this.host = host;
        this.serverMap = serverMap;
        this.udpToGatewayPort = udpToGatewayPort;
        this.periodMillis = periodMillis;
    }

    @Override
    public void run() {
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            DatagramSocket udpSocket = new DatagramSocket();
            while (true) {
                try {
                    Thread.sleep(periodMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ServerData serverData = serverMap.getBest();
                if (serverData == null)
                    continue;

                String best = serverData.toString();

                System.out.println("sending best server to gateway: " + best + ":" + serverData.getLoad());

                byte[] sendBest = best.getBytes();
                DatagramPacket packetOut = new DatagramPacket(
                        sendBest,
                        sendBest.length,
                        inetAddress,
                        udpToGatewayPort
                );

                udpSocket.send(packetOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
