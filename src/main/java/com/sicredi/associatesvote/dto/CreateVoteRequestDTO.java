package com.sicredi.associatesvote.dto;

import javax.validation.constraints.NotNull;

public class CreateVoteRequestDTO {

	@NotNull
	private Long voteSessionId;

	@NotNull
	private String cpf;

	@NotNull
	private String vote;

	public CreateVoteRequestDTO() {
	}

	public Long getVoteSessionId() {
		return voteSessionId;
	}

	public void setVoteSessionId(Long voteSessionId) {
		this.voteSessionId = voteSessionId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

}
