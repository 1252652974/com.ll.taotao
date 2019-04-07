package com.taotao.common.pojo;

import java.util.List;
/**
 * 创建此对象因为EasyUI对返回json的格式有要求，将json里面应该有的数据封装通过springmvc注解转化为json
 * 放在common里面时因为其他工程都有可能用到EasyUI，使用此封装结果对象通过注解转化为json对象
 * @author 刘亮
 * @date 2019年2月6日 下午6:00:52
 */
public class EasyUIDateGridResult {
	 	private Integer total;

	    private List<?> rows;

	    public EasyUIDateGridResult(Integer total, List<?> rows) {
	        this.total = total;
	        this.rows = rows;
	    }

	    public EasyUIDateGridResult(Long total, List<?> rows) {
	        this.total = total.intValue();
	        this.rows = rows;
	    }

	    public Integer getTotal() {
	        return total;
	    }

	    public EasyUIDateGridResult() {
			super();
		}

		public void setTotal(Integer total) {
	        this.total = total;
	    }

	    public List<?> getRows() {
	        return rows;
	    }

	    public void setRows(List<?> rows) {
	        this.rows = rows;
	    }
}
