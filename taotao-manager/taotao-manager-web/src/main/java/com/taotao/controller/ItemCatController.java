package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;

/**
 * 商品分类列表
 * @author 刘亮
 * @date 2019年2月13日 上午10:18:16
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	  @Autowired
	  private ItemCatService itemCatService;
	  
	  @RequestMapping("/list")
	  @ResponseBody
	  public List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		  List<EUTreeNode> list = itemCatService.getCatList(parentId);
		  return list;
	  }
}