package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class ScaleFunc implements HeightFunction {

    private final double min_old;
    private final double range_old;
    private final double min_new;
    private final double range_new;
    private final HeightFunction FUNC;

    public ScaleFunc(double min_old, double max_old, double min_new, double max_new, HeightFunction func) {
        this.min_old = min_old;
        this.range_old = max_old - min_old;
        this.min_new = min_new;
        this.range_new = max_new - min_new;
        this.FUNC = func;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float val = FUNC.applyHeightFunc(x,z);
        double scaled = (val - this.min_old) / this.range_old;
        float fin = (float) (this.min_new + scaled * this.range_new);
        return fin;
    }
}
