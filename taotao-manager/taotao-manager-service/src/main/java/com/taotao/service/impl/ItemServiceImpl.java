package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	//SOLR_BASE_URL=http://localhost:8083/search
	//SOLR_MANAGER_IMPORTALL_URL=/manager/importall
	@Value("${SOLR_BASE_URL}")
	private String SOLR_BASE_URL;
	@Value("${SOLR_MANAGER_IMPORTALL_URL}")
	private String SOLR_MANAGER_IMPORTALL_URL;
	
	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyUIDateGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		EasyUIDateGridResult result = new EasyUIDateGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal((int)pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		//item补全
		//生成商品id
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态：1：正常  2：下架 3：删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入到数据库
		itemMapper.insert(item);
		
		//添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId, desc);
		//添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if(result.getStatus() != 200) {
			throw new Exception();
		}
		//添加更新Solr索引逻辑
		try {
			HttpClientUtil.doGet(SOLR_BASE_URL + SOLR_MANAGER_IMPORTALL_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}
	
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
     	TbItemDesc itemDesc = new TbItemDesc();
     	itemDesc.setItemId(itemId);
     	itemDesc.setItemDesc(desc);
     	itemDesc.setCreated(new Date());
     	itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	/**
	 * 添加规格参数
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setUpdated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok() ;
	}
}
