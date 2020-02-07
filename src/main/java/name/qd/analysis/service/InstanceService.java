package name.qd.analysis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import name.qd.analysis.chip.analyzer.ChipAnalyzerManager;
import name.qd.analysis.tech.analyzer.TechAnalyzerManager;

@Service
public class InstanceService {
	private TechAnalyzerManager techAnalyzerManager;
	private ChipAnalyzerManager chipAnalyzerManager;
	
	@Value("${file.cache.path}")
	private String cachePath;
	@Value("${twse.data.path}")
	private String twseDataPath;
	@Value("${write.cache.file}")
	private boolean isWriteCacheToFile;
	
	public TechAnalyzerManager getTechAnalyzerManager() {
		if(techAnalyzerManager == null) {
			techAnalyzerManager = new TechAnalyzerManager(cachePath, isWriteCacheToFile);
		}
		return techAnalyzerManager;
	}
	
	public ChipAnalyzerManager getChipAnalyzerManager() {
		if(chipAnalyzerManager == null) {
			chipAnalyzerManager = new ChipAnalyzerManager(cachePath, isWriteCacheToFile);
		}
		return chipAnalyzerManager;
	}
	
	public String getTWSEDataPath() {
		return twseDataPath;
	}
}
