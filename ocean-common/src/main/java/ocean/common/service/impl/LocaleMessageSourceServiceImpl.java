package ocean.common.service.impl;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import ocean.common.service.LocaleMessageSourceService;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-17
 */
@Service
public class LocaleMessageSourceServiceImpl implements LocaleMessageSourceService {
	@Resource
	private MessageSource messageSource;

	public String getMessage(String code) {
		return getMessage(code, null);
	}

	/**
	 *
	 * @param code ： messages key.
	 * @param args : arguments array.
	 * @return
	 */
	public String getMessage(String code, Object[] args) {
		return getMessage(code, args, "");
	}

	/**
	 *
	 * @param code           : messages key.
	 * @param args           : arguments array.
	 * @param defaultMessage : default message without key.
	 * @return
	 */
	public String getMessage(String code, Object[] args, String defaultMessage) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, defaultMessage, locale);
	}
}
