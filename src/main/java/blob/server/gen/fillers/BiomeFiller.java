package blob.server.gen.fillers;

import blob.server.biomes.BiomeColumn;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;

import java.util.Random;
import java.util.function.Consumer;

public interface BiomeFiller {

    Consumer<BiomeColumn> Fill(Point start, Random r, FillerData data);

}
