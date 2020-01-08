package name.qd.analysis.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.qd.analysis.chip.analyzer.ChipAnalyzerManager;

@Service
public class ChipAnalysisService {
	private static Logger logger = LoggerFactory.getLogger(ChipAnalysisService.class);
	
	@Autowired
	private InstanceService instanceService;
	
	private ChipAnalyzerManager chipAnalyzerManager;
	
	@PostConstruct
	private void init() {
		chipAnalyzerManager = instanceService.getChipAnalyzerManager();
	}
}
