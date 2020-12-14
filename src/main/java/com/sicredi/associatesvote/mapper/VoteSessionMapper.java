package com.sicredi.associatesvote.mapper;

import org.springframework.stereotype.Component;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.dto.CreateVoteSessionRequestDTO;

@Component
public class VoteSessionMapper {

	public VoteSession map(CreateVoteSessionRequestDTO dto, Theme theme) {
		VoteSession entity = new VoteSession();
		entity.setTheme(theme);
		entity.setVotingOpenTime(dto.getVotingOpenTime());
		entity.setVotingCloseTime(dto.getVotingCloseTime());

		return entity;
	}

}
