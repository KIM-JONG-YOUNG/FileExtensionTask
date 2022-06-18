package edu.jong.server.enums;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import edu.jong.server.exception.ConvertFailEnumException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FixedExtensionState {

	CHECKED(1), UNCHECKED(0);
	
	private final int code;
	
	@Converter
	public static class AttributeConverterImpl implements AttributeConverter<FixedExtensionState, Integer> {

		@Override
		public Integer convertToDatabaseColumn(FixedExtensionState attribute) {
			return Optional.ofNullable(attribute)
					.orElseThrow(() -> new ConvertFailEnumException("Convert Fail Enum(FixedExtensionState)!"))
					.getCode();
		}

		@Override
		public FixedExtensionState convertToEntityAttribute(Integer dbData) {
			switch (dbData) {
			case 0:	return FixedExtensionState.UNCHECKED;
			case 1:	return FixedExtensionState.CHECKED;
			default:
				throw new ConvertFailEnumException("Convert Fail Enum(FixedExtensionState)!");
			}
		}
		
	}
}
