package blob.server.utils;

public class MathUtils {

    public static float clamp(float $$0, float $$1, float $$2) {
        if ($$0 < $$1)
            return $$1;
        if ($$0 > $$2)
            return $$2;
        return $$0;
    }

    public static int clamp(int $$0, int $$1, int $$2) {
        if ($$0 < $$1)
            return $$1;
        if ($$0 > $$2)
            return $$2;
        return $$0;
    }

    public static int hsvToRgb(float $$0, float $$1, float $$2) {
        float $$8, $$11, $$14, $$17, $$20, $$23, $$9, $$12, $$15, $$18, $$21, $$24, $$10, $$13, $$16, $$19, $$22, $$25;
        int $$29, $$30, $$31, $$3 = (int)($$0 * 6.0F) % 6;
        float $$4 = $$0 * 6.0F - $$3;
        float $$5 = $$2 * (1.0F - $$1);
        float $$6 = $$2 * (1.0F - $$4 * $$1);
        float $$7 = $$2 * (1.0F - (1.0F - $$4) * $$1);
        switch ($$3) {
            case 0:
                $$8 = $$2;
                $$9 = $$7;
                $$10 = $$5;
                $$29 = clamp((int)($$8 * 255.0F), 0, 255);
                $$30 = clamp((int)($$9 * 255.0F), 0, 255);
                $$31 = clamp((int)($$10 * 255.0F), 0, 255);
                return $$29 << 16 | $$30 << 8 | $$31;
            case 1:
                $$11 = $$6;
                $$12 = $$2;
                $$13 = $$5;
                $$29 = clamp((int)($$11 * 255.0F), 0, 255);
                $$30 = clamp((int)($$12 * 255.0F), 0, 255);
                $$31 = clamp((int)($$13 * 255.0F), 0, 255);
                return $$29 << 16 | $$30 << 8 | $$31;
            case 2:
                $$14 = $$5;
                $$15 = $$2;
                $$16 = $$7;
                $$29 = clamp((int)($$14 * 255.0F), 0, 255);
                $$30 = clamp((int)($$15 * 255.0F), 0, 255);
                $$31 = clamp((int)($$16 * 255.0F), 0, 255);
                return $$29 << 16 | $$30 << 8 | $$31;
            case 3:
                $$17 = $$5;
                $$18 = $$6;
                $$19 = $$2;
                $$29 = clamp((int)($$17 * 255.0F), 0, 255);
                $$30 = clamp((int)($$18 * 255.0F), 0, 255);
                $$31 = clamp((int)($$19 * 255.0F), 0, 255);
                return $$29 << 16 | $$30 << 8 | $$31;
            case 4:
                $$20 = $$7;
                $$21 = $$5;
                $$22 = $$2;
                $$29 = clamp((int)($$20 * 255.0F), 0, 255);
                $$30 = clamp((int)($$21 * 255.0F), 0, 255);
                $$31 = clamp((int)($$22 * 255.0F), 0, 255);
                return $$29 << 16 | $$30 << 8 | $$31;
            case 5:
                $$23 = $$2;
                $$24 = $$5;
                $$25 = $$6;
                $$29 = clamp((int)($$23 * 255.0F), 0, 255);
                $$30 = clamp((int)($$24 * 255.0F), 0, 255);
                $$31 = clamp((int)($$25 * 255.0F), 0, 255);
                return $$29 << 16 | $$30 << 8 | $$31;
        }
        throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + $$0 + ", " + $$1 + ", " + $$2);
    }

}
