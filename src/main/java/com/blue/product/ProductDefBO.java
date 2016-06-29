package com.blue.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.blue.base.BaseBO;

public class ProductDefBO extends BaseBO<ProductDef>{
	@Autowired
	private ProductDefDAO dao;
	@Override
	public void update(ProductDef obj) throws Exception {
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void insert(ProductDef obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public ProductDef queryForObject(Object obj) throws Exception {
		return dao.selectByPrimaryKey(obj.toString());
	}

	@Override
	public List<ProductDef> queryForList(Object obj) throws Exception {
		ProductDefExample e = new ProductDefExample();
		return dao.selectByExample(e);
	}
	
	public List<ProductDefVO> queryForListVO(Object obj)throws Exception{
		List<ProductDef> l = queryForList(obj);
		List<ProductDefVO> result = new ArrayList<ProductDefVO>(l.size());
		for(ProductDef pd : l){
			ProductDefVO v = new ProductDefVO();
			BeanUtils.copyProperties(pd, v);
			result.add(v);
		}
		return result;
	}
	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByPrimaryKey(obj.toString());
	}


}
