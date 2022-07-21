package blob.server.biomes.config;

import blob.server.biomes.BiomeConfig;
import blob.server.gen.fillers.FillerData;

public class ForestConfig implements BiomeConfig {

    private final ForestType type;

    public ForestConfig(ForestType type) {
        this.type = type;
    }

    @Override
    public boolean isValid(FillerData d, int Height) {
        float temp = d.getTemperature();
        boolean h = Height > 64 && Height < 105;
        boolean t = switch (this.type) {
            case WARM -> temp > 0.55 && temp < 0.7;
            case COLD -> temp > 0.45 && temp < 0.6;
        };
        return h && t;
    }

    public enum ForestType {
        WARM,
        COLD
    }
}
