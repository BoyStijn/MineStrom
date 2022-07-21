package blob.server.biomes;

import blob.server.utils.MathUtils;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;
import net.minestom.server.world.biomes.BiomeEffects;

public class OverworldBiomes {

    protected static final int NORMAL_WATER_COLOR = 4159204;

    protected static final int NORMAL_WATER_FOG_COLOR = 329011;

    private static final int OVERWORLD_FOG_COLOR = 12638463;

    private static final BiomeEffects.Music NORMAL_MUSIC = null;

    protected static int calculateSkyColor(float $$0) {
        float $$1 = $$0;
        $$1 /= 3.0F;
        $$1 = MathUtils.clamp($$1, -1.0F, 1.0F);
        return MathUtils.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static Biome.Builder biome(Biome.Precipitation $$0, Biome.Category $$1, float $$2, float $$3, BiomeEffects.Music $$6) {
        return biome($$0, $$1, $$2, $$3, NORMAL_WATER_COLOR, NORMAL_WATER_FOG_COLOR, $$6);
    }

    private static Biome.Builder biome(Biome.Precipitation $$0, Biome.Category $$1, float $$2, float $$3, int $$4, int $$5, BiomeEffects.Music $$8) {
        return Biome.builder()
                .precipitation($$0)
                .category($$1)
                .temperature($$2)
                .downfall($$3)
                .effects(buildEffects($$2,$$4,$$5,$$8));
    }

    private static BiomeEffects buildEffects(float $$2, int $$4, int $$5, BiomeEffects.Music $$8) {
        BiomeEffects.Builder builder = BiomeEffects.builder()
                .waterColor($$4)
                .waterFogColor($$5)
                .fogColor(OVERWORLD_FOG_COLOR)
                .skyColor(calculateSkyColor($$2))
                .moodSound(new BiomeEffects.MoodSound(NamespaceID.from("ambient.cave"), 6000, 8, 2.0D));
        if ($$8 != null) builder.music($$8);
        return builder.build();
    }

    public static Biome.Builder plains(boolean $$1) {
        float $$5 = $$1 ? 0.0F : 0.8F;
        return biome($$1 ? Biome.Precipitation.SNOW : Biome.Precipitation.RAIN, $$1 ? Biome.Category.ICY : Biome.Category.PLAINS, $$5, $$1 ? 0.5F : 0.4F, NORMAL_MUSIC);
    }

    public static Biome.Builder theVoid() {
        return biome(Biome.Precipitation.NONE, Biome.Category.NONE, 0.5F, 0.5F, NORMAL_MUSIC);
    }

    public static Biome.Builder frozenPeaks() {
        BiomeEffects.Music $$2 = new BiomeEffects.Music(NamespaceID.from("minecraft","music.overworld.frozen_peaks"),12000, 24000, false);
        return biome(Biome.Precipitation.SNOW, Biome.Category.EXTREME_HILLS, -0.7F, 0.9F, $$2);
    }

    public static Biome.Builder jaggedPeaks() {
        BiomeEffects.Music $$2 = new BiomeEffects.Music(NamespaceID.from("minecraft","music.overworld.jagged_peaks"),12000, 24000, false);
        return biome(Biome.Precipitation.SNOW, Biome.Category.EXTREME_HILLS, -0.7F, 0.9F, $$2);
    }

    public static Biome.Builder stonyPeaks() {
        BiomeEffects.Music $$2 = new BiomeEffects.Music(NamespaceID.from("minecraft","music.overworld.stony_peaks"),12000, 24000, false);
        return biome(Biome.Precipitation.RAIN, Biome.Category.EXTREME_HILLS, 1.0F, 0.3F, $$2);
    }

    private static Biome.Builder baseOcean(int $$1, int $$2) {
        return biome(Biome.Precipitation.RAIN, Biome.Category.OCEAN, 0.5F, 0.5F, $$1, $$2, NORMAL_MUSIC);
    }

    public static Biome.Builder ocean() {
        return baseOcean(4159204, 329011);
    }

    public static Biome.Builder desert() {
        return biome(Biome.Precipitation.NONE, Biome.Category.DESERT, 2.0F, 0.0F, NORMAL_MUSIC);
    }

    public static Biome.Builder swamp() {
        return Biome.builder()
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.SWAMP)
                .temperature(0.8F)
                .downfall(0.9F)
                .effects(BiomeEffects.builder()
                        .waterColor(6388580)
                        .waterFogColor(2302743)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor(0.8F))
                        .foliageColor(6975545)
                        .grassColorModifier(BiomeEffects.GrassColorModifier.SWAMP)
                        .moodSound(new BiomeEffects.MoodSound(NamespaceID.from("ambient.cave"), 6000, 8, 2.0D))
                        .build());
    }

    public static Biome.Builder forest(boolean $$0) {
        float $$5 = $$0 ? 0.6F : 0.7F;
        return biome(Biome.Precipitation.RAIN, Biome.Category.FOREST, $$5, $$0 ? 0.6F : 0.8F, NORMAL_MUSIC);
    }

    public static Biome.Builder coldOcean() {
        return baseOcean(4020182, 329011);
    }

    public static Biome.Builder lukeWarmOcean() {
        return baseOcean(4566514, 267827);
    }

    public static Biome.Builder warmOcean() {
        return baseOcean(4445678, 270131);
    }

    public static Biome.Builder frozenOcean(boolean $$0) {
        float $$2 = $$0 ? 0.5F : 0.0F;
        return Biome.builder()
                .precipitation($$0 ? Biome.Precipitation.RAIN : Biome.Precipitation.SNOW)
                .category(Biome.Category.OCEAN)
                .temperature($$2)
                .temperatureModifier(Biome.TemperatureModifier.FROZEN)
                .downfall(0.5F)
                .effects(BiomeEffects.builder()
                        .waterColor(3750089)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(calculateSkyColor($$2))
                        .moodSound(new BiomeEffects.MoodSound(NamespaceID.from("ambient.cave"), 6000, 8, 2.0D))
                        .build());
    }

}
