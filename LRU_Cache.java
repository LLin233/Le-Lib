import java.util.LinkedHashMap;
import java.util.Map;

public class LRU_Cache<K, V> extends LinkedHashMap<K, V> {
	private final int MAX_CACHE_SIZE;

	public LRU_Cache(int cacheSize) {
		super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
		MAX_CACHE_SIZE = cacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry eldest) {
		return size() > MAX_CACHE_SIZE;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<K, V> entry : entrySet()) {
			sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
		}
		return sb.toString();
	}
	
	
	// =======================================================
	
	public static void main(String[] args) {
		/*
		 * short version to use;
		 */
		final int cacheSize = 100;
		Map<String, String> map = new LinkedHashMap<String, String>( (int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
				return size() > cacheSize;
			}
		};
	}
}
