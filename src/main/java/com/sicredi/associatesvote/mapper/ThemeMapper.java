package com.sicredi.associatesvote.mapper;

import org.springframework.stereotype.Component;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.dto.ThemeResponseDTO;

@Component
public class ThemeMapper {

	public ThemeResponseDTO map(Theme entity) {
		ThemeResponseDTO dto = new ThemeResponseDTO();
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.getStatus().getStatus());
		dto.setResult(entity.getResult());
		dto.setPositiveVotesAmount(entity.getPositiveVotesAmount());
		dto.setNegativeVotesAmount(entity.getNegativeVotesAmount());

		return dto;
	}

}
