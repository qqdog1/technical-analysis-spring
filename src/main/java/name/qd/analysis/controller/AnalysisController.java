package name.qd.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import name.qd.analysis.service.AnalysisService;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
	@Autowired
	private AnalysisService analysisService;

	@RequestMapping("/test")
	public String test() {
		return "123";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAnalysisResult() {
		return "";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAnalysisList() {
		return analysisService.getAnalysisList();
	}
}
