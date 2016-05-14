package cn.xjtu.track.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;

import cn.xjtu.track.common.GridBean;
import cn.xjtu.track.common.Result;
import cn.xjtu.track.common.Type.LocusType;
import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.dao.DataItemMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.entity.DataItem;
import cn.xjtu.track.service.DataItemService;

@Component
public class DataItemServiceImpl implements DataItemService {

	@Resource
	private DataDetailMapper dataDetailMapper;

	@Resource
	private DataItemMapper dataItemMapper;

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
						DataDetail detail = initDataDetail(data);
						detail.setDataItemId(item.getId());
						dataDetailMapper.insertSelective(detail);
					}
				} catch (IOException e) {
					System.out.println("未找到文件");
					e.printStackTrace();
					return new Result(false, "未找到文件");
				} finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		try {
			if (br != null)
				br.close();
		} catch (IOException e) {
			e.printStackTrace();
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

	// 初始化DataDetail数据
	public DataDetail initDataDetail(String data) {
		DataDetail detail = new DataDetail();
		String[] datas = data.split("  ");

		detail.setLongitude(Double.parseDouble(datas[6]));// 经度
		detail.setLatitude(Double.parseDouble(datas[7]));// 纬度
		detail.setHeight(Integer.parseInt(datas[8]));// 高度

		detail.setVelE(Double.parseDouble(datas[9]));// 东向速度
		detail.setVelN(Double.parseDouble(datas[10]));// 北向速度
		detail.setVelU(Double.parseDouble(datas[11]));// 天向速度

		detail.setRoll(Double.parseDouble(datas[12]));// 横滚角
		detail.setPitch(Double.parseDouble(datas[13]));// 俯仰角
		detail.setCourse(Double.parseDouble(datas[14]));// 惯导航向角

		detail.setLogAcce(Double.parseDouble(datas[17]));// 纵向加速度
		detail.setLateralAcce(Double.parseDouble(datas[18]));// 横向加速度
		detail.setNorAcce(Double.parseDouble(datas[19]));// 法向加速度

		detail.setTimeYear(Integer.parseInt(datas[20]));// 北京时间年
		detail.setTimeMonth(Integer.parseInt(datas[21]));// 北京时间月
		detail.setTimeDay(Integer.parseInt(datas[22]));// 北京时间日
		detail.setTimeHour(Integer.parseInt(datas[23]));// 北京时间时
		detail.setTimeMinute(Integer.parseInt(datas[24]));// 北京时间分
		detail.setTimeSecond(Double.parseDouble(datas[25]));// 北京时间秒
		return detail;

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
	public GridBean getOnePageDataItemList(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
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
}
