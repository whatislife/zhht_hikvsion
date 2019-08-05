package com.zhht.hikvision.zhht_hikvsion.ssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.zhht.hikvision.zhht_hikvsion.util.TokenGenerateUtil;



public class HttpUtil {

	private static TrustManager myX509TrustManager = new X509TrustManager() {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 
		}

		public X509Certificate[] getAcceptedIssuers() { 
			return null;
		}

	};
	
	public static String doGet8700(String preUrl, String interfaceName, Map<String, Object> paramMap, String appkey,
	        String secret) {
		StringBuffer sb = new StringBuffer("appkey=" + appkey + "&time=" + System.currentTimeMillis());
		// 遍历map
		if (paramMap != null) {
			for (String key : paramMap.keySet()) {
				sb.append("&" + key + "=" + paramMap.get(key));
			}
		}
		String token = TokenGenerateUtil.buildToken(preUrl + interfaceName + "?" + sb.toString(), null, secret);
		String urlStr = preUrl + interfaceName + "?" + sb.toString() + "&token=" + token;
		System.out.println("请求地址："+urlStr);
		String response = "";
		if(preUrl.indexOf("https")!=-1){//未找到
			response = sendHttpsGET(urlStr);
		}else{
			response = sendHttpGET(urlStr);
		}
		return response;
	}
	
	
	public static String sendHttpsPOST(String url, String data) {
		String result = null;
		OutputStream out=null;
		BufferedReader in=null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();
			//连接超时3秒
			httpsConn.setConnectTimeout(3000);
			//读取超时3秒
			httpsConn.setReadTimeout(3000);
			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			out = httpsConn.getOutputStream() ;
			 
			if (data != null)
				out.write(data.getBytes("UTF-8")); 
			out.flush();
			out.close();

			// 获取输入流
			in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(),"UTF-8"));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (KeyManagementException|NoSuchAlgorithmException|IOException e) {
//			log.error("httputil error:",e);
			System.out.println("httputil error:"+e);
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
//					log.error("httputil error:",e);
					System.out.println("httputil error:"+e);
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
//					log.error("httputil error:",e);
					System.out.println("httputil error:"+e);
				}
			}
		}

		return result;
	}
	
	public static String sendHttpsGET(String url) {
		String result = null;
		BufferedReader in =null;
		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("GET");
//			httpsConn.setDoOutput(true);
			httpsConn.setConnectTimeout(3000);
			httpsConn.setReadTimeout(3000);
			// 获取输入流
			in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(),"UTF-8"));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (Exception e) {
//			log.error("httputil error:",e);
			System.out.println("httputil error:"+e);
		}finally{
			try {
				if(in!=null)
				{
					in.close();
				}
			} catch (IOException e) {
//				log.error("httputil error:",e);
				System.out.println("httputil error:"+e);
			}
		}

		return result;
	}
	
	public static String sendHttpGET(String url) {
		String result = null;
		BufferedReader in=null;
		try {

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpURLConnection httpsConn = (HttpURLConnection) requestUrl
					.openConnection();


			// 加入数据
			httpsConn.setRequestMethod("GET");
//			httpsConn.setDoOutput(true);
			  

			// 获取输入流
			in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(),"UTF-8"));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (Exception e) {
//			log.error("httputil error:",e);
			System.out.println("httputil error:"+e);
		} finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
//					log.error("httputil error:",e);
					System.out.println("httputil error:"+e);
				}
			}
		}
		return result;
	}

	public static InputStream getInputStream(String path) throws IOException {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(path);
            if (url != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                // 设置连接网络的超时时间
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setDoInput(true);
                // 设置本次http请求使用get方式请求
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    // 从服务器获得一个输入流
                    inputStream = httpURLConnection.getInputStream();
                }
            }
        } catch (MalformedURLException e) {
//			log.error("httputil error:",e);
        	System.out.println("httputil error:"+e);
        }
 
        return inputStream;
    }
	
	public static String sendHttpPOST(String url, String data) {
		return sendHttpPOST(url,data,null);
	}
	
	public static String sendHttpPOST(String url, String data,String strJson) {
		String result = null;
		OutputStream out=null;
		BufferedReader in=null;
		try {

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpURLConnection httpsConn = (HttpURLConnection) requestUrl
					.openConnection();
			httpsConn.setDoOutput(true);
			httpsConn.setRequestMethod("POST");
			httpsConn.setRequestProperty("Accept-Charset", "utf-8");
			if(null==strJson){
				
				httpsConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			}else{
				httpsConn.setRequestProperty("Content-Type", strJson);
			}
			httpsConn.setConnectTimeout(10000);
			httpsConn.setReadTimeout(10000);
            
			out = httpsConn.getOutputStream() ;
			 
			if (data != null)
				out.write(data.getBytes("UTF-8")); 
			out.flush();
			out.close();

			// 获取输入流
			in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream(),"UTF-8"));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (IOException e) {
//			log.error("httputil error:",e);
			System.out.println("httputil error:"+e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
//					log.error("httputil error:",e);
					System.out.println("httputil error:"+e);
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
//					log.error("httputil error:",e);
					System.out.println("httputil error:"+e);
				}
			}
		}

		try {
			result=result==null?"":new String(result.getBytes("UTF-8"),"UTF-8");  
		} catch (UnsupportedEncodingException e) {
//			log.error("httputil error:",e);
			System.out.println("httputil error:"+e);
		}
		return result;
	}	
	
}
