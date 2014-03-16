package com.bill99.base.service;

public interface IOrgAndUser {

	public Boolean validate(String userCode, String pwd);

	public String findUserList();

	public String findOrgList();

	public String findUser(String orgId);

}
