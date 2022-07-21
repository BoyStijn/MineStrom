package blob.server.biomes;

import net.minestom.server.instance.generator.UnitModifier;
import net.minestom.server.world.biomes.Biome;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class BiomeColumn {

    private final int minHeight;
    private final int maxHeight;
    private final ConcurrentHashMap<Integer, Biome> biomes = new ConcurrentHashMap();

    public BiomeColumn(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight-1;
    }

    public void setBiome(int y, Biome b) {
        if (y < this.minHeight || y > this.maxHeight) return;
        biomes.put(y,b);
    }

    public Biome getBiome(int y) {
        if (y < this.minHeight || y > this.maxHeight || !biomes.containsKey(y)) return BiomeFinder.get("the_void");
        return biomes.get(y);
    }

    public void fillBiome(Biome b) {
        for (int y = this.minHeight; y <= this.maxHeight; y++) {
            biomes.put(y,b);
        }
    }

    public void Apply(UnitModifier unit, int x, int z) {
        for (Entry<Integer, Biome> e : this.biomes.entrySet()) {
            unit.setBiome(x, e.getKey(), z, e.getValue());
        }
    }

}
