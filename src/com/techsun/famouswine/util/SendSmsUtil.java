package com.techsun.famouswine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;











import com.alibaba.druid.support.logging.Log;

public class SendSmsUtil {
	private static final String CHARSET = "utf-8";
    private static InputStream ins;
    
	private static String Url = "http://sms.10690221.com:9011/hy/";
	//private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	public static void sendSms(String mobile, String crCode) {

		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset(CHARSET);
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=utf-8");
		String content = new String("您的验证码是：" + crCode  );
		String formatContent="";
		try {
			formatContent = URLEncoder.encode(content,"gbk");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*try {
			ins = method.getResponseBodyAsStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		/*BufferedReader br = new BufferedReader(newInputStreamReader(ins,CHARSET));
		StringBuffer sbf = new StringBuffer();
		String line = null;
		try {
			while ((line = br.readLine()) != null)
			{
			sbf.append(line);
			}
		} catch (IOException e2) {
			
			e2.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}*/


		
		NameValuePair[] data = { new NameValuePair("uid", "80506"),
				new NameValuePair("auth", "5969fb0c4b601e2711a8ac60a9336be0"),
				new NameValuePair("mobile", mobile),
				new NameValuePair("msg", formatContent ),
				new NameValuePair("expid", ""+0)};
		
		method.setRequestBody(data);
		 
		
		try {
			client.executeMethod(method);
			 String SubmitResult = method.getResponseBodyAsString();
			//BufferedReader br = new BufferedReader(newInputStreamReader(ins,CHARSET));
			//StringBuffer sbf = new StringBuffer();
			
			
			//System.out.println(SubmitResult);
			
/*			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
              
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);*/
			
			  String [] arr = SubmitResult.split(",");
			  
			  
            
			if ("0".equals(arr[0])) {
				System.out.println("短信提交成功");
			}
			/*if ("UNDELIVRD ".equals(arr[0])) {
				System.out.println("短信提交失败");
			}
			if ("EXPIRED".equals(arr[0])) {
				System.out.println("短信发送超时");
			}
			if ("REJECTD".equals(arr[0])) {
				System.out.println("短信被拒绝");
			}
			if ("MB:0066".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("MK:0001".equals(arr[0])) {
				System.out.println("欠费或停机");
			}
			if ("MK:0013".equals(arr[0])) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MK:0015".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("MK:0036".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("MK:0041".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("IB:0008".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("IB:0011".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("DB:0018".equals(arr[0])) {
				System.out.println("移动内部错误 不处理");
			}
			if ("DB:0140".equals(arr[0])) {
				System.out.println("用户未点播该业务");
			}
			if ("DB:0139".equals(arr[0])) {
				System.out.println("黑名单");
			}
			if ("MB:0241".equals(arr[0])) {
				System.out.println("移动内部错误 不处理 ");
			}

			if ("IB:0064".equals(arr[0])) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MK:0005".equals(arr[0])) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MK:0017".equals(arr[0])) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MK:0011".equals(arr[0])) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MK:0000".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MK:0004".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MI:0010".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}

			if ("MI:0013".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}

			if ("MI:0024".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}

			if ("MI:0029".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MI:0081".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("MI:0084".equals(code)) {
				System.out.println("移动内部错误 不处理 ");
			}
			if ("DB:0142 ".equals(code)) {
				System.out.println("超过下行上限 ");
			}
			if ("IB:0100".equals(code)) {
				System.out.println("重号过滤 ");
			}
			if ("MC:0001".equals(code)) {
				System.out.println("签名未报备 ");
			}*/
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}


}