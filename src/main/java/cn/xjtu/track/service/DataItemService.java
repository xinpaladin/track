package cn.xjtu.track.service;

import java.util.List;

import net.sf.json.JSONArray;
import cn.xjtu.track.common.GridBean;
import cn.xjtu.track.common.Result;
import cn.xjtu.track.entity.DataItem;

public interface DataItemService {

	/**
	 * @author zhangxin
	 * @param String
	 *            从文件包中获取数据列表 添加数据（item和 detail）
	 */
	public Result insertData(String path);

	/**
	 * @author zhangxin
	 * @param DataItem
	 *            获取所有的item的数据
	 * 
	 */
	public JSONArray getAllItemList();

	/**
	 * @author zhangxin
	 * @param DataItem
	 *            添加DataItem数据
	 * 
	 */
	public Long insertDataItem(DataItem item);

	/**
	 * @author zhangxin
	 * @param DataItem
	 * @return delete DataItem 删除Item数据
	 */
	public int deleteDataItem(DataItem item);

	/**
	 * @author zhangxin
	 * @param DataItem
	 * @return delete DataItem 删除Item数据
	 */
	public int deleteDataItemById(Long id);

	/**
	 * @author zhangxin
	 * @param DataItem
	 * @return update DataItem 更新Item数据
	 */
	public int updateDataItem(DataItem item);

	/**
	 * @author zhangxin
	 * @param DataItem
	 * @return select DataItem 根据DataItem的某些属性 查询itemList
	 */
	public List<DataItem> getDataItem(DataItem DataItem);

	/** 根据文件名来确定行驶动作 */
	public Integer getType(String fileName);

	/** 获取分页数据 */
	public abstract GridBean getOnePageItemList(DataItem item ,int page, int rows);
	
	/** 获取数据数量*/
	public Long getItemCount(DataItem record);

}
