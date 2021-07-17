package ocean.common.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Service
public interface DataCacheService extends BaseService<DataCacheService> {

	Optional<String> readString(String key);

	Optional<Long> readExpire(String key);

	boolean update(String key, String value);

	boolean update(String key, String value, int seconds);

	Optional<Long> updateIncrement(String key, long delta);

	Optional<Long> updateDecrement(String key, long delta);

	boolean updateExpire(String key, long time);

	boolean delete(String key);

	boolean isExists(String key);

}
