package com.sicredi.associatesvote.mapper;

import org.springframework.stereotype.Component;

import com.sicredi.associatesvote.domain.Vote;
import com.sicredi.associatesvote.domain.VoteEnum;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.dto.CreateVoteRequestDTO;

@Component
public class VoteMapper {

	public Vote map(CreateVoteRequestDTO dto, VoteSession voteSession) {
		Vote vote = new Vote();
		vote.setVoteSession(voteSession);
		vote.setAssociateDocument(dto.getCpf());
		vote.setVote(VoteEnum.toEnum(dto.getVote().trim().toUpperCase()));

		return vote;
	}
	
}
