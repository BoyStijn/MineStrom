package blob.server.gen.fillers;

import blob.server.biomes.BiomeColumn;
import blob.server.biomes.BiomeFinder;
import blob.server.utils.DataList;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.biomes.Biome;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class DebugBiomeFiller implements BiomeFiller {
    @Override
    public Consumer<BiomeColumn> Fill(Point start, Random rand, FillerData data) {

        int Heigth = start.blockY();

        return (setter -> {
                setter.fillBiome(BiomeFinder.find(data, Heigth));
        });
    }
}
