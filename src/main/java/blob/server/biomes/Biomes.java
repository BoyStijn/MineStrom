package blob.server.biomes;

import blob.server.utils.DataList;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.server.world.biomes.BiomeEffects;
import net.minestom.server.world.biomes.BiomeManager;

public class Biomes {

    private final BiomeManager bm;

    public Biomes(BiomeManager bm) {
        bm.removeBiome(Biome.PLAINS);
        Biome.ID_COUNTER.set(0);
        this.bm = bm;
    }

    public void registerBiomes() {
        register("THE_VOID", OverworldBiomes.theVoid());
        register("PLAINS", OverworldBiomes.plains(false));
        register("SUNFLOWER_PLAINS", OverworldBiomes.plains(false));
        register("SNOWY_PLAINS", OverworldBiomes.plains(true));
        register("ICE_SPIKES", OverworldBiomes.plains(true));
        register("DESERT", OverworldBiomes.desert());
        register("WETLAND", OverworldBiomes.swamp());
        register("FOREST", OverworldBiomes.forest(false));
        register("FLOWER_FOREST", OverworldBiomes.forest(false));
        register("BIRCH_FOREST", OverworldBiomes.forest(true));
        register("OLD_GROWTH_BIRCH_FOREST", OverworldBiomes.forest(true));
        register("FROZEN_PEAKS", OverworldBiomes.frozenPeaks());
        register("JAGGED_PEAKS", OverworldBiomes.jaggedPeaks());
        register("STONY_PEAKS", OverworldBiomes.stonyPeaks());
        register("WARM_OCEAN", OverworldBiomes.warmOcean());
        register("LUKEWARM_OCEAN", OverworldBiomes.lukeWarmOcean());
        register("DEEP_LUKEWARM_OCEAN", OverworldBiomes.lukeWarmOcean());
        register("OCEAN", OverworldBiomes.ocean());
        register("DEEP_OCEAN", OverworldBiomes.ocean());
        register("COLD_OCEAN", OverworldBiomes.coldOcean());
        register("DEEP_COLD_OCEAN", OverworldBiomes.coldOcean());
        register("FROZEN_OCEAN", OverworldBiomes.frozenOcean(false));
        register("DEEP_FROZEN_OCEAN", OverworldBiomes.frozenOcean(true));
    }

    public void register(String key, Biome.Builder bb) {
        Biome b = bb.name(NamespaceID.from("minecraft", key.toLowerCase())).build();
        this.bm.addBiome(b);
        DataList.BIOME_LISTS.putData(b.name().asString(), b);
    }

}
