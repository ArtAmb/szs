package psk.pip.project.szs.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

	@GetMapping("/test")
	public String test() {
		return "dzialam!!";
	}

}
