package cn.xjtu.track.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.service.DataDetailService;

@Component
public class DataDetailServiceImpl  implements DataDetailService {

	@Resource
	private DataDetailMapper dataDetailMappeer;
	@Transactional
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
}
