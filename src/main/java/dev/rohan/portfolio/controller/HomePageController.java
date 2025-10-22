package dev.rohan.portfolio.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.rohan.portfolio.domain.FileFormat;

@Controller
public class HomePageController {
	
	@Autowired
	MessageSource messageSource;
	
	Locale locale = LocaleContextHolder.getLocale();

	@GetMapping("/")
	public String home(Model model) {
		model.addAllAttributes(Map.of(
				"title", messageSource.getMessage("title", null, locale),
				"about", messageSource.getMessage("about", null, locale),
				"pos1", messageSource.getMessage("pos1", null, locale),
				"comp1", messageSource.getMessage("comp1", null, locale),
				"pos2", messageSource.getMessage("pos2", null, locale),
				"comp2", messageSource.getMessage("comp2", null, locale),
				"points1", messageSource.getMessage("points1", null, locale),
				"points2", messageSource.getMessage("points2", null, locale),
				"flag", false
				));
		return "index";
	}
	
	@GetMapping(value = "/resume", produces = "application/pdf")
	@ResponseBody
	public FileFormat printResume() throws IOException {
		var resumePdf = new ClassPathResource("Rohan_s_HackerResume.pdf").getFile();
		
		return new FileFormat(resumePdf, "application/pdf");
	}
	
}
