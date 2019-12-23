package name.qd.analysis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import name.qd.analysis.Constants.Exchange;
import name.qd.analysis.dataSource.DataSource;
import name.qd.analysis.dataSource.DataSourceFactory;
import name.qd.analysis.tech.TechAnalyzers;
import name.qd.analysis.tech.analyzer.TechAnalyzerManager;
import name.qd.analysis.tech.vo.AnalysisResult;
import name.qd.analysis.utils.JsonUtils;
import name.qd.analysis.utils.TimeUtil;

@Service
public class AnalysisService {
	private static Logger logger = LoggerFactory.getLogger(AnalysisService.class);
	private ObjectMapper objectMapper = JsonUtils.getObjectMapper();
	private DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
	private SimpleDateFormat sdf = TimeUtil.getDateFormat();
	
	@Autowired
	private InstanceService instanceService;
	
	private TechAnalyzerManager techAnalyzerManager = instanceService.getTechAnalyzerManager();
	
	public String getAnalyzerList() {
		return objectMapper.valueToTree(TechAnalyzers.values()).toString();
	}
	
	public String getAnalysisResult(String market, String analyzer, String product, String from, String to) {
		List<AnalysisResult> lst = null;
		Exchange exchange = Exchange.valueOf(market);
		TechAnalyzers techAnalyzer = TechAnalyzers.valueOf(analyzer);
		try {
			Date dateFrom = sdf.parse(from);
			Date dateTo = sdf.parse(to);
			DataSource dataSource = dataSourceFactory.getDataSource(exchange, instanceService.getTWSEDataPath());
			lst = techAnalyzerManager.getAnalysisResult(dataSource, techAnalyzer, product, dateFrom, dateTo);
			
		} catch (ParseException e) {
			logger.error("Parse time failed. From:{},To:{}", from, to, e);
		} catch (Exception e) {
			logger.error("Get analysis result failed. TechAnalysis:{}", analyzer, e);
		}
		
		String jsonString = null;
		try {
			jsonString =  objectMapper.writeValueAsString(lst).toString();
		} catch (JsonProcessingException e) {
			logger.error("Parse result to json failed.", e);
		}
		return jsonString;
	}
}
