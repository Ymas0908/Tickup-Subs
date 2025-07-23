package org.etix.adapters.session;

public interface ApplicationSession {

	void invalidate();

	Object get(String key);

	boolean has(String key);

	void delete(String key);


	boolean exists(String key);

	boolean missing(String key);

	void put(String key, Object value);

	void forget(String... keys);


}
