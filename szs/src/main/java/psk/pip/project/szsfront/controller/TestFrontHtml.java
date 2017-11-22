package psk.pip.project.szsfront.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFrontHtml {

	@GetMapping("/front/test")
	public String test() {
		return "DZIALAM!!!";
	}

}
