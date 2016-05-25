package cn.xjtu.track.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.xjtu.track.common.GridBean;
import cn.xjtu.track.common.Result;
import cn.xjtu.track.common.Type.LocusType;
import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.dao.DataItemMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.entity.DataItem;
import cn.xjtu.track.service.DataDetailService;
import cn.xjtu.track.service.DataItemService;

@Component
public class DataItemServiceImpl implements DataItemService {

	@Resource
	private DataDetailMapper dataDetailMapper;

	@Resource
	private DataItemMapper dataItemMapper;
	@Autowired
	private DataDetailService dataDetailService;

	@Transactional
	public Result insertData(String path) {

		File file = new File(path);
		File[] fileList = file.listFiles();
		System.out.println("该目录下对象的个数" + fileList.length);

		// 循环读取每个文件
		BufferedReader br = null;
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				DataItem item = new DataItem();
				item.setType(getType(fileList[i].getName()));
				item.setFileName(fileList[i].getName());

				try {
					LinkedList<String> datas = new LinkedList<String>();
					br = new BufferedReader(new FileReader(fileList[0]));
					String line = "";
					while ((line = br.readLine()) != null) {
						datas.add(line);
					}
					// 轨迹数据评价
					String evalution = datas.getLast();
					item = initDataItem(item, evalution);

					// 将item存入数据库
					dataItemMapper.insert(item);
					// 删除评价数据
					datas.removeLast();
					// 遍历
					Iterator<String> iter = datas.iterator();
					while (iter.hasNext()) {
						String data = iter.next();
						DataDetail detail = dataDetailService.initDataDetail(data);
						detail.setDataItemId(item.getId());
						dataDetailMapper.insertSelective(detail);
					}
				} catch (IOException e) {
					System.out.println("IO异常"+e.getMessage());
				
					return new Result(false, "未找到文件");
				} catch (Exception e) {
					//插入异常
					System.out.println("数据库操作异常："+e.getMessage());
					return new Result(false, "数据库操作异常");
				}finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException e) {
						System.out.println("bufferReader 关闭异常："+e.getMessage());
					}
				}
			}
		}
		
		return new Result(true, "");
	}

	// 根据读取评价数据初始化DataItem
	public DataItem initDataItem(DataItem item, String evalution) {
		String[] evals = evalution.split(" ");
		// IsAvailable<<" "<<Speed <<" "<<Locus <<" "<<Comfort<<" "<<Total;
		if (evals[0].equals("0")) {
			item.setIsAvailable(true);// evals[0]
		} else {
			item.setIsAvailable(false);
		}
		item.setSpeed(evals[1]);
		item.setLocus(evals[2]);
		item.setComfortable(evals[3]);
		item.setOverallEval(evals[4]);
		return item;
	}

	// 判断文件的轨迹类型
	public Integer getType(String fileName) {

		if (fileName.contains("直行")) {
			return LocusType.Straight.ordinal();
		} else if (fileName.contains("左换道")) {
			return LocusType.LeftLaneChange.ordinal();
		} else if (fileName.contains("右换道")) {
			return LocusType.RightLaneChange.ordinal();
		} else if (fileName.contains("左转")) {
			return LocusType.TurnLeft.ordinal();
		} else if (fileName.contains("右转")) {
			return LocusType.TurnRight.ordinal();
		} else if (fileName.contains("左掉头")) {
			return LocusType.TurnAround.ordinal();
		}
		return null;
	}

	@Override
	public JSONArray getAllItemList() {
		
		return null;
	}

	@Override
	public Long insertDataItem(DataItem item) {
		try {
			dataItemMapper.insert(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item.getId();
	}

	@Override
	public int deleteDataItem(DataItem item) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public int updateDataItem(DataItem item) {
		int result = 1;
		try {
			dataItemMapper.updateByPrimaryKeySelective(item);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	
	@Override
	public List<DataItem> getDataItem(DataItem dataItem) {
		List<DataItem> items = null;
		try {
			items = dataItemMapper.selectByDataItem(dataItem);
		} catch (Exception e) {
			return null;
		}

		return items;
	}

	@Override
	public int deleteDataItemById(Long id) {
		int result = 1;
		try {
			dataItemMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	@Override
	public GridBean getOnePageItemList(DataItem item , int page, int rows) {
		PageHelper.startPage(page, rows);
		List<DataItem> items = dataItemMapper.selectByDataItem(item);
		int totalpage = ((Page<?>)items).getPages();
		Long totalNum = ((Page<?>)items).getTotal();
		
		return new GridBean(page, totalpage, totalNum.intValue(), items);
	}

	@Override
	public Long getItemCount(DataItem record) {
		Long count = 0l;
		try {
			count = dataItemMapper.getItemCount(record);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
}
