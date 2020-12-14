package com.sicredi.associatesvote.domain;

public enum ThemeStatusEnum {

	CLOSED(0, "CLOSED"),
	OPENED(1, "OPENED");

	private Integer id;
	private String status;
	
	private ThemeStatusEnum(Integer id, String status) {
		this.id = id;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public static ThemeStatusEnum toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (ThemeStatusEnum x : ThemeStatusEnum.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + id);
	}
}
