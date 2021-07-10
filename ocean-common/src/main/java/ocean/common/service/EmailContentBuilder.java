package ocean.common.service;

import java.util.Optional;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-10
 */
public interface EmailContentBuilder {
	<T> Optional<String> generateMailContent(T content, String template);
}
