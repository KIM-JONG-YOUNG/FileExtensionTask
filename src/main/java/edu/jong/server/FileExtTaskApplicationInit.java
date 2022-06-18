package edu.jong.server;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.jong.server.enums.FixedExtensionState;
import edu.jong.server.service.FixedExtensionService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileExtTaskApplicationInit implements ApplicationListener<ContextRefreshedEvent> {

	private final FixedExtensionService fixedExtensionService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		fixedExtensionService.add("jpg", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("png", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("exe", FixedExtensionState.UNCHECKED);
	}
}