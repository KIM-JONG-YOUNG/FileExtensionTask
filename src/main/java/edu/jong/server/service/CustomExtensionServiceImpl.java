package edu.jong.server.service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import edu.jong.server.entity.CustomExtensionEntity;
import edu.jong.server.exception.BadParameterException;
import edu.jong.server.exception.DuplicateResourceException;
import edu.jong.server.exception.NotFoundResourceException;
import edu.jong.server.model.ExtensionInfo;
import edu.jong.server.repository.CustomExtensionRepository;
import edu.jong.server.repository.FixedExtensionRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomExtensionServiceImpl implements CustomExtensionService {

	private final ExtensionInfo.EntityMapper extensionMapper;
	private final CustomExtensionRepository customExtensionRepository;
	private final FixedExtensionRepository fixedExtensionRepository;
	
	@Override
	public ExtensionInfo add(String extension) {

		if (StringUtils.isBlank(extension))
			throw new BadParameterException("확장자가 비어있습니다.");

		if (extension.trim().length() > 20)
			throw new BadParameterException("확장자의 길이는 20자를 넘길 수 없습니다.");

		if (!Pattern.matches("^[a-zA-Z0-9]+$", extension))
			throw new BadParameterException("확장자는 영문과 숫자만 가능합니다.");

		if (fixedExtensionRepository.existsByExtension(extension))
			throw new DuplicateResourceException("이미 고정 확장자에 존재하는 확장자입니다.");
		
		if (customExtensionRepository.existsByExtension(extension))
			throw new DuplicateResourceException("이미 사용자 정의 확장자에 존재하는 확장자입니다.");

		if (customExtensionRepository.count() >= 200)
			throw new DuplicateResourceException("이미 등록된 사용자 정의 확장자가 200개 이상입니다.");

		return extensionMapper.toInfo(
				customExtensionRepository.save(
						CustomExtensionEntity.builder()
						.extension(extension.trim().toLowerCase()).build()));
	}

	@Override
	public void remove(long extensionNo) {
		customExtensionRepository.delete(
				customExtensionRepository.findByIdForUpdate(extensionNo)
					.orElseThrow(() -> new NotFoundResourceException("해당하는 사용자 정의 확장자가 존재하지 않습니다.")));
	} 

	@Override
	public List<ExtensionInfo> search() {
		return customExtensionRepository.findAll()
				.stream().map((entity) -> extensionMapper.toInfo(entity)).collect(Collectors.toList());
	}

}
