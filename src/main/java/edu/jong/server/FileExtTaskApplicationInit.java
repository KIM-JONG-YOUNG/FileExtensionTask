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
		fixedExtensionService.add("bat", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("cmd", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("com", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("cpl", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("exe", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("scr", FixedExtensionState.UNCHECKED);
		fixedExtensionService.add("js", FixedExtensionState.UNCHECKED);
	}
}