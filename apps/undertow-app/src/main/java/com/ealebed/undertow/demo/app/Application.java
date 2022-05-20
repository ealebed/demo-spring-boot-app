package com.ealebed.undertow.demo.app;

import com.ealebed.undertow.demo.app.utils.FileHealthManager;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.undertow.Undertow;
import io.undertow.util.Headers;

public class Application {
    public static final Config CONFIG = ConfigFactory.load().getConfig("server");

    public static void main(String[] args) {
        new FileHealthManager().notifyLive();

        Undertow server = Undertow.builder()
                .addHttpListener(CONFIG.getInt("port"), CONFIG.getString("host"))
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Greetings to SoftServians from Undertow and Jib!");
                }).build();

        server.start();

        new FileHealthManager().notifyReady();
    }
}
