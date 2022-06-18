package edu.jong.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@Table(name = "tb_custom_extension")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomExtensionEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false, length = 20, unique = true)
	private String extension;

	@Builder
	public CustomExtensionEntity(String extension) {
		super();
		this.extension = extension;
	}
}
