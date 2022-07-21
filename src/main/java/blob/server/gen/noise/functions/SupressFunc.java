package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class SupressFunc implements HeightFunction {

    private final double suppres;
    private final double gain;
    private final double exponent1;
    private final double exponent2;
    private final HeightFunction func;

    public SupressFunc(double suppres, double gain, double exponent1, double exponent2, HeightFunction func) {
        this.suppres = suppres;
        this.gain = gain;
        this.exponent1 = exponent1;
        this.exponent2 = exponent2;
        this.func = func;
    }


    @Override
    public float applyHeightFunc(double x, double z) {
        float val = this.func.applyHeightFunc(x,z);
        if (val < this.suppres) return 0;
        if (val > this.gain) return val;
        return scale(val);
    }

    private float scale(float val) {
        double scaled = (val - this.suppres) / (this.gain - this.suppres);
        float fin = (float) (Math.pow(scaled, (scaled * this.exponent2) + ((1-scaled) * this.exponent1)) * this.gain);
        return fin;
    }
}
