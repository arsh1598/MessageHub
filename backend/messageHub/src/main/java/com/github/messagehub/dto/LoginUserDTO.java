package com.github.messagehub.dto;

import javax.validation.constraints.Size;

public class LoginUserDTO {

	@Size(min = 10, max = 10)
	private String phone;

	@Size(min = 8)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
