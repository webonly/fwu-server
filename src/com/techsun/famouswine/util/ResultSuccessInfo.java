package com.techsun.famouswine.util;

import java.util.HashMap;

/**
 * 
 * 
 * <p>@ClassName: ResultSuccessInfo.java</p> 
 * <p>
 * Description: ���سɹ���Ϣ
 * </p> 
 * @author Rocky 
 * @date 2015-7-24 ����3:24:52 
 * @version 1.0
 */
public class ResultSuccessInfo {
	/**
	 * ���ر�ʶ
	 */
	private String errorCode = "0";
	
	private String errorMessage = "";


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

	/***
	 * ���ض���
	 */
	private Object result;

	public ResultSuccessInfo() {

	}

	public ResultSuccessInfo(String code) {
		super();
		this.errorCode = code;
	}

	public ResultSuccessInfo(Object result) {
		super();
		this.result = result;
	}

	public ResultSuccessInfo(String errorCode,String errorMessage,  Object result) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.result = result;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		if (result == null) {
			this.result = new HashMap();
			return;
		}
		this.result = result;
	}

}
