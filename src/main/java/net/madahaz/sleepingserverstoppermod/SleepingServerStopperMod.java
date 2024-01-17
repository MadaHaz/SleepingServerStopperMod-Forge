package net.madahaz.sleepingserverstoppermod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.server.MinecraftServer;

@Mod(SleepingServerStopperMod.MODID)
public class SleepingServerStopperMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "sleepingserverstoppermod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int shutdownTime = 2;
    private static final boolean startServer = false;
    private static MinecraftServer server;
    private static Timer timer;

    public SleepingServerStopperMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Server started Event.
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        onServerStart(event.getServer());
    }

    // Server stopping Event.
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        timer.cancel();
    }

    // Player joining Event.
    @SubscribeEvent
    public void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event) {
        onPlayerJoin();
    }

    // Player leaving Event.
    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        countPlayers();
    }

    // METHODS

    public static void onServerStart(MinecraftServer server) {
        SleepingServerStopperMod.server = server;

        if (!startServer) {
            countPlayers();
        }
    }

    public static void countPlayers() {
        if (server.getPlayerCount() < 1) {
            LOGGER.info(String.format("[SSS] Server Empty - Server will shutdown in %d minute(s)!", shutdownTime));
            TimerTask task = new TimerTask() {
                public void run() {
                    stopper();
                }
            };
            timer = new Timer();
            timer.schedule(task, (60000L * shutdownTime));
        }
    }

    public static void onPlayerJoin() {
        if (server.getPlayerCount() <= 1 & timer != null) {
            LOGGER.info("[SSS] Player joined - Server shutdown cancelled.");
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    public static void stopper() {
        int playerCount = server.getPlayerCount();
        if (playerCount <= 0) {
            LOGGER.info("[SSS] Server empty - Server shutting down.");
            server.halt(true);
        } else {
            LOGGER.info(String.format("[SSS} Abort shutdown - %d connected player(s).", playerCount));
        }
    }
}
