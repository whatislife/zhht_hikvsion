package com.zhht.hikvision.zhht_hikvsion.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import com.alibaba.fastjson.JSON;
import com.zhht.hikvision.zhht_hikvsion.util.JavaHttpUtil;
import com.zhht.hikvision.zhht_hikvsion.util.TokenGenerateUtil;
/**
 * 
* @ClassName: HikvsionControlC  
* <p>Description: C端提供的代码 自己的请求方式  </p>
* @author 宋建 songjian@zhihuihutong.com 
* @date 2019年8月14日 下午3:55:13  
*
 */
public class HikvsionControlC {
	
//	private static final String HOST = "http://10.26.235.240:81/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
//	public static final String APPKEY = "37d3136b";//此处替换成申请的appkey
//	public static final String SECRET = "db13b69af7d54ff787748c50812e97dd";//此处替换成申请的secret

	private static final String HOST = "http://47.94.46.162:83/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
	public static final String APPKEY = "b5568a87";//此处替换成申请的appkey
	public static final String SECRET = "0a545055c2574e0695558e2e41f813e4";//此处替换成申请的secret

	public static void main(String[] args) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNo", 1);
		param.put("pageSize", 5);
		param.put("parkingSyscode", "");// 停车场 indexCode,为空则查询所有
		// 停车场空余车位数（根据楼层分组）
		param.put("floorSyscode", "");// 楼层
		// indexCode,为空则查询所有楼层空余车位数（根据楼层分组）
		
		String method1 = "pms/getParkingSpaceLeftNum";
		String response = JavaHttpUtil.doGet(HOST, method1, param, APPKEY, SECRET);
		System.out.println("返回的数据结果是："+response);
		
		
	}
}
