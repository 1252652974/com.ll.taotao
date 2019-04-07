package com.taotao.service;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	EasyUIDateGridResult contentList(Long id, Integer page, Integer rows);
	TaotaoResult insertContent(TbContent content);
}
