package cn.xjtu.track.service;

import java.util.List;

import cn.xjtu.track.common.Result;
import cn.xjtu.track.entity.DataDetailBaidu;

public interface DataDetailBaiduService {

	public List<DataDetailBaidu> getDetailBaiduList(Long dataItemId);
	
	public Result tranDetailToBaidu(Long dataItemId);
}
