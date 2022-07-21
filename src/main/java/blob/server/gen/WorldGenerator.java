package blob.server.gen;

import blob.server.biomes.BiomeColumn;
import blob.server.gen.dimension.Dimension;
import blob.server.gen.dimension.builtin.Overworld;
import blob.server.gen.fillers.*;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class WorldGenerator implements Generator {

    private final Dimension dim;
    private final Random rand;
    private final Filler filler = new DebugFiller();
    private final BiomeFiller biomeFiller = new DebugBiomeFiller();

    public WorldGenerator(long Seed) {
        this.rand = new Random(Seed);
        this.dim = new Overworld(Seed);
    }

    public Dimension getDim() {
        return this.dim;
    }

    public int getSpawnHeight() {
        return (int) Math.floor(this.dim.getHeightAt(0, 0));
    }

    @Override
    public void generate(@NotNull GenerationUnit unit) {
        Point start = unit.absoluteStart();
        for (int x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
            for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                int absX = start.blockX() + x;
                int absZ = start.blockZ() + z;
                final int height = (int) Math.floor(this.dim.getHeightAt(absX, absZ));
                final float Roughness = this.dim.getRoughnessAt(absX, absZ);
                final float Seaness = this.dim.getSeanessAt(absX, absZ);
                final float temperature = this.dim.getTemperatureAt(absX, absZ);
                final float biomeval = this.dim.getBiomeValueAt(absX, absZ);
                Point pos = new Vec(absX, height, absZ);
                BiomeColumn biomeColumn = new BiomeColumn(unit.absoluteStart().blockY(), unit.absoluteEnd().blockY());
                biomeFiller.Fill(pos, new Random(this.rand.nextLong()), new FillerData(Roughness, Seaness, temperature, biomeval)).accept(biomeColumn);
                unit.fork(filler.Fill(pos, new Random(this.rand.nextLong()), new FillerData(Roughness, Seaness, temperature, biomeval), biomeColumn));
                biomeColumn.Apply(unit.modifier(), absX, absZ);
            }
        }
    }

}
