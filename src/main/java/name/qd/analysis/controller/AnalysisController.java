package name.qd.analysis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {

	@RequestMapping("/test")
	public String test() {
		return "123";
	}
}
