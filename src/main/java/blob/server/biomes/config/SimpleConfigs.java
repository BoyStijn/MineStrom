package blob.server.biomes.config;

import blob.server.biomes.BiomeConfig;
import blob.server.gen.fillers.FillerData;

public class SimpleConfigs {

    public static class Desert implements BiomeConfig {
        @Override
        public boolean isValid(FillerData d, int Height) {
            return d.getTemperature() > 0.75 && Height > 64 && Height < 115;
        }
    }

    public static class Swamp implements BiomeConfig {
        @Override
        public boolean isValid(FillerData d, int Height) {
            return d.getSeaness() > 0.3 && Height > 64 && Height < 105;
        }
    }

}
