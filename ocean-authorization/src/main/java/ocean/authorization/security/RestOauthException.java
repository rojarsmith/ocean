package ocean.authorization.security;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-24
 */
@JsonSerialize(using = RestOauthExceptionSerializer.class)
public class RestOauthException extends OAuth2Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5529346358400607727L;

	public RestOauthException(String msg) {
		super(msg);
	}

}