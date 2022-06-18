package edu.jong.server.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.jong.server.entity.CustomExtensionEntity;
import edu.jong.server.entity.FixedExtensionEntity;
import edu.jong.server.enums.FixedExtensionState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtensionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private long no;
	
	private String extension;
	
	private FixedExtensionState state;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
	private LocalDateTime createDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd")
	private LocalDateTime updateDateTime;

	@Mapper(componentModel = "spring",
			unmappedSourcePolicy = ReportingPolicy.IGNORE,
			unmappedTargetPolicy = ReportingPolicy.IGNORE)
	public interface EntityMapper {
		
		ExtensionInfo toInfo(CustomExtensionEntity entity);
	
		ExtensionInfo toInfo(FixedExtensionEntity entity);
	}
}
