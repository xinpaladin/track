package cn.xjtu.track.dao;

import java.util.List;

import cn.xjtu.track.entity.DataItem;

public interface DataItemMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(DataItem record);

    DataItem selectByPrimaryKey(Long id);

    List<DataItem> selectByDataItem(DataItem record);

    int updateByPrimaryKeySelective(DataItem record);

    int updateByPrimaryKey(DataItem record);
    
    long getItemCount(DataItem record);
}