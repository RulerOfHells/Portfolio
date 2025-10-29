package dev.rohan.portfolio.initializer;

import org.jspecify.annotations.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import dev.rohan.portfolio.config.DispatcherServletConfig;
import jakarta.servlet.Filter;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?> @Nullable [] getRootConfigClasses() {
		return null;
	}
	
	@Override
	protected Filter @Nullable [] getServletFilters() {
		var filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		System.out.println("here");
		return new Filter[] {filter};
	}


	@Override
	protected Class<?> @Nullable [] getServletConfigClasses() {
		return new Class[] {DispatcherServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("It is working");
		return new String[] {"/"};
	}

}
