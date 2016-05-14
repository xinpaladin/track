package cn.xjtu.track.dao;

import cn.xjtu.track.entity.DataDetail;

public interface DataDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDetail record);

    int insertSelective(DataDetail record);

    DataDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDetail record);

    int updateByPrimaryKey(DataDetail record);
}