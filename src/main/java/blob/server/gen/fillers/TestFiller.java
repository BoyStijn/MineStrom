package blob.server.gen.fillers;

import blob.server.biomes.BiomeColumn;
import blob.server.gen.noise.HeightFunction;
import blob.server.gen.noise.functions.*;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;

import java.util.Random;
import java.util.function.Consumer;

public class TestFiller implements Filler {
    @Override
    public Consumer<Block.Setter> Fill(Point start, Random rand, FillerData data, BiomeColumn biomeColumn) {

        HeightFunction RoughTerr = new ExpFunc(
                new CombineFunc(
                        new NoiseFunc(rand.nextLong(), 0.06),
                        new NoiseFunc(rand.nextLong(), 0.006),
                        CombineFunc.CombineType.MULTIPLY
                ),
                1.3
        );

        HeightFunction Mountains = new CombineFunc(
                new ScaleFunc (0,1,0,0.8,
                        new ExpFunc(
                                new ScaleFunc(0, 2, 0, 1,
                                        new CombineFunc(
                                                new NoiseFunc(rand.nextLong(), 0.004),
                                                new NoiseFunc(rand.nextLong(), 0.008),
                                                CombineFunc.CombineType.ADD
                                        )
                                ),
                                0.75
                        )
                ),
                new ScaleFunc (0,1,0,0.2,
                        RoughTerr
                ),
                CombineFunc.CombineType.ADD
        );

        HeightFunction Hills = new CombineFunc(
                new ScaleFunc(0, 1, 200, 300,
                        Mountains
                ),
                new ScaleFunc(0, 2, 0.3, 0.4,
                        new CombineFunc(
                                new NoiseFunc(rand.nextLong(), 0.03),
                                new NoiseFunc(rand.nextLong(), 0.008),
                                CombineFunc.CombineType.ADD
                        )
                ),
                CombineFunc.CombineType.MULTIPLY
        );

        HeightFunction test = new FadeFunc(
                new ScaleFunc(60, 120, 0,1,
                        Hills
                ),
                new ExpFunc(
                        new ScaleFunc(0, 2, 0, 1,
                                new CombineFunc(
                                        new NoiseFunc(rand.nextLong(), 0.004),
                                        new NoiseFunc(rand.nextLong(), 0.008),
                                        CombineFunc.CombineType.ADD
                                )
                        ),
                        0.75
                ),
                new ScaleFunc(20, 155, 0 ,1,
                        new CombineFunc(
                                new ScaleFunc(0, 2, 20, 150,
                                        new CombineFunc(
                                                new NoiseFunc(rand.nextLong(), 0.008),
                                                new NoiseFunc(rand.nextLong(), 0.005),
                                                CombineFunc.CombineType.ADD
                                        )
                                ),
                                new ScaleFunc(0, 1, 0, 5,
                                        new NoiseFunc(rand.nextLong(), 0.001)
                                ),
                                CombineFunc.CombineType.ADD
                        )
                )
        );

        int Height = Math.round(test.applyHeightFunc(start.blockX(), start.blockZ()) * 100);

        return (setter -> {
            for (int y = -64; y <= Height; y++) {
                setter.setBlock(start.blockX(), y, start.blockZ(), Block.RED_CONCRETE);
            }
        });

    }
}
