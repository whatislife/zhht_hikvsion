package com.zhht.hikvision.zhht_hikvsion.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zhht.hikvision.zhht_hikvsion.util.TokenGenerateUtil;

public class HikvisionCon {
	
	/*private static final String url = "http://10.26.235.240:81/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
	public static final String name = "37d3136b";//此处替换成申请的appkey
	public static final String password = "db13b69af7d54ff787748c50812e97dd";//此处替换成申请的secret
*/
	
	private static final String url = "http://47.94.46.162:83/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
	public static final String name = "b5568a87";//此处替换成申请的appkey
	public static final String password = "0a545055c2574e0695558e2e41f813e4";//此处替换成申请的secret
	
	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNo", 1);
		param.put("pageSize", 5);
		param.put("parkingSyscode", "");// 停车场 indexCode,为空则查询所有
		// 停车场空余车位数（根据楼层分组）
		param.put("floorSyscode", "");// 楼层
		// indexCode,为空则查询所有楼层空余车位数（根据楼层分组）
		Date time = new Date();
		String uri = url + "pms/getParkingSpaceLeftNum?";
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			uri += entry.getKey() + "=" + entry.getValue() + "&";
		}
		uri += "appkey=" + name + "&time=" + time.getTime();
		String token = TokenGenerateUtil.buildToken(uri, new String(JSON.toJSONString(param)), password);
		uri += "&token=" + token;
		//String result = HttpUtils.doGetSSL(uri, new HashMap<String, Object>());
	}

}
