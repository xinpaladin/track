package cn.xjtu.track.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xjtu.track.common.Result;
import cn.xjtu.track.dao.DataItemMapper;
import cn.xjtu.track.dao.DataOriginMapper;
import cn.xjtu.track.entity.DataItem;
import cn.xjtu.track.entity.DataOrigin;
import cn.xjtu.track.service.DataItemService;
import cn.xjtu.track.service.DataOriginSerivice;

@Service
public class DataOriginServiceImpl implements DataOriginSerivice {

	@Resource
	private DataItemMapper dataItemMapper;

	@Autowired
	private DataOriginMapper dataOriginMapper;

	@Autowired
	private DataItemService dataItemService;

	
	public Result bathInsertData(String path) {
		File file = new File(path);
		File[] fileList = file.listFiles();
		System.out.println("该目录下对象的个数" + fileList.length);

		// 循环读取每个文件
		BufferedReader br = null;
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				DataItem item = new DataItem();
				item.setType(dataItemService.getType(fileList[i].getName()));
				item.setFileName(fileList[i].getName());
				item.setDriverName("阴皓");
				try {
					LinkedList<String> datas = new LinkedList<String>();
					br = new BufferedReader(new FileReader(fileList[0]));
					String line = "";
					while ((line = br.readLine()) != null) {
						if(line.length()>100){
							datas.add(line);
						}
					
					}
					// 轨迹数据评价
					// String evalution = datas.getLast();
					// item = dataItemService.initDataItem(item, evalution);

					// 将item存入数据库
					dataItemMapper.insert(item);
					// 删除评价数据
					// datas.removeLast();
					// 遍历
					Iterator<String> iter = datas.iterator();
					
					while (iter.hasNext()) {
						String data = iter.next();
						DataOrigin dataOrigin = initDataOrigin(data);
						
						if(dataOrigin == null){
							continue;
						}
						
						dataOrigin.setDataItemId(item.getId());
						dataOriginMapper.insert(dataOrigin);
					}
				}catch (IOException e) {
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

	/**初始化原始数据类*/
	public DataOrigin initDataOrigin(String data) {
		String[] datas = data.split("  ");
		
		/** 开头或结尾数据接收不完全 */
		if(datas.length!=48){
			return null;
		}
		DataOrigin dataOrigin = new DataOrigin();

		dataOrigin.setSynWord1(datas[0]);
		dataOrigin.setSynWord2(datas[1]);
		dataOrigin.setFrameNum(datas[2]);
		dataOrigin.setState(datas[3]);
		dataOrigin.setDefWord1(datas[4]);
		dataOrigin.setDefWord2(datas[5]);
		dataOrigin.setLongitude(datas[6] + "-" + datas[7] + "-" + datas[8]
				+ "-" + datas[9]);
		dataOrigin.setLatitude(datas[10] + "-" + datas[11] + "-" + datas[12]
				+ "-" + datas[13]);
		dataOrigin.setHeight(datas[14] + "-" + datas[15]);
		dataOrigin.setVelE(datas[16] + "-" + datas[17]);
		dataOrigin.setVelN(datas[18] + "-" + datas[19]);
		dataOrigin.setVelU(datas[20] + "-" + datas[21]);
		dataOrigin.setRoll(datas[22] + "-" + datas[23]);
		dataOrigin.setPitch(datas[24] + "-" + datas[25]);
		dataOrigin.setCourse(datas[26] + "-" + datas[27]);
		dataOrigin.setAliTime(datas[28] + "-" + datas[29]);
		dataOrigin.setAliStateWord(datas[30]);
		dataOrigin.setLogAcce(datas[31] + "-" + datas[32]);
		dataOrigin.setLateralAcce(datas[33] + "-" + datas[34]);
		dataOrigin.setNorAcce(datas[35] + "-" + datas[36]);
		dataOrigin.setTimeYear(datas[37]);
		dataOrigin.setTimeMonth(datas[38]);
		dataOrigin.setTimeDay(datas[39]);
		dataOrigin.setTimeHour(datas[40]);
		dataOrigin.setTimeMinute(datas[41]);
		dataOrigin.setTimeSecond(datas[42] + "-" + datas[43]);
		dataOrigin.setEffiWord(datas[44]);
		dataOrigin.setMagVir(datas[45] + "-" + datas[46]);
		dataOrigin.setCheckSum(datas[47]);
		return dataOrigin;
	}
}
