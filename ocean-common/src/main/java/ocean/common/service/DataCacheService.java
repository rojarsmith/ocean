package ocean.common.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-15
 */
@Service
public interface DataCacheService extends BaseService<DataCacheService> {

	Optional<String> readString(String key);

	void update(String key, String value, int seconds);

	void delete(String key);

}
