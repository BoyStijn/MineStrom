package blob.server.utils;

import blob.server.gen.noise.HeightFunction;
import net.minestom.server.world.biomes.Biome;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DataList<F> {

    private final ConcurrentHashMap<String, F> data = new ConcurrentHashMap();

    public F getData(String key) {
        return this.data.getOrDefault(key.toLowerCase(), null);
    }

    public F putData(String key, F value) {
        F d = this.data.put(key.toLowerCase(), value);
        return (d == null) ? value : d;
    }

    public F computeIfAbsent(String key, Function<String, F> function) {
        return this.data.computeIfAbsent(key.toLowerCase() , function);
    }

    public Collection<F> getAllData() {
        return this.data.values();
    }

    public ConcurrentHashMap<String, F> getData() {
        return this.data;
    }
    public static DataList<DataList<?>> DATA_LISTS = new DataList<>();
    public static DataList<Biome> BIOME_LISTS = new DataList<>();

    public static void bootstrap() {
        DATA_LISTS.putData("biome_list", BIOME_LISTS);
        BIOME_LISTS.putData("minecraft:plains", Biome.PLAINS);
    }

}
