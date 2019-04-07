package com.taotao.service;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDateGridResult getItemList(int page, int rows);
	TaotaoResult createItem(TbItem item, String desc, String itemParam)  throws Exception;
}
