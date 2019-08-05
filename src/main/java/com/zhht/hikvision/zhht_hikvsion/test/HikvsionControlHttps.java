package com.zhht.hikvision.zhht_hikvsion.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zhht.hikvision.zhht_hikvsion.ssl.HttpUtil;

public class HikvsionControlHttps {
	
	private static final String HOST = "http://10.26.235.240:81/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
	public static final String APPKEY = "37d3136b";//此处替换成申请的appkey
	public static final String SECRET = "db13b69af7d54ff787748c50812e97dd";//此处替换成申请的secret
	
	public static void main(String[] args) {
		for(String arg:args){
			System.out.println("请求参数："+arg);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plateNo", args[0]);
		//map.put("parkingSyscode", "");
		String method1 = "pms/fetchParkingRecordFuzzy";
		String response = HttpUtil.doGet8700(HOST, method1, map, APPKEY, SECRET);
		System.out.println("返回参数：" + response);
		HikvisionData data = JSON.parseObject(response,HikvisionData.class);
		if(data!=null){
			if(data.getErrorCode().equals("0")){
				HikvisionDataList list = data.getData();
				System.out.println("list:"+JSON.toJSON(list));
				if(list!=null){
					List<HikvisionGuidCarVO> voList = list.getList();
					System.out.println("voList:"+JSON.toJSON(voList));
				}
			}else{
				System.out.println("异常信息是："+data.getErrorMessage());
			}
		}
	}
}
