package cn.xjtu.track.serviceImpl;

import java.util.Iterator;
import java.util.LinkedList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.xjtu.track.common.Result;
import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.dao.DataOriginMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.entity.DataOrigin;
import cn.xjtu.track.service.DataDetailService;

@Component
public class DataDetailServiceImpl implements DataDetailService {

	@Resource
	private DataDetailMapper dataDetailMappeer;

	@Autowired
	private DataOriginMapper dataOriginMapper;

	public DataDetail initDataDetail(String data) {
		DataDetail detail = new DataDetail();
		String[] datas = data.split(" ");

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

	public Result tranDetail(Long dataItemId) {
		LinkedList<DataOrigin> origins = dataOriginMapper
				.selectByItemId(dataItemId);
		Iterator<DataOrigin> iter = origins.iterator();
		while (iter.hasNext()) {
			DataOrigin orgin = iter.next();
			DataDetail detail = tranOriginToDetail(orgin);
			try {
				dataDetailMappeer.insert(detail);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new Result(false, "数据转换错误");
			}

		}
		return new Result(true, "转换成功");
	}

	public DataDetail tranOriginToDetail(DataOrigin origin) {
		DataDetail detail = new DataDetail();
		detail.setId(origin.getId());
		// 经度
		detail.setLongitude(getLongLater(origin.getLongitude()));
		// 纬度
		detail.setLatitude(getLongLater(origin.getLatitude()));
		// 高度
		detail.setHeight(getHeight(origin.getHeight()));
		// 东向速度
		detail.setVelE(getVelocity(origin.getVelE()));
		// 北向速度
		detail.setVelN(getVelocity(origin.getVelN()));
		// 天向速度
		detail.setVelU(getVelocity(origin.getVelU()));
		// 横滚角
		detail.setRoll(getAngle(origin.getRoll()));
		// 俯仰角
		detail.setPitch(getAngle(origin.getPitch()));
		// 惯导航向角
		detail.setCourse(getAngle(origin.getCourse()));
		// 纵向加速度
		detail.setLogAcce(getAcceleration(origin.getLogAcce()));
		// 横向加速度
		detail.setLateralAcce(getAcceleration(origin.getLateralAcce()));
		// 法向加速度
		detail.setNorAcce(getAcceleration(origin.getNorAcce()));
		// 北京时间年
		detail.setTimeYear(Integer.parseInt(origin.getTimeYear(), 16) + 2000);
		// 北京时间月
		detail.setTimeMonth(Integer.parseInt(origin.getTimeMonth()));
		// 北京时间日
		detail.setTimeDay(Integer.parseInt(origin.getTimeDay()));
		// 北京时间时
		detail.setTimeHour(Integer.parseInt(origin.getTimeHour()));
		// 北京时间分
		detail.setTimeMinute(Integer.parseInt(origin.getTimeMinute()));
		// 北京时间秒
		detail.setTimeSecond(getDouble(origin.getTimeSecond(), 60, 16));

		detail.setDataItemId(origin.getDataItemId());
		return detail;
	}

	public String[] getStringList(String data) {
		return data.split("-");
	}

	/** 数据转换 */
	public Double getDouble(String dataOrigin, int LSB, double power) {
		Long dataDetail = 0l;
		String[] datas = getStringList(dataOrigin);

		for (int i = 0; i < datas.length; i++) {
			dataDetail += Long.valueOf(datas[i], 16) >> 8 * (i + 1);
		}
		return dataDetail * LSB / Math.pow(2.0, power);
	}

	/** 经纬度数据转换 */
	public Double getLongLater(String dataOrigin) {
		Double longiAndLater = getDouble(dataOrigin, 180, 31);
		if (longiAndLater > 180) {
			longiAndLater = longiAndLater - 360;
		}
		return longiAndLater;
	}

	/** 高度 */
	public Integer getHeight(String dataOrigin) {
		String[] datas = getStringList(dataOrigin);
		Integer height = 0;
		for (int i = 0; i < datas.length; i++) {
			height += Integer.valueOf(datas[i], 16) >> 8 * (i + 1);
		}
		if (height > 32768) {
			height = height - 360;
		}
		return height;
	}

	/** 东向速度 北向速度 天向速度 */
	public Double getVelocity(String dataOrigin) {
		Double velocity = getDouble(dataOrigin, 400, 15);
		if (velocity > 400) {
			velocity = velocity - 800;
		}
		return velocity;
	}

	/** 横滚角 俯仰角 航向角 */
	public Double getAngle(String dataOrigin) {
		Double angle = getDouble(dataOrigin, 180, 15);
		if (angle > 180) {
			angle = angle - 360;
		}
		return angle;
	}

	/** 纵向加速度 横向加速度 法向加速度 */
	public Double getAcceleration(String dataOrigin) {
		Double acceleration = getDouble(dataOrigin, 100, 15);
		if (acceleration > 100) {
			acceleration = acceleration - 200;
		}
		return acceleration;
	}

	// public Double getYear(String dataOrigin) {
	//
	// }
}
