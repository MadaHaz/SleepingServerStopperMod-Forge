package com.madahaz.sleepingserverstoppermod.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class SleepingServerStopperModConfig {
    private static Configuration config;
    
    public static int SHUTDOWN_TIME_IN_MINUTES = 2;
    public static boolean SHUTDOWN_SERVER_ON_LAUNCH = true;
    
    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfiguration();
        }
    }
    
    private static void loadConfiguration() {
        try {
            config.load();
            
            Property shutdownTimeProp = config.get(Configuration.CATEGORY_GENERAL, 
                "shutdown_time_in_minutes", 2, 
                "How many minutes until the server closes. (Minimum is 1 minute!)");
            shutdownTimeProp.setMinValue(1);
            SHUTDOWN_TIME_IN_MINUTES = shutdownTimeProp.getInt();
            
            Property shutdownOnLaunchProp = config.get(Configuration.CATEGORY_GENERAL, 
                "shutdown_server_on_launch", true, 
                "Should the server shutdown on launch?");
            SHUTDOWN_SERVER_ON_LAUNCH = shutdownOnLaunchProp.getBoolean();
            
        } catch (Exception e) {
            System.out.println("Problem loading config");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
