package net.madahaz.sleepingserverstoppermod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SleepingServerStopperModCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> SHUTDOWN_TIME_IN_MINUTES;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SHUTDOWN_SERVER_ON_LAUNCH;

    static {
        BUILDER.push("Configs for Sleeping Server Stopper Mod");

        SHUTDOWN_TIME_IN_MINUTES = BUILDER.comment("How many minutes until the server closes. (Minimum is 1 minute!)")
                .define("Minutes", 2);
        SHUTDOWN_SERVER_ON_LAUNCH = BUILDER.comment("Should the server shutdown on launch?")
                .define("ShutdownOnLaunch",true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
