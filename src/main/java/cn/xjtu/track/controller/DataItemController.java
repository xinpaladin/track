package cn.xjtu.track.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xjtu.track.common.GridBean;
import cn.xjtu.track.common.Result;
import cn.xjtu.track.entity.DataItem;
import cn.xjtu.track.service.DataItemService;
import cn.xjtu.track.service.DataOriginSerivice;

@Controller
@RequestMapping("/item")
public class DataItemController {

	@Autowired
	private DataItemService dataItemService;

	@Autowired 
	private DataOriginSerivice dataOriginSerivice;
	
	public String getIndex() {
		return "";
	}

	@RequestMapping("/getItemList")
	@ResponseBody
	public JSONArray getItemList() {

		return dataItemService.getAllItemList();
	}

	@RequestMapping("/getItemCount")
	@ResponseBody
	public Long getItemCount(DataItem item) {
		return dataItemService.getItemCount(item);
	}
	
	@RequestMapping("/insertData")
	@ResponseBody
	public Result insertData(String path){
		return dataOriginSerivice.bathInsertData(path);
	}
	
	@RequestMapping("/getItemPage")
	@ResponseBody
	public GridBean getItemPage(int pageNum,int rows , DataItem item) {

		return dataItemService.getOnePageItemList(item, pageNum, rows);
	}
}
