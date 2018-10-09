package com.moerlong.carloan.modular.app.utils;

import java.io.Serializable;

public class UserTokenInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private long expiredTime;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

}
