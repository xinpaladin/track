package cn.xjtu.track.serviceImpl;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xjtu.track.common.Result;
import cn.xjtu.track.dao.DataDetailBaiduMapper;
import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.entity.DataDetailBaidu;
import cn.xjtu.track.service.DataDetailBaiduService;


public class DataDetailBaiduServiceImpl implements DataDetailBaiduService {

	@Autowired
	private DataDetailMapper dataDetailMapper;

	@Autowired
	private DataDetailBaiduMapper detailBaiduMapper;
	
	@Override
	public List<DataDetailBaidu> getDetailBaiduList(Long dataItemId) {
		List<DataDetailBaidu> detailBaidus = null;
		try {
			detailBaidus = detailBaiduMapper.selectByItemId(dataItemId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return detailBaidus;
	}

	@Override
	public Result tranDetailToBaidu(Long dataItemId) {
		List<DataDetail> details = dataDetailMapper.selectByItemId(dataItemId);
		int length = details.size();
		CloseableHttpClient client = new DefaultHttpClient();
		HttpGet get = null;
		HttpResponse response = null;
		// 经纬度转换
		// 每100个数据请求一会服务，不足一百算1次
		for (int i = 0; i < (length / 100 + 1); i++) {
			String data = "";
			if (i == length / 100) {
				for (int j = 0; j < length - i * 100; j++) {
					data += details.get(i * 100 + j).getLatitude() + ","
							+ details.get(i * 100 + j).getLatitude() + ";";
				}
			} else {
				for (int j = 0; j < 100; j++) {
					data += details.get(i * 100 + j).getLatitude() + ","
							+ details.get(i * 100 + j).getLatitude() + ";";
				}
			}
			// 百度坐标转换服务
			final String url = "http://api.map.baidu.com/geoconv/v1/?coords="
					+ data.substring(0, data.length() - 1)
					+ "&ak=Y4luUSoc9rtwB91M0wmFnQk8ZRc9XkHb&output=json";
			
			get = new HttpGet(url);
			
			try {
				response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == 200) {
					// 请求和响应都成功了
					HttpEntity entity = response.getEntity();// 调用getEntity()方法获取到一个HttpEntity实例
					String transData = EntityUtils.toString(entity, "utf-8");// 用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,防止服务器返回的数据带有中文,所以在转换的时候将字符集指定成utf-8就可以了
					
					JSONObject jo = JSONObject.fromObject(transData);
					JSONArray dataArray = jo.getJSONArray("result");
					for(int j= 0 ;j<dataArray.size();j++){
						JSONObject obj = dataArray.getJSONObject(j);
						DataDetailBaidu detailBaidu = new DataDetailBaidu();
						detailBaidu.setDataItemId(dataItemId);
						detailBaidu.setLongitude(obj.getDouble("x"));
						detailBaidu.setLatitude(obj.getDouble("y"));
						detailBaiduMapper.insert(detailBaidu);
					}
				}
			} catch (ClientProtocolException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} 
		}
		
		try {
			client.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return new Result(true, "获取转换的百度数据成功");
	}
}