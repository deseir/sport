package com.moerlong.carloan.modular.app.utils;



import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.persistence.model.UserVO;

import java.io.Serializable;

public class LoginModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private long expiredTime;

    private User user;
    private UserVO userVO;
    

    /**
	 * @return the userVO
	 */
	public UserVO getUserVO() {
		return userVO;
	}

	/**
	 * @param userVO the userVO to set
	 */
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
