package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;
import blob.server.gen.noise.OpenSimplex2S;

public class FractalFunc implements HeightFunction {

    private final long Seed;
    private final double max;
    private final int depth;

    public FractalFunc(long seed, double max, int depth) {
        Seed = seed;
        this.max = max;
        this.depth = depth;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float value = 0;
        double b = Math.pow(2, this.depth) - 1;
        for (int i = 0; i<this.depth; i++) {
            double scale = this.max * (1.0D/(this.depth-i));
            double c = Math.pow(2, (this.depth-1) - i);
            value += (getNoise(x * scale, z * scale) * (c/b));
        }
        return value;
    }

    private float getNoise(double x, double z) {
        return (OpenSimplex2S.noise2(this.Seed, x, z) + 1)/2;
    }

}
