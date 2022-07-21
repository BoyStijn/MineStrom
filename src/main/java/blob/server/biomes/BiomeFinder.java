package blob.server.biomes;

import blob.server.biomes.config.*;
import blob.server.gen.fillers.FillerData;
import blob.server.utils.DataList;
import net.minestom.server.world.biomes.Biome;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BiomeFinder {

    private static ConcurrentHashMap<Biome, BiomeConfig> CONFIG_BIOMES = new ConcurrentHashMap<>();

    public static void configBiomes() {
        config("PLAINS", new PlainsConfig(PlainsConfig.PlainsType.NORMAL));
        config("SUNFLOWER_PLAINS", new PlainsConfig(PlainsConfig.PlainsType.NORMAL));
        config("SNOWY_PLAINS", new PlainsConfig(PlainsConfig.PlainsType.COLD));
        config("ICE_SPIKES", new PlainsConfig(PlainsConfig.PlainsType.FROZEN));
        config("DESERT", new SimpleConfigs.Desert());
        config("WETLAND", new SimpleConfigs.Swamp());
        config("FOREST", new ForestConfig(ForestConfig.ForestType.WARM));
        config("FLOWER_FOREST", new ForestConfig(ForestConfig.ForestType.WARM));
        config("BIRCH_FOREST", new ForestConfig(ForestConfig.ForestType.COLD));
        config("OLD_GROWTH_BIRCH_FOREST", new ForestConfig(ForestConfig.ForestType.COLD));
        config("FROZEN_PEAKS", new PeakConfig(PeakConfig.PeakType.SNOW));
        config("JAGGED_PEAKS", new PeakConfig(PeakConfig.PeakType.JAGGED));
        config("STONY_PEAKS", new PeakConfig(PeakConfig.PeakType.STONE));
        config("WARM_OCEAN", new OceanConfig(false, OceanConfig.OceanType.WARM));
        config("LUKEWARM_OCEAN", new OceanConfig(false, OceanConfig.OceanType.LUKEWARM));
        config("DEEP_LUKEWARM_OCEAN", new OceanConfig(true, OceanConfig.OceanType.LUKEWARM));
        config("OCEAN", new OceanConfig(false, OceanConfig.OceanType.NORMAL));
        config("DEEP_OCEAN", new OceanConfig(true, OceanConfig.OceanType.NORMAL));
        config("COLD_OCEAN", new OceanConfig(false, OceanConfig.OceanType.COLD));
        config("DEEP_COLD_OCEAN", new OceanConfig(true, OceanConfig.OceanType.COLD));
        config("FROZEN_OCEAN", new OceanConfig(false, OceanConfig.OceanType.FROZEN));
        config("DEEP_FROZEN_OCEAN", new OceanConfig(true, OceanConfig.OceanType.FROZEN));
    }

    public static void config(String b, BiomeConfig bc) {
        if (b == null || bc == null) return;
        CONFIG_BIOMES.put(get(b),bc);
    }

    public static Biome find(FillerData d, int Height) {
        List<Map.Entry<Biome, BiomeConfig>> bl = CONFIG_BIOMES.entrySet().stream().filter(entry -> entry.getValue().isValid(d, Height)).toList();
        if (bl.isEmpty()) return get("the_void");
        int val = Math.round(d.getBiomeVal() * (bl.size()-1));
        return bl.get(val).getKey();
    }

    public static BiomeConfig getConf(Biome b) {
        return CONFIG_BIOMES.getOrDefault(b, null);
    }

    public static Biome get(String key) {
        Biome b = DataList.BIOME_LISTS.getData("minecraft:"+key.toLowerCase());
        if (b == null) return DataList.BIOME_LISTS.getData("minecraft:the_void");
        return b;
    }

}
