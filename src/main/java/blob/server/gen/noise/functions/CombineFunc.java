package blob.server.gen.noise.functions;

import blob.server.gen.noise.HeightFunction;

public class CombineFunc implements HeightFunction {

    private final HeightFunction Func1;
    private final HeightFunction Func2;
    private final CombineType Type;

    public CombineFunc(HeightFunction func1, HeightFunction func2, CombineType type) {
        this.Func1 = func1;
        this.Func2 = func2;
        this.Type = type;
    }

    @Override
    public float applyHeightFunc(double x, double z) {
        float val1 = this.Func1.applyHeightFunc(x,z);
        float val2 = this.Func2.applyHeightFunc(x,z);
        float res = switch (this.Type) {
            case ADD -> val1 + val2;
            case SUBTRACT -> val1 - val2;
            case MULTIPLY -> val1 * val2;
            case DIVIDE -> val1 / val2;
            case MIN -> Math.min(val1, val2);
            case MAX -> Math.max(val1, val2);
        };
        return res;
    }

    public enum CombineType {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MIN,
        MAX
    }

}
