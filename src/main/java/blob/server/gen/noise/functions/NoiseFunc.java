package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;
import blob.server.gen.noise.OpenSimplex2S;

public class NoiseFunc implements HeightFunction {

    private final long Seed;
    private final double scalex;
    private final double scalez;

    public NoiseFunc(long Seed, double scale) {
        this(Seed, scale, scale);
    }

    public NoiseFunc(long Seed, double scalex, double scalez) {
        this.Seed = Seed;
        this.scalex = scalex;
        this.scalez = scalez;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float val = (OpenSimplex2S.noise2(this.Seed, (x * this.scalex), (z * scalez)) + 1)/2;
        return val;
    }
}
