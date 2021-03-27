package de.telran.spring.service;

public interface IServerMap {
    void update(ServerData serverData);

    ServerData getBest();

    void removeUnused(int millis);
}
