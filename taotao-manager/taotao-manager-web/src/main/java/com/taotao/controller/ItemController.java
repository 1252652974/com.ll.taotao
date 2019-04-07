package com.taotao.controller;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {  
	@Autowired
	private ItemService itemService;
	//设置相应的内容为json数据
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDateGridResult getItemlist(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows) throws Exception {
		//查询商品列表
		EasyUIDateGridResult result = itemService.getItemList(page, rows);
		
		return result;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		TaotaoResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
}
