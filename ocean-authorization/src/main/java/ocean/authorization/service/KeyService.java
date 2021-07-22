package ocean.authorization.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-22
 */
public interface KeyService {

	@Async("executor")
	public CompletableFuture<Optional<String>> getPublicKey();

}
