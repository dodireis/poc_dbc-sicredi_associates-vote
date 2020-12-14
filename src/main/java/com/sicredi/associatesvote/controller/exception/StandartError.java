package com.sicredi.associatesvote.controller.exception;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class StandartError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private LocalDateTime timestamp;

	public StandartError(Integer status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), 
				ZoneId.of("America/Sao_Paulo"));
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}