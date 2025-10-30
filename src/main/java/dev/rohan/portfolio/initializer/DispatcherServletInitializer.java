package dev.rohan.portfolio.initializer;

import java.util.logging.Logger;

import org.jspecify.annotations.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import dev.rohan.portfolio.config.DispatcherServletConfig;
import jakarta.servlet.Filter;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	Logger logs = Logger.getLogger("logs.txt");
	
	@Override
	protected Class<?> @Nullable [] getRootConfigClasses() {
		return null;
	}
	
	@Override
	protected Filter @Nullable [] getServletFilters() {
		var filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		logs.info("UTF-8 enforced from servlet filters");
		return new Filter[] {filter};
	}


	@Override
	protected Class<?> @Nullable [] getServletConfigClasses() {
		return new Class[] {DispatcherServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		logs.info("Dispatcher Servlet mapped");
		return new String[] {"/"};
	}

}
