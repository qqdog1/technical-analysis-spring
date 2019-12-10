package name.qd.analysis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

	@RequestMapping("/test")
	public String test() {
		return "123";
	}
	
	@RequestMapping("")
	public String getAnalysisResult() {
		return "";
	}
}
