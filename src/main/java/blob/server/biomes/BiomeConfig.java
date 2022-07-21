package blob.server.biomes;

import blob.server.gen.fillers.FillerData;
import net.minestom.server.utils.math.DoubleRange;
import net.minestom.server.utils.math.IntRange;

public interface BiomeConfig {

    boolean isValid(FillerData d, int Height);

}
