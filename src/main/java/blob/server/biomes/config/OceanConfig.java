package blob.server.biomes.config;

import blob.server.biomes.BiomeConfig;
import blob.server.gen.fillers.FillerData;

public class OceanConfig implements BiomeConfig {

    private final boolean deep;
    private final OceanType type;

    public OceanConfig(boolean deep, OceanType type) {
        this.deep = deep;
        this.type = type;
    }

    @Override
    public boolean isValid(FillerData d, int Height) {
        float temp = d.getTemperature();
        boolean h = (this.deep) ? Height < 40 : Height < 64 && 40 <= Height;
        boolean t = switch (this.type) {
            case WARM -> temp > 0.75;
            case LUKEWARM -> temp >= 0.6 && temp < 0.8;
            case NORMAL -> temp > 0.45 && temp < 0.6;
            case COLD -> temp >= 0.25 && temp < 0.5;
            case FROZEN -> temp < 0.25;
        };
        return h && t;
    }

    public boolean hasGravel() {
        return switch (this.type) {
            case WARM -> false;
            case LUKEWARM -> false;
            case NORMAL -> true;
            case COLD -> true;
            case FROZEN -> true;
        };
    }

    public OceanType getType() {
        return this.type;
    }

    public enum OceanType {
        WARM,
        LUKEWARM,
        NORMAL,
        COLD,
        FROZEN
    }

}
