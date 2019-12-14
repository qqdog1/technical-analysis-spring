package name.qd.analysis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import name.qd.analysis.tech.analyzer.TechAnalyzerManager;

@Service
public class InstanceService {
	private TechAnalyzerManager techAnalyzerManager;
	
	@Value("${file.cache.path}")
	private String cachePath;
	
	public TechAnalyzerManager getTechAnalyzerManager() {
		if(techAnalyzerManager == null) {
			techAnalyzerManager = new TechAnalyzerManager(cachePath);
		}
		return techAnalyzerManager;
	}
}