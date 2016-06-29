package com.blue.report.base;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class DBQuery {
	private JdbcTemplate jt;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}
	public int queryForInt(String sql){
		return jt.queryForInt(sql);
	}
	public String queryForString(String sql){
		return (String)jt.queryForObject(sql, String.class);
	}
	public List queryForList(String sql){
		return jt.queryForList(sql);
	}
	
}
