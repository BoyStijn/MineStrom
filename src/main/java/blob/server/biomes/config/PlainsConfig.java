package blob.server.biomes.config;

import blob.server.biomes.BiomeConfig;
import blob.server.gen.fillers.FillerData;

public class PlainsConfig implements BiomeConfig {

    private final PlainsType type;

    public PlainsConfig(PlainsType t) {
        this.type = t;
    }

    @Override
    public boolean isValid(FillerData d, int Height) {
        float temp = d.getTemperature();
        boolean h = Height > 64 && Height < 105;
        boolean t = switch (this.type) {
            case NORMAL -> temp > 0.45 && temp < 0.6;
            case COLD -> temp >= 0.25 && temp < 0.5;
            case FROZEN -> temp < 0.25;
        };
        boolean r =  d.getRoughness() < 0.15;
        return t && h && r;
    }

    public enum PlainsType {
        NORMAL,
        COLD,
        FROZEN
    }

}
