package com.madahaz.sleepingserverstoppermod.config;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class SleepingServerStopperModConfig {
    private static final Logger LOGGER = LogManager.getLogger("SleepingServerStopperMod");
    
    private static Configuration config;
    
    // Config values
    public static int SHUTDOWN_TIME_IN_MINUTES = 2;
    public static boolean SHUTDOWN_SERVER_ON_LAUNCH = true;
    
    public static void init(File configFile) {
        config = new Configuration(configFile);
        loadConfig();
    }
    
    public static void loadConfig() {
        try {
            config.load();
            
            SHUTDOWN_TIME_IN_MINUTES = config.getInt(
                "shutdownTimeInMinutes", 
                "general", 
                2, 
                1, 
                60, 
                "How many minutes until the server closes. (Minimum is 1 minute!)"
            );
            
            SHUTDOWN_SERVER_ON_LAUNCH = config.getBoolean(
                "shutdownOnLaunch", 
                "general", 
                true, 
                "Should the server shutdown on launch?"
            );
            
            LOGGER.info("[SSS] Configuration loaded - Shutdown time: " + SHUTDOWN_TIME_IN_MINUTES + 
                       " minutes, Shutdown on launch: " + SHUTDOWN_SERVER_ON_LAUNCH);
            
        } catch (Exception e) {
            LOGGER.error("[SSS] Problem loading config file!", e);
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
    
    public static void saveConfig() {
        if (config != null) {
            config.save();
        }
    }
}
