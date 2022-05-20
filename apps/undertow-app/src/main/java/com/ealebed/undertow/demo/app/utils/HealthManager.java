package com.ealebed.undertow.demo.app.utils;

public interface HealthManager {
    void resetAll();

    void notifyReady();

    void notifyNotReady();

    void notifyLive();

    void notifyDead();

    static HealthManager file() {
        return new FileHealthManager();
    }
}
