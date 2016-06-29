package com.eis.base.export.core;

import org.springframework.dao.DataAccessException;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.List;

public interface IbatisGenericDao {

	 public <T> T getRecord(String sqlID) throws DataAccessException;
	 
     public <T> T getRecord(String sqlID, Object object) throws DataAccessException;
     
     public <T> T getMaxId(String sqlID) throws DataAccessException;
     
     public <T> List<T> getRecordList(String sqlID) throws DataAccessException;

     public <T> List<T> getRecordList(String sqlID, Object object) throws DataAccessException;
     
     public <T> List<T> getRecordList(String sqlID, Object object, int offset, int limit) throws DataAccessException;

     public int insertRecord(String sqlID, Object object) throws DataAccessException;

     public int updateRecord(String sqlID, Object object) throws DataAccessException;

     public int deleteRecord(String sqlID, Object object) throws DataAccessException;
     
     public int deleteRecord(String sqlID) throws DataAccessException;
 
 	 public SqlMapClient getSqlMapClient2();
    
     public void addBatchRecord(String sqlID,List<?> examlogList) throws SQLException;
     
     
     public void editBatchRecord(String sqlID,List<?> RecordList) throws SQLException;
     
      public void deleteBatchRecord(String sqlID,List<?> RecordList) throws SQLException;
      
      
      public void insertNewRecord(String sqlID,List<?> RecordList) throws SQLException;
}
