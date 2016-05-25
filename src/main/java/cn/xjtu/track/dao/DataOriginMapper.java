package cn.xjtu.track.dao;

import java.util.LinkedList;

import cn.xjtu.track.entity.DataOrigin;

public interface DataOriginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataOrigin record);

    int insertSelective(DataOrigin record);

    DataOrigin selectByPrimaryKey(Long id);
    
    LinkedList<DataOrigin> selectByItemId(Long dataItemId);

    int updateByPrimaryKeySelective(DataOrigin record);

    int updateByPrimaryKey(DataOrigin record);
}