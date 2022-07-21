package blob.server.gen.fillers;

public class FillerData {

    private final float Roughness;
    private final float Seaness;
    private final float Temperature;
    private final float BiomeVal;


    public FillerData(float roughness, float seaness, float temperature, float biomeVal) {
        this.Roughness = roughness;
        this.Seaness = seaness;
        this.Temperature = temperature;
        this.BiomeVal = biomeVal;
    }

    public float getRoughness() {
        return this.Roughness;
    }

    public float getSeaness() {
        return this.Seaness;
    }

    public float getTemperature() {
        return this.Temperature;
    }

    public float getBiomeVal() {
        return this.BiomeVal;
    }

}
