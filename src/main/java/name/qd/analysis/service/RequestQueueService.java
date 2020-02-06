package name.qd.analysis.service;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.qd.analysis.vo.ClientAction;

@Service
public class RequestQueueService {
	private static final Logger log = LoggerFactory.getLogger(RequestQueueService.class);
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private ClientActionQueue clientActionQueue = new ClientActionQueue();
	
	@Autowired
	private ChipAnalysisService chipAnalysisService;
	
	@Autowired
	private TechAnalysisService techAnalysisService;
	
	@PostConstruct
	private void init() {
		executor.execute(clientActionQueue);
	}
	
	public class ClientActionQueue implements Runnable {
		private final ConcurrentLinkedDeque<ClientAction> queue = new ConcurrentLinkedDeque<>();
		
		public void putQueue(ClientAction clientAction) {
			queue.offer(clientAction);
		}
		
		@Override
		public void run() {
			ClientAction clientAction;
			while (!Thread.currentThread().isInterrupted()) {
				if((clientAction = queue.poll())!=null) {
					if(clientAction.getChipAnalyzers() != null) {
						
					} else if(clientAction.getTechAnalyzers() != null) {
						
					} else {
						log.error("No analyzer was set.");
					}
				}
			}
		}
	}
}
