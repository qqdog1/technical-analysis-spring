package name.qd.analysis.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import name.qd.analysis.Constants.Action;
import name.qd.analysis.Constants.Exchange;
import name.qd.analysis.chip.ChipAnalyzers;
import name.qd.analysis.chip.analyzer.ChipAnalyzerManager;
import name.qd.analysis.dataSource.DataSource;
import name.qd.analysis.dataSource.DataSourceFactory;
import name.qd.analysis.utils.DateUtils;
import name.qd.analysis.utils.JsonUtils;

@Service
public class ChipAnalysisService {
	private static Logger logger = LoggerFactory.getLogger(ChipAnalysisService.class);
	private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
	private ObjectMapper objectMapper = JsonUtils.getObjectMapper();
	
	@Autowired
	private InstanceService instanceService;
	
	private ChipAnalyzerManager chipAnalyzerManager;
	
	@PostConstruct
	private void init() {
		chipAnalyzerManager = instanceService.getChipAnalyzerManager();
	}
	
	public String bestBranchBuySell(String market, int days, int branchs, long tradeCost, Action side) {
		Exchange exchange = Exchange.valueOf(market);
		DataSource dataSource = dataSourceFactory.getDataSource(exchange, instanceService.getTWSEDataPath());
		String[] inputs = {String.valueOf(branchs)};
		List<Date> lstFromTo = DateUtils.getFromToByDays(instanceService.getTWSEDataPath(), market, days);
		
		List<List<String>> lst = chipAnalyzerManager.getAnalysisResult(dataSource, ChipAnalyzers.BEST_BRANCH_BUY_SELL, "", "", lstFromTo.get(0), lstFromTo.get(1), tradeCost, false, inputs);
		ArrayNode node = objectMapper.createArrayNode();
		
		for(List<String> lstResult : lst) {
			if(lstResult.get(2).equals(side.name())) {
				ObjectNode objectNode = objectMapper.createObjectNode();
				objectNode.put("branch", lstResult.get(0));
				objectNode.put("product", lstResult.get(1));
				objectNode.put("side", lstResult.get(2));
				objectNode.put("price", lstResult.get(3));
				objectNode.put("share", lstResult.get(4));
				objectNode.put("volume", lstResult.get(5));
				node.add(objectNode);
			}
		}
		return node.toString();
	}
}
