package blob.server.biomes.config;

import blob.server.biomes.BiomeConfig;
import blob.server.gen.fillers.FillerData;

public class PeakConfig implements BiomeConfig {

    private final PeakType type;

    public PeakConfig(PeakType type) {
        this.type = type;
    }

    @Override
    public boolean isValid(FillerData d, int Height) {
        float temp = d.getTemperature();
        boolean h = Height > 160;
        boolean r = d.getRoughness() > 0.5;
        boolean t = switch (this.type) {
            case STONE -> temp > 0.65;
            case SNOW -> temp < 0.35;
            case JAGGED -> temp >= 0.35 && temp <= 0.65;
        };
        return h && r && t;
    }

    public enum PeakType {
        STONE,
        SNOW,
        JAGGED
    }

}
