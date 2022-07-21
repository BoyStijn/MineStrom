package blob.server;

import blob.server.biomes.BiomeFinder;
import blob.server.biomes.Biomes;
import blob.server.commands.GamemodeCommand;
import blob.server.commands.TeleportCommand;
import blob.server.commands.TimeCommand;
import blob.server.commands.WorldGenCommand;
import blob.server.gen.WorldGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.world.DimensionType;
import net.minestom.server.world.biomes.Biome;

import java.util.Collection;

public class Main {

    private static int SpawnHeight = 0;

    public static void main(String[] args) {
        // Initialize the server
        MinecraftServer minecraftServer = MinecraftServer.init();
        //bootstrap
        Bootstrap.bootStrap();

        //minecraftServer.setChunkViewDistance(32);

        // Get World manager
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer world = startWorld(instanceManager);

        minecraftServer.getCommandManager().register(new WorldGenCommand());
        minecraftServer.getCommandManager().register(new GamemodeCommand());
        minecraftServer.getCommandManager().register(new TeleportCommand());
        minecraftServer.getCommandManager().register(new TimeCommand());

        new Biomes(MinecraftServer.getBiomeManager()).registerBiomes();
        BiomeFinder.configBiomes();

        Collection<Biome> biomes = MinecraftServer.getBiomeManager().unmodifiableCollection();
        for (Biome b : biomes) {
            System.out.println("Biome: " + b.name().asString() + ", id: " + b.id());
        }

        // REGISTER EVENTS (set spawn instance, teleport player at spawn)
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(world);
            player.setRespawnPoint(new Pos(0, SpawnHeight, 0));
        }).addListener(PlayerSpawnEvent.class, event -> {
            final Player player = event.getPlayer();
            player.setGameMode(GameMode.CREATIVE);
            player.setPermissionLevel(4);
        });

        // Start the server
        OptifineSupport.enable();
        MojangAuth.init();
        minecraftServer.start("0.0.0.0", 25565);
        Runtime.getRuntime().addShutdownHook(new Thread(MinecraftServer::stopCleanly));
    }
    private static InstanceContainer startWorld(InstanceManager instanceManager) {
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer(DimensionType.OVERWORLD);
        WorldGenerator gen = new WorldGenerator(System.currentTimeMillis());
        SpawnHeight = gen.getSpawnHeight() + 1;
        instanceContainer.setGenerator(gen);
        return instanceContainer;
    }



}
