package cn.xjtu.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xjtu.track.service.DataItemService;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/item")
public class DataItemController {

	@Autowired
	private DataItemService dataItemService;
	
	public String getIndex(){
		return "";
	} 
	@RequestMapping("/getItemList")
	@ResponseBody
	public JSONArray getItemList(){
		
		return dataItemService.getItemList();
	}
}
