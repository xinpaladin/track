package cn.xjtu.track.dao;

import java.util.List;

import cn.xjtu.track.entity.DataDetail;

public interface DataDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDetail record);

    int insertSelective(DataDetail record);

    DataDetail selectByPrimaryKey(Long id);
    
    List<DataDetail> selectByItemId(Long dataItemId);

    int updateByPrimaryKeySelective(DataDetail record);

    int updateByPrimaryKey(DataDetail record);
}