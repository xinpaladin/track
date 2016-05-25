package cn.xjtu.track.service;

import cn.xjtu.track.common.Result;

public interface DataOriginSerivice {

	/** 从文件中读取数据*/
	public Result bathInsertData(String path);
}
