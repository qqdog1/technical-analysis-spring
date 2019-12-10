package name.qd.analysis.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import name.qd.analysis.tech.TechAnalyzers;

@Service
public class AnalysisService {
	private ObjectMapper objectMapper = new ObjectMapper();

	public String getAnalysisList() {
		return objectMapper.valueToTree(TechAnalyzers.values()).toString();
	}
}
