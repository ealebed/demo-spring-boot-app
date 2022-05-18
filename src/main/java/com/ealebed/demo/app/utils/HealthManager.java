package com.ealebed.demo.app.utils;

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
