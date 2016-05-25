package cn.xjtu.track.dao;

import java.util.List;

import cn.xjtu.track.entity.DataDetailBaidu;

public interface DataDetailBaiduMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDetailBaidu record);

    int insertSelective(DataDetailBaidu record);

    DataDetailBaidu selectByPrimaryKey(Long id);
    
    List<DataDetailBaidu> selectByItemId(Long dataItemId);

    int updateByPrimaryKeySelective(DataDetailBaidu record);

    int updateByPrimaryKey(DataDetailBaidu record);
}