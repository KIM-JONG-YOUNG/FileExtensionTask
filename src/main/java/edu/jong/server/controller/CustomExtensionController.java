package edu.jong.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jong.server.model.ExtensionInfo;
import edu.jong.server.service.CustomExtensionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "사용자 지정 확장자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/custom-extension", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomExtensionController {

	private final CustomExtensionService customExtensionService;
	
	@ApiOperation("사용자 지정 확장자 추가")
	@PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<ExtensionInfo> add(
			@ApiParam("사용자 지정 확장자")
			@RequestBody String extension) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(customExtensionService.add(extension.trim()));
	}
	
	@ApiOperation("사용자 지정 확장자 삭제")
	@DeleteMapping(value = "/{extensionNo}")
	ResponseEntity<Void> remove(
			@ApiParam("사용자 지정 확장자 번호")
			@PathVariable("extensionNo") long extensionNo) {
		customExtensionService.remove(extensionNo);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation("사용자 지정 확장자 조회")
	@GetMapping
	ResponseEntity<List<ExtensionInfo>> search() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(customExtensionService.search());
	}
}
