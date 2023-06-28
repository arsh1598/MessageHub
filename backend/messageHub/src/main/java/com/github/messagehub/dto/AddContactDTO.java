package com.github.messagehub.dto;

import javax.validation.constraints.Size;

public class AddContactDTO {

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Size(min = 10, max = 10)
	private String phone;
}
