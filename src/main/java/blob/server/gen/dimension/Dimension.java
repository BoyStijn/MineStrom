package blob.server.gen.dimension;

import blob.server.gen.noise.HeightFunction;
import blob.server.gen.noise.functions.CombineFunc;

public interface Dimension {

    float getHeightAt(double x, double z);
    float getRoughnessAt(double x, double z);
    float getSeanessAt(double x, double z);
    float getTemperatureAt(double x, double z);
    float getBiomeValueAt(double x, double z);

}
