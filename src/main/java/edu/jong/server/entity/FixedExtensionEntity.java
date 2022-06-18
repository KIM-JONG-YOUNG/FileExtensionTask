package edu.jong.server.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.jong.server.enums.FixedExtensionState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@Table(name = "tb_fixed_extension")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedExtensionEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false, length = 20, unique = true)
	private String extension;

	@Convert(converter = FixedExtensionState.AttributeConverterImpl.class)
	@Column(nullable = false)
	private FixedExtensionState state;

	@Builder
	public FixedExtensionEntity(String extension, FixedExtensionState state) {
		super();
		this.extension = extension;
		this.state = state;
	}
	
	public FixedExtensionEntity check() {
		this.state = FixedExtensionState.CHECKED;
		return this;
	}
	
	public FixedExtensionEntity uncheck() {
		this.state = FixedExtensionState.UNCHECKED;
		return this;
	}
	
}
