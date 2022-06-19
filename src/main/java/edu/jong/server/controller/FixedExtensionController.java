package edu.jong.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jong.server.enums.FixedExtensionState;
import edu.jong.server.model.ExtensionInfo;
import edu.jong.server.service.FixedExtensionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "고정 확장자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fixed-extension", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class FixedExtensionController {

	private final FixedExtensionService fixedExtensionService;
	
	@ApiOperation("고정 확장자 체크")
	@PutMapping(value = "/{extensionNo}/check")
	ResponseEntity<ExtensionInfo> check(
			@ApiParam("고정 확장자 번호")
			@PathVariable("extensionNo") long extensionNo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(fixedExtensionService.checkOrUncheck(
						extensionNo, FixedExtensionState.CHECKED));
	}

	@ApiOperation("고정 확장자 체크 해제")
	@PutMapping(value = "/{extensionNo}/uncheck")
	ResponseEntity<ExtensionInfo> uncheck(
			@ApiParam("고정 확장자 번호")
			@PathVariable("extensionNo") long extensionNo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(fixedExtensionService.checkOrUncheck(
						extensionNo, FixedExtensionState.UNCHECKED));
	}
 
	@ApiOperation("고정 확장자 조회")
	@GetMapping
	ResponseEntity<List<ExtensionInfo>> search(
			@ApiParam("확장자 체크 여부")
			@RequestParam(required = false) FixedExtensionState state) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(fixedExtensionService.search(state));
	}
}
