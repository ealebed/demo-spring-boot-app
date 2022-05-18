package com.ealebed.demo.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileHealthManager implements HealthManager {
    private static final Logger logger = LoggerFactory.getLogger(FileHealthManager.class);
    private static final String READINESS_FILE = "/tmp/ready";
    private static final String LIVENESS_FILE = "/tmp/live";

    @Override
    public void resetAll() {
        notifyNotReady();
        notifyDead();
    }

    @Override
    public void notifyReady() {
        try {
            File file = new File(READINESS_FILE);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException("fail to create readiness file");
                }
            }
        } catch (Exception e) {
            logger.error("[notifyNotReady]", e);
        }
    }

    @Override
    public void notifyNotReady() {
        try {
            File file = new File(READINESS_FILE);
            if (file.exists()) {
                if (!file.delete()) {
                    throw new RuntimeException("fail to delete readiness file");
                }
            }
        } catch (Exception e) {
            logger.error("[notifyNotReady]", e);
        }
    }

    @Override
    public void notifyLive() {
        try {
            File file = new File(LIVENESS_FILE);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException("fail to create liveness file");
                }
            }
        } catch (Exception e) {
            logger.error("[notifyNotReady]", e);
        }
    }

    @Override
    public void notifyDead() {
        try {
            File file = new File(LIVENESS_FILE);
            if (file.exists()) {
                if (!file.delete()) {
                    throw new RuntimeException("fail to delete liveness file");
                }
            }
        } catch (Exception e) {
            logger.error("[notifyNotReady]", e);
        }
    }
}
