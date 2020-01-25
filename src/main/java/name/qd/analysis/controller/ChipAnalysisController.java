package name.qd.analysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import name.qd.analysis.Constants.Action;
import name.qd.analysis.service.ChipAnalysisService;

@RestController
@RequestMapping("/chip/analysis")
public class ChipAnalysisController {
	private static Logger logger = LoggerFactory.getLogger(ChipAnalysisController.class);
	private static final String DAYS_OVER = "Days can't over than 20.";
	
	@Autowired
	private ChipAnalysisService chipAnalysisService;

	@RequestMapping(value = "/bestBranchBuy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> bestBranchBuy(String market, int days, int branchs, long tradeCost) {
		if(days > 20) {
			logger.error(DAYS_OVER);
			return new ResponseEntity<String>(DAYS_OVER, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		String result = chipAnalysisService.bestBranchBuySell(market, days, branchs, tradeCost, Action.BUY);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bestBranchSell", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> bestBranchSell(String market, int days, int branchs, long tradeCost) {
		if(days > 20) {
			logger.error(DAYS_OVER);
			return new ResponseEntity<String>(DAYS_OVER, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		String result = chipAnalysisService.bestBranchBuySell(market, days, branchs, tradeCost, Action.SELL);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
