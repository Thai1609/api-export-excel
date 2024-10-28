package com.deanshoes.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api-export-excel")
public class HomeController {

	@GetMapping("/")
	public String HomePage() {
		return "index";
	}

}
