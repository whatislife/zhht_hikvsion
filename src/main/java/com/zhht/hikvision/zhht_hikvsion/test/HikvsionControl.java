package com.zhht.hikvision.zhht_hikvsion.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import com.alibaba.fastjson.JSON;
import com.zhht.hikvision.zhht_hikvsion.util.HttpClientUtil;
import com.zhht.hikvision.zhht_hikvsion.util.JavaHttpUtil;

public class HikvsionControl {
	
	private static final String HOST = "http://47.94.46.162:83/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
	public static final String APPKEY = "b5568a87";//此处替换成申请的appkey
	public static final String SECRET = "0a545055c2574e0695558e2e41f813e4";//此处替换成申请的secret
	
	public static void main(String[] args) {
		for(String arg:args){
			System.out.println("请求参数："+arg);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plateNo", args[0]);
		//map.put("parkingSyscode", "");
		String method1 = "pms/fetchParkingRecordFuzzy";
		String response = JavaHttpUtil.doGet(HOST, method1, map, APPKEY, SECRET);
		//String response = HttpClientUtil.doPost(HOST, method1, map, APPKEY, SECRET);
		System.out.println("返回参数：" + response);
		
		
/*		HikvisionData indata  = new HikvisionData();
		HikvisionDataList indatalist = new HikvisionDataList();
		List<HikvisionGuidCarVO> inlist = new ArrayList<HikvisionGuidCarVO>();
		HikvisionGuidCarVO vo1 = new HikvisionGuidCarVO();
		vo1.setParkingTime(new Date());
		vo1.setParkName("北京");
		vo1.setPlateNumber("京A78945");
		vo1.setSpaceNo("C-1");
		vo1.setFloorName("二层");
		HikvisionGuidCarVO vo2 = new HikvisionGuidCarVO();
		vo2.setParkingTime(new Date());
		vo2.setParkName("北京");
		vo2.setPlateNumber("京A78945");
		vo2.setSpaceNo("C-2");
		vo2.setFloorName("1层");
		inlist.add(vo1);
		inlist.add(vo2);
		indatalist.setList(inlist);
		indata.setData(indatalist);
		indata.setErrorCode("0");
		indata.setErrorMessage("操作成功");
		
		response = JSON.toJSONString(indata);
		System.out.println("制造返回数据："+response);*/
		
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
