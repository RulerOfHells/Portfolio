package dev.rohan.portfolio.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import dev.rohan.portfolio.converter.FileToPdfConverter;

@Configuration
@ComponentScan(basePackages = "dev.rohan.portfolio*")
@EnableWebMvc
public class DispatcherServletConfig implements WebMvcConfigurer {

	@Autowired
	ApplicationContext applicationContext;
	
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Add your custom converter to the list
        converters.add(new FileToPdfConverter());

        // IMPORTANT: The order matters! Add it early to ensure it takes precedence
        // over default converters (like the JSON converter) for your specific type.
    }
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		var thymeleafVr =  new ThymeleafViewResolver();
		thymeleafVr.setTemplateEngine(templateEngine());
		return thymeleafVr;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
	    // SpringResourceTemplateResolver automatically integrates with Spring's own
	    // resource resolution infrastructure, which is highly recommended.
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(this.applicationContext);
	    templateResolver.setPrefix("classpath:/templates/");
	    templateResolver.setSuffix(".html");
	    // HTML is the default value, added here for the sake of clarity.
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    // Template cache is true by default. Set to false if you want
	    // templates to be automatically updated when modified.
	    templateResolver.setCacheable(true);
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(){
	    // SpringTemplateEngine automatically applies SpringStandardDialect and
	    // enables Spring's own MessageSource message resolution mechanisms.
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	    // speed up execution in most scenarios, but might be incompatible
	    // with specific cases when expressions in one template are reused
	    // across different data types, so this flag is "false" by default
	    // for safer backwards compatibility.
	    templateEngine.setEnableSpringELCompiler(true);
	    return templateEngine;
	}
}
