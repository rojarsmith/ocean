package ocean.common.service.impl;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ocean.common.service.EmailContentBuilder;

/**
 * @author Rojar Smith
 *
 * @date 2021-07-10
 */
@Service
public class EmailContentBuilderImpl implements EmailContentBuilder {
	TemplateEngine templateEngine;

	public EmailContentBuilderImpl(@Autowired TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Override
	public <T> Optional<String> generateMailContent(T content, String template) {
		Locale locale = LocaleContextHolder.getLocale();
		Context context = new Context(locale);
		context.setVariable("content", content);
		return Optional.ofNullable(templateEngine.process(template, context));
	}

}
