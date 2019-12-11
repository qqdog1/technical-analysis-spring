package name.qd.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<String> getAnalysisResult(String market, String analyzer, @RequestParam(required = false) String product, String from, String to) {
		String result = analysisService.getAnalysisResult(market, analyzer, product, from, to);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAnalysisList() {
		return new ResponseEntity<>(analysisService.getAnalyzerList(), HttpStatus.OK);
	}
}
