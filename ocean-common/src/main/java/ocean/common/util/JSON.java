package ocean.common.util;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-16
 */
public class JSON {

	public static Optional<String> stringify(Object o) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(o);
			return Optional.ofNullable(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public static <T> Optional<T> parse(String json, Class<T> target) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			T obj = objectMapper.readValue(json, target);
			return Optional.ofNullable(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
