package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类列表
 * @author 刘亮
 * @date 2019年2月25日 下午9:05:47
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	//方法一
	//解决返回json格式字符串出现乱码问题，设置MediaType
	@RequestMapping(value="/item/list",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemList(String callback) {
		CatResult catList = itemCatService.getItemList();
		//把pojo转化成字符串
		String json = JsonUtils.objectToJson(catList);
		//转化返回值
		String result = callback + "(" + json +");";
		return result;
	}
	
	//方法二（springmvc 4.1 以后支持该方法）
	/*@RequestMapping("/item/list")
	@ResponseBody
	//object类型返回会被自动转化为json
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}*/
}
