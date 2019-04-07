package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		// TODO Auto-generated method stub
		//根据查询条件,非主键要添加条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询条件
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		
		for(TbItemCat tbitemCat : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbitemCat.getId());
			node.setText(tbitemCat.getName());
			node.setState(tbitemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

}
