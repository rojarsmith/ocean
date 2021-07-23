package ocean.authorization.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ocean.authorization.ServiceProperty;
import ocean.authorization.service.KeyService;
import ocean.common.controller.BaseController;

/**
 * For public RSA service
 * 
 * @author Rojar Smith
 *
 * @date 2021-07-21
 */
@RestController
public class KeyController extends BaseController {

	@Autowired
	ServiceProperty serviceProperty;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	KeyService keyService;

	@GetMapping("/rsa/pub")
	public ResponseEntity<?> getKey() throws InterruptedException, ExecutionException {
		CompletableFuture<Optional<String>> jkeyCompletableFuture = keyService.getPublicKey();
		CompletableFuture.allOf(jkeyCompletableFuture).join();
		Optional<String> jkey = jkeyCompletableFuture.get();
		Map<String, Object> data = new HashMap<>();
		data.put("pub", jkey.get());
		return success(data);
	}

}
