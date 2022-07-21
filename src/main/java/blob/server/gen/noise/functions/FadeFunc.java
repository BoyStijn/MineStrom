package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class FadeFunc implements HeightFunction {

    private final HeightFunction function1;
    private final HeightFunction function2;
    private final HeightFunction function3;

    public FadeFunc(HeightFunction function1, HeightFunction function2, HeightFunction function3) {
        this.function1 = function1;
        this.function2 = function2;
        this.function3 = function3;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float multiplier1 = function3.applyHeightFunc(x,z);
        float multiplier2 = 1 - multiplier1;
        return (function1.applyHeightFunc(x,z) * multiplier1) + (function2.applyHeightFunc(x,z) * multiplier2);
    }
}
