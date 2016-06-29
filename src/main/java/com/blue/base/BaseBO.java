package com.blue.base;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseBO<T> {
	protected JdbcTemplate jdbcTemplate;
	public abstract void update(T obj) throws Exception;
	public abstract void insert(T obj) throws Exception;
	public abstract T queryForObject(Object obj) throws Exception;
	public abstract List<T> queryForList(Object obj) throws Exception;
	public abstract void delete(Object obj) throws Exception;
	@Autowired
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
