package dev.rohan.portfolio.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.rohan.portfolio.domain.FileFormat;

@Controller
public class HomePageController {

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping(value = "/resume", produces = "application/pdf")
	@ResponseBody
	public FileFormat printResume() throws IOException {
		var resumePdf = new ClassPathResource("Rohan_s_HackerResume.pdf").getFile();
		
		return new FileFormat(resumePdf, "application/pdf");
	}
	
}
