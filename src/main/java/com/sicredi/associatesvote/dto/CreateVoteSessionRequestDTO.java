package com.sicredi.associatesvote.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class CreateVoteSessionRequestDTO {

	@NotNull
	private Long themeId;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime votingOpenTime;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime votingCloseTime;

	public CreateVoteSessionRequestDTO() {
	}

	public CreateVoteSessionRequestDTO(@NotNull Long themeId, @NotNull LocalDateTime votingOpenTime,
			@NotNull LocalDateTime votingCloseTime) {
		super();
		this.themeId = themeId;
		this.votingOpenTime = votingOpenTime;
		this.votingCloseTime = votingCloseTime;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public LocalDateTime getVotingOpenTime() {
		return votingOpenTime;
	}

	public void setVotingOpenTime(LocalDateTime votingOpenTime) {
		this.votingOpenTime = votingOpenTime;
	}

	public LocalDateTime getVotingCloseTime() {
		return votingCloseTime;
	}

	public void setVotingCloseTime(LocalDateTime votingCloseTime) {
		this.votingCloseTime = votingCloseTime;
	}

}
