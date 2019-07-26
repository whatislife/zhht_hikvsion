package com.zhht.hikvision.zhht_hikvsion.test;

import java.io.Serializable;

public class HikvisionData implements Serializable {

	private static final long serialVersionUID = -7001636395876373070L;
	private String errorCode;
	private String errorMessage;
	private HikvisionDataList data;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HikvisionDataList getData() {
		return data;
	}

	public void setData(HikvisionDataList data) {
		this.data = data;
	}

}
