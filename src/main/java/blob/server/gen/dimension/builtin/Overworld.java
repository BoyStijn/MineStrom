package blob.server.gen.dimension.builtin;

import blob.server.gen.dimension.Dimension;
import blob.server.gen.noise.HeightFunction;
import blob.server.gen.noise.functions.*;
import org.checkerframework.checker.units.qual.C;

import java.util.Random;

public class Overworld implements Dimension {

    private final long Seed;
    private final Random rand;
    private final HeightFunction HFunc;
    private final HeightFunction Roughness;
    private final HeightFunction SeaNess;
    private final HeightFunction Temperature;
    private final HeightFunction BiomeNoise;

    public Overworld(long seed) {
        this.Seed = seed;
        this.rand = new Random(seed);

        //FUNCTION DEFINE//

        this.Temperature = new SupressFunc(
                0.2,
                0.80,
                2,
                0,
                new ScaleFunc(20, 160, 0 ,1,
                        new CombineFunc(
                                new ScaleFunc(0, 2, 20, 150,
                                        new CombineFunc(
                                                new NoiseFunc(rand.nextLong(), 0.0005),
                                                new NoiseFunc(rand.nextLong(), 0.0012),
                                                CombineFunc.CombineType.ADD
                                        )
                                ),
                                new ScaleFunc(0, 1, 0, 10,
                                        new NoiseFunc(rand.nextLong(), 0.001)
                                ),
                                CombineFunc.CombineType.ADD
                        )
                )
        );

        HeightFunction TempMul = new CombineFunc(
                new ConstFunc(1),
                new SupressFunc(
                        0.6,
                        0.85,
                        2,
                        0,
                        this.Temperature
                ),
                CombineFunc.CombineType.SUBTRACT
        );

        this.BiomeNoise = new ScaleFunc(20, 155, 0 ,1,
                new CombineFunc(
                        new ScaleFunc(0, 2, 20, 150,
                                new CombineFunc(
                                        new NoiseFunc(rand.nextLong(), 0.0005),
                                        new NoiseFunc(rand.nextLong(), 0.002),
                                        CombineFunc.CombineType.ADD
                                )
                        ),
                        new ScaleFunc(0, 1, 0, 5,
                                new NoiseFunc(rand.nextLong(), 0.001)
                        ),
                        CombineFunc.CombineType.ADD
                )
        );

        this.SeaNess =
                new CombineFunc(
                        new SupressFunc(
                                0.5,
                                0.80,
                                2.25,
                                0.15,
                                new ScaleFunc(20, 160, 0 ,1,
                                        new CombineFunc(
                                                new ScaleFunc(0, 2, 20, 150,
                                                        new CombineFunc(
                                                                new NoiseFunc(rand.nextLong(), 0.0005),
                                                                new NoiseFunc(rand.nextLong(), 0.0012),
                                                                CombineFunc.CombineType.ADD
                                                        )
                                                ),
                                                new ScaleFunc(0, 1, 0, 10,
                                                        new NoiseFunc(rand.nextLong(), 0.001)
                                                ),
                                                CombineFunc.CombineType.ADD
                                        )
                                )
                        ),
                        TempMul,
                        CombineFunc.CombineType.MULTIPLY
        );

        this.Roughness = new CombineFunc(
                new SupressFunc(
                        0.60,
                        0.80,
                        2.25,
                        0.15,

                                new ScaleFunc(20, 155, 0 ,1,
                                        new CombineFunc(
                                                new ScaleFunc(0, 2, 20, 150,
                                                        new CombineFunc(
                                                                new NoiseFunc(rand.nextLong(), 0.0005),
                                                                new NoiseFunc(rand.nextLong(), 0.002),
                                                                CombineFunc.CombineType.ADD
                                                        )
                                                ),
                                                new ScaleFunc(0, 1, 0, 5,
                                                        new NoiseFunc(rand.nextLong(), 0.001)
                                                ),
                                                CombineFunc.CombineType.ADD
                                        )
                                )
                        ),
                TempMul,
                CombineFunc.CombineType.MULTIPLY
        );

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

        HeightFunction RoughMountain = new ScaleFunc(0, 1, 110, 320,
                Mountains
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

        HeightFunction SmoothHills = new FadeFunc(
                Hills,
                new ScaleFunc (0,1,70,85,
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

        HeightFunction SeaNoise = new ScaleFunc(0, 1, -40, 0,
                new ExpFunc(
                        new ScaleFunc(-2, 2, 0, 1,
                                new CombineFunc(
                                        new NoiseFunc(rand.nextLong(), 0.004),
                                        new NoiseFunc(rand.nextLong(), 0.008),
                                        CombineFunc.CombineType.ADD
                                )
                        ),
                        0.25
                )
        );

        this.HFunc = new FadeFunc(
                SeaNoise,
                new FadeFunc(
                        RoughMountain,
                        SmoothHills,
                        this.Roughness
                ),
                this.SeaNess
        );
    }

    @Override
    public float getHeightAt(double x, double y) {
        return this.HFunc.applyHeightFunc(x,y);
    }

    @Override
    public float getRoughnessAt(double x, double z) {
        return this.Roughness.applyHeightFunc(x,z);
    }

    @Override
    public float getSeanessAt(double x, double z) {
        return this.SeaNess.applyHeightFunc(x,z);
    }

    @Override
    public float getTemperatureAt(double x, double z) {
        return this.Temperature.applyHeightFunc(x,z);
    }

    @Override
    public float getBiomeValueAt(double x, double z) {
        return this.BiomeNoise.applyHeightFunc(x,z);
    }
}
