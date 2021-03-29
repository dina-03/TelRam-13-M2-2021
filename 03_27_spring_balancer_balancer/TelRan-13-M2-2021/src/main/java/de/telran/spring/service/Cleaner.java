package de.telran.spring.service;

import org.springframework.stereotype.Service;

@Service
public class Cleaner implements Runnable {

    private final IServerMap serverMap;
    private int periodMillis = 1000;

    public Cleaner(IServerMap serverMap) {
        this.serverMap = serverMap;
        this.periodMillis = periodMillis;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(periodMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            serverMap.removeUnused(periodMillis);
        }
    }
}
