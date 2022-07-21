package blob.server.gen.fillers;

import blob.server.biomes.BiomeColumn;
import blob.server.biomes.BiomeFinder;
import blob.server.biomes.config.OceanConfig;
import blob.server.utils.DataList;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.biomes.Biome;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class DebugFiller implements Filler {
    @Override
    public Consumer<Block.Setter> Fill(Point start, Random rand, FillerData data, BiomeColumn column) {

        int dirt = rand.nextInt(3);
        int sand = rand.nextInt(2);
        int deepslate = rand.nextInt(5);
        int Snow = rand.nextInt(5);
        double roughoff = rand.nextDouble(0.3);
        double seaoff = rand.nextDouble(0.15);
        double desoff = rand.nextDouble(0.020);

        return (setter -> {

            for (int y = -64; y <= start.blockY(); y++) {
                Biome biome = column.getBiome(y);
                Block b = Block.STONE;
                if (y > start.blockY() - 4 - dirt && data.getRoughness() > 0.5 - roughoff && rand.nextBoolean()) b = Block.GRAVEL;
                if (y == start.blockY() && biome.equals(BiomeFinder.get("JAGGED_PEAKS"))) b = Block.SNOW_BLOCK;
                if (y > start.blockY() - 4 - dirt && biome.equals(BiomeFinder.get("FROZEN_PEAKS"))) b = Block.SNOW_BLOCK;
                if ((y < 100 + Snow || (rand.nextBoolean() && y < 135 + deepslate && data.getTemperature() <= 0.75 - desoff)) && data.getTemperature() >= 0.2 - desoff) {
                    if (y > start.blockY() - 4 - dirt) b = Block.DIRT;
                    if (y == start.blockY()) b = Block.GRASS_BLOCK;
                }
                if (biome.equals(BiomeFinder.get("DESERT"))) {
                    if (y > start.blockY() - 15 - dirt) b = Block.SANDSTONE;
                    if (y >= start.blockY() - 4 - dirt && !(rand.nextBoolean() && y > 100 + Snow)) b = Block.SAND;
                }
                if (start.blockY() < 66 && y > start.blockY() - 2 - sand) {
                    b = Block.SAND;
                    if ((BiomeFinder.getConf(biome) instanceof OceanConfig)) {
                        OceanConfig config = (OceanConfig) BiomeFinder.getConf(biome);
                        if (config.hasGravel()) b = Block.GRAVEL;
                    }
                }
                if (y < 0 + deepslate) b = Block.DEEPSLATE;
                if (y < -60 && rand.nextBoolean()) b = Block.BEDROCK;
                if(y == -64) b = Block.BEDROCK;

                setter.setBlock(start.blockX(), y, start.blockZ(), b);
            }
            if (start.blockY() < 64) {
                for (int y = start.blockY() + 1; y <= 64; y++) {
                    setter.setBlock(start.blockX(), y, start.blockZ(), Block.WATER);
                }
            }
        });
    }
}
