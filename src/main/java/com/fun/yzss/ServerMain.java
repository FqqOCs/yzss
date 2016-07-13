package com.fun.yzss;

import com.fun.yzss.util.ShutdownHookManager;
import com.fun.yzss.util.SystemEnvHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by fanqq on 2016/7/13.
 */
public class ServerMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);
    private static String APPLICATION_NAME = "yzss";

    private static void setSystemProperties() {
        //Archaius loading configuration depends on this property.
        SystemEnvHandler.setPropertyDefaultValue("server.port", "8080");
        SystemEnvHandler.setPropertyDefaultValue("archaius.deployment.applicationId", APPLICATION_NAME);
        SystemEnvHandler.setPropertyDefaultValue("archaius.deployment.environment", "dev");

    }
    public static void main(String[] args) {
        //Set System Properties
        setSystemProperties();
        printStartupAndShutdownMsg(args);
    }

    private static void printStartupAndShutdownMsg(String[] args) {
        String host= "Unknown";
        try {
            host = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            LOGGER.warn("Can't get the local host.", e);
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement topStack = stackTrace[stackTrace.length - 1];

        final String hostName = host;
        final String className = topStack.getClassName();

        LOGGER.info("STARTUP_MSG:\n" +
                        "*******************************************\n" +
                        "\tStarting : {}\n" +
                        "\tHost : {}\n" +
                        "\tArgs : {}\n" +
                        "*******************************************",
                className, hostName, Arrays.toString(args));

        ShutdownHookManager.get().addShutdownHook(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("SHUTDOWN_MSG:\n" +
                                "*******************************************\n" +
                                "\tShutting down : {}\n" +
                                "\tHost : {}\n" +
                                "*******************************************",
                        className, hostName);

            }
        }, 1);
    }
}
