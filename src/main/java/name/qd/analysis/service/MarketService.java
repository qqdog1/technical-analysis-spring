package name.qd.analysis.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import name.qd.analysis.Constants.Exchange;
import name.qd.analysis.utils.JsonUtils;

@Service
public class MarketService {
	private ObjectMapper objectMapper = JsonUtils.getObjectMapper();

	public String getSupportMarket() {
		return objectMapper.valueToTree(Exchange.values()).toString();
	}
}
