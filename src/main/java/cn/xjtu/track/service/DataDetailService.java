package cn.xjtu.track.service;

import cn.xjtu.track.common.Result;
import cn.xjtu.track.entity.DataDetail;

public interface DataDetailService {
	/** 初始化DataDetail数据*/
	public DataDetail initDataDetail(String data);
	
	public Result tranDetail(Long dataItemId);

}
