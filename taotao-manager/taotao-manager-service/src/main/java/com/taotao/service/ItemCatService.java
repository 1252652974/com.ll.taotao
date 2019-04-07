package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
/**
 * 商品分类管理
 * @author 刘亮
 * @date 2019年2月13日 上午9:56:12
 */
public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
