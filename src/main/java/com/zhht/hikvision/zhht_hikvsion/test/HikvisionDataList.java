package com.zhht.hikvision.zhht_hikvsion.test;

import java.io.Serializable;
import java.util.List;

public class HikvisionDataList implements Serializable {
	private static final long serialVersionUID = -4590676400084603643L;
	private List<HikvisionGuidCarVO> list;

	public List<HikvisionGuidCarVO> getList() {
		return list;
	}

	public void setList(List<HikvisionGuidCarVO> list) {
		this.list = list;
	}
}
