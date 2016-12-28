package com.techsun.famouswine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * <p>@ClassName: ResultInfoUtil.java</p> 
 * <p>
 * Description: 
 * </p> 
 * @author Rocky
 * @date 2015-7-24 ����3:24:52 
 * @version 1.0
 */
public class ResultInfoUtil {

	private static final String SUCCESS = "0";
	public static final String ERROR = "400";

	private static Map<String, String> errorMap = new HashMap<String, String>();
	static {
		errorMap.put(SUCCESS, "�����ɹ�");
		errorMap.put(ERROR, "����ʧ��!");
	}

	/***
	 * ������ȷ ���
	 * 
	 * @param obj ���ض���
	 * @return
	 */
	public static ResultSuccessInfo setSuccessInfo(Object obj) {
		ResultSuccessInfo resultSuccessInfo = new ResultSuccessInfo();
		resultSuccessInfo.setErrorCode(SUCCESS);
		resultSuccessInfo.setResult(obj);
		return resultSuccessInfo;
	}
	
	/***
	 * ���ô��� ���
	 * @param type ����
	 * @return
	 */
	public static ResultErrorInfo setErrorInfo(String type) {
		ResultErrorInfo resultInfo = new ResultErrorInfo();
		resultInfo.setErrorMessage(ResultInfoUtil.errorMap.get(type));
		resultInfo.setErrorCode(type);
		return resultInfo;
	}

}
