package com.james.springboot.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * author: yang
 * datetime: 2020/11/23 15:33
 */
@Configuration
public class WebMvcConfig {

    private static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);


	@Bean(name="templateEngine")
	@Description("org.thymeleaf.spring4.SpringTemplateEngine:"
			+ "http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#integrating-thymeleaf-with-spring")
	public SpringTemplateEngine getTemplateEngine(){
		SpringTemplateEngine template = new SpringTemplateEngine();
		template.setTemplateResolver(getTemplateResolver());
		return template;
	}
	@Bean
	@Description("org.thymeleaf.templateresolver.ServletContextTemplateResolver")
	public ServletContextTemplateResolver getTemplateResolver(){
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		return resolver;
	}
}
