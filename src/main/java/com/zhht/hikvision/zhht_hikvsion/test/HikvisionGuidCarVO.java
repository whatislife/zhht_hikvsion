package com.zhht.hikvision.zhht_hikvsion.test;

import java.io.Serializable;

public class HikvisionGuidCarVO implements Serializable {

	private static final long serialVersionUID = 2758796946056174737L;

	private java.util.Date parkingTime;// 停车时间
	private String plateNumber;// 车牌号
	private String parkName;// 车场名称
	private String spaceNo;// 车位编号
	private String floorName;//楼层

	public java.util.Date getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(java.util.Date parkingTime) {
		this.parkingTime = parkingTime;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
}
