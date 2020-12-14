package com.sicredi.associatesvote.dto;

import javax.validation.constraints.NotNull;

public class CreateThemeRequestDTO {

	@NotNull
	private String description;

	public CreateThemeRequestDTO() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
