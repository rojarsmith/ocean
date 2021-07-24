package ocean.authorization.security;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ocean.common.model.vo.ResultVO;
import ocean.common.service.LocaleMessageSourceService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-24
 */
public class RestOauthExceptionSerializer extends StdSerializer<RestOauthException> {

	@Autowired
	LocaleMessageSourceService localeMessageSourceService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8488465435995067774L;

	public RestOauthExceptionSerializer() {
		super(RestOauthException.class);
	}

	@Override
	public void serialize(RestOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeObject(new ResultVO<Object>(localeMessageSourceService.getMessage("MEMBER_IS_NOT_FOUND")));
	}

}
