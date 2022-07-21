package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class ExpFunc implements HeightFunction {


    private final HeightFunction function;
    private final double exponent;

    public ExpFunc(HeightFunction func, double exp) {
        this.function = func;
        this.exponent = exp;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float val = this.function.applyHeightFunc(x,z);
        float pow = (float) Math.pow(val, this.exponent);

        return pow;
    }
}
