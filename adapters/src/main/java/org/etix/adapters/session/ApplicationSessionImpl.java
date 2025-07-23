package org.etix.adapters.session;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Configuration
public class ApplicationSessionImpl implements ApplicationSession {

	private final HttpSession session;

	@Override
	public void invalidate() {
		try {
			session.invalidate();
		} catch (Exception e) {
			System.out.println("invalidated");
		}
	}

	@Override
	public Object get(String key) {
		return session.getAttribute(key);
	}

	@Override
	public boolean has(String key) {
		boolean exists = false;
		Iterator<String> iterators = session.getAttributeNames().asIterator();
		while (iterators.hasNext() && !exists) {
			exists = key.equals(iterators.next());
		}
		return exists;
	}

	@Override
	public void delete(String key) {
		session.removeAttribute(key);
	}


	@Override
	public boolean exists(String key) {
		return !Objects.isNull(this.get(key));
	}

	@Override
	public boolean missing(String key) {
		return !this.has(key);
	}

	@Override
	public void put(String key, Object value) {
		session.setAttribute(key, value);
	}

	@Override
	public void forget(String... keys) {
		List<String> itemKeys = Arrays.asList(keys);
		itemKeys.forEach(session::removeAttribute);
	}

}
