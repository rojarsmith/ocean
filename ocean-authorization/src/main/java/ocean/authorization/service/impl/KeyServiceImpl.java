package ocean.authorization.service.impl;

import java.security.PublicKey;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;

import ocean.authorization.ServiceProperty;
import ocean.authorization.service.KeyService;
import ocean.common.util.JSON;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-22
 */
@Service
public class KeyServiceImpl implements KeyService {

	@Autowired
	ServiceProperty serviceProperty;

	@Autowired
	ResourceLoader resourceLoader;

	public CompletableFuture<Optional<String>> getPublicKey() {
		Resource key = null;
		if (serviceProperty.isTest()) {
			key = resourceLoader
			        .getResource("classpath:key/" + serviceProperty.getService().getOauth2().getKeyStore().getFile());
		} else {
			key = resourceLoader.getResource("file:" + serviceProperty.getSpring().getConfig().getAdditionalLocation()
			        + serviceProperty.getService().getOauth2().getKeyStore().getFile());
		}
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory( //
		        key, //
		        serviceProperty.getService().getOauth2().getKeyStore().getPassword().toCharArray());
		PublicKey pKey = keyStoreKeyFactory.getKeyPair(serviceProperty.getService().getOauth2().getKeyStore().getPair())
		        .getPublic();
		Optional<String> jkey = JSON.stringify(pKey.getEncoded());

		return CompletableFuture.completedFuture(jkey);
	}

}
