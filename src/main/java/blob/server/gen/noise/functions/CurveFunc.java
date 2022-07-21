package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class CurveFunc implements HeightFunction {

    private final double exp1;
    private final double exp2;
    private final HeightFunction func;

    public CurveFunc(double exp1, double exp2, HeightFunction func) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.func = func;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float val = this.func.applyHeightFunc(x,z);
        float result = (float) Math.pow(val, (val * this.exp2) + ((1-val) * this.exp1));
        return result;
    }
}
