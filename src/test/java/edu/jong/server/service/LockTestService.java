package edu.jong.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jong.server.entity.FixedExtensionEntity;
import edu.jong.server.enums.FixedExtensionState;
import edu.jong.server.exception.BadParameterException;
import edu.jong.server.exception.NotFoundResourceException;
import edu.jong.server.model.ExtensionInfo;
import edu.jong.server.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LockTestService {

	private final ExtensionInfo.EntityMapper extensionMapper;
	private final FixedExtensionRepository fixedExtensionRepository;

	public ExtensionInfo get(long extensionNo) {
		return extensionMapper.toInfo(fixedExtensionRepository.findById(extensionNo)
				.orElseThrow(() -> new NotFoundResourceException("해당하는 고정 확장자가 존재하지 않습니다.")));
	}
	
	public ExtensionInfo checkOrUncheck(long extensionNo, FixedExtensionState state, long sleepTime) throws InterruptedException {

		if (state == null)
			throw new BadParameterException("'state' is Null");

		FixedExtensionEntity entity = fixedExtensionRepository.findByIdForUpdate(extensionNo)
			.orElseThrow(() -> new NotFoundResourceException("해당하는 고정 확장자가 존재하지 않습니다."));

		Thread.sleep(sleepTime);
		
		return extensionMapper.toInfo(
				fixedExtensionRepository.save(
						(state == FixedExtensionState.CHECKED) 
						? entity.check() 
						: entity.uncheck()));
	}
}
