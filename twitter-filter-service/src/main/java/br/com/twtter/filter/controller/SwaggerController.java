package br.com.twtter.filter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author fernandogodoy
 *
 */

@Controller
public class SwaggerController {

	@GetMapping("/swagger")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
}
