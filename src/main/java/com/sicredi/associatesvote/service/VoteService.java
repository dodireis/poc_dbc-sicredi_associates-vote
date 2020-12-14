package com.sicredi.associatesvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.associatesvote.domain.Vote;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.dto.CreateVoteRequestDTO;
import com.sicredi.associatesvote.mapper.VoteMapper;
import com.sicredi.associatesvote.repository.VoteRepository;
import com.sicredi.associatesvote.service.exception.ObjectNotFoundException;
import com.sicredi.associatesvote.util.AssociateInfo;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private VoteSessionService voteSessionService;

	@Autowired
	private VoteMapper voteMapper;

	@Autowired
	private AssociateInfo associateInfo;

	public Long create(CreateVoteRequestDTO dto) {
		VoteSession voteSession = voteSessionService.findById(dto.getVoteSessionId());

		voteSessionService.validateVoteSessionTime(voteSession);
		validateAssociateVote(voteSession, dto);

		Vote vote = voteMapper.map(dto, voteSession);
		vote = voteRepository.save(vote);

		return vote.getId();
	}

	private void validateAssociateVote(VoteSession voteSession, CreateVoteRequestDTO dto) {
		String cpf = removeSpecialCharsAndSpacesFromCPF(dto.getCpf());

		if (voteRepository.findByVoteSessionAndAssociateDocument(voteSession, cpf).isPresent()) {
			throw new IllegalArgumentException("Associate already vote in this session.");
		}

		String status = associateInfo.getAssociate(cpf);

		if (status.equals("")) {
			throw new ObjectNotFoundException(
					"Associate document 'CPF' wasn't found or we had some issue while searching"
							+ ". Confirm the document number and try again.");
		}

		if (!status.equalsIgnoreCase("ABLE_TO_VOTE")) {
			throw new IllegalArgumentException("Associate cannot vote in this session.");
		}

		dto.setCpf(cpf);
	}

	private String removeSpecialCharsAndSpacesFromCPF(String cpf) {
		return cpf.replaceAll("\\W", "").trim();
	}
}
