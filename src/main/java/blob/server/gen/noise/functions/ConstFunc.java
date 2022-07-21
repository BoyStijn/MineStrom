package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class ConstFunc implements HeightFunction {


    private final float constant;

    public ConstFunc(float constant) {
        this.constant = constant;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        return this.constant;
    }
}
