package payload;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Extractor Configuration created after merging all configuration options
 * 
 * @author prakhar
 */
public class ExtractorConfig {
	
	/**
	 * Store a configuration with key as extractor name and value as the Function reference
	 * @param key example 'payload.Extractors.cExtractor'
	 * @param value example java.util.Function references
	 */
	public static void putConfig(String key,Function<Object, Object> value) {
		extractorMap.put(key, value);
	}
	
	// assumes no parallel writes, one time config
	/**
	 * Internal map used to hold the configured extractos list
	 */
	private static final HashMap<String, Function<Object, Object>> extractorMap = new HashMap<>();

	/**
	 * Get configuration for a given key-name
	 * 
	 * @param inp example 'payload.Extractors.nestedFieldExtractor'
	 * @return
	 */
	// assume no exception,types waste a lot of time
	public static Function<Object, Object> get(Object inp) {
		return extractorMap.get(inp);
	}
	
	/**
	 * Returns a list of all the extractors configured
	 * @return
	 */
	//use immutables
	public static List<Function<Object,Object>> getAll(){
		return extractorMap
				.entrySet()
				.stream()
				.map(e->e.getValue())
				.collect(Collectors.toList());
	}

}
