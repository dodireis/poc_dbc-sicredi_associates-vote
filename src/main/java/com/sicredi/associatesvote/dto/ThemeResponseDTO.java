package com.sicredi.associatesvote.dto;

public class ThemeResponseDTO {

	private String description;
	private String status;
	private String result;
	private Long positiveVotesAmount;
	private Long negativeVotesAmount;

	public ThemeResponseDTO() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getPositiveVotesAmount() {
		return positiveVotesAmount;
	}

	public void setPositiveVotesAmount(Long positiveVotesAmount) {
		this.positiveVotesAmount = positiveVotesAmount;
	}

	public Long getNegativeVotesAmount() {
		return negativeVotesAmount;
	}

	public void setNegativeVotesAmount(Long negativeVotesAmount) {
		this.negativeVotesAmount = negativeVotesAmount;
	}

}
