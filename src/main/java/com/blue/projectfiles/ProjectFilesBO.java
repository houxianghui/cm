package com.blue.projectfiles;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.annotation.Transactional;

import com.abc.logic.IbatisBO;
import com.blue.enums.YesOrNo;
import com.blue.project.ProjectList;
import com.blue.project.ProjectListDAO;
import com.eis.util.CheckUtil;

public class ProjectFilesBO extends IbatisBO {
	private ProjectFilesDAO projectFilesDAO;
	private JdbcTemplate jdbcTemplate;
	private LobHandler lobHandler;
	@Override
	public void update(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void insert(Object obj) throws Exception {
//		projectFilesDAO.insertSelective((ProjectFiles)obj);
		final ProjectFiles pd = (ProjectFiles)obj;
		final InputStream is = new ByteArrayInputStream(pd.getContent());
		jdbcTemplate.execute("insert into project_files (PROJECT_ID, FILE_NAME, FILE_SIZE, FILE_TYPE, USER_ID,UPDATE_DATE, MEMO,VERSION_ID,CONTENT) values (?,?,?,?,?,?,?,?,?)", new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
				ps.setString(1, pd.getProjectId());
				ps.setString(2, pd.getFileName());
				ps.setInt(3, pd.getFileSize());
				ps.setString(4, pd.getFileType());
				ps.setString(5, pd.getUserId());
				ps.setString(6, pd.getUpdateDate());
				ps.setString(7, pd.getMemo());
				ps.setString(8, pd.getVersionId());
				lobCreator.setBlobAsBytes(ps, 9, pd.getContent());
			}
		});
		jdbcTemplate.update("update project_list set IS_IN_CONTRACT = '"+YesOrNo.Y+"' where project_id = '"+pd.getProjectId()+"'");
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return projectFilesDAO.selectByPrimaryKey(Long.parseLong((String)obj));
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		ProjectFilesExample e = new ProjectFilesExample();
		if(obj != null){
			e.createCriteria().andProjectIdEqualTo(obj.toString());
		}
		return projectFilesDAO.selectByExampleWithoutBLOBs(e);
	}
	public List queryForList(String projectId,String versionId) throws Exception {
		ProjectFilesExample e = new ProjectFilesExample();
		if(!CheckUtil.isEmptry(projectId)){
			e.createCriteria().andProjectIdEqualTo(projectId);
		}
		if(!CheckUtil.isEmptry(versionId)){
			e.createCriteria().andVersionIdEqualTo(versionId);
		}
		return projectFilesDAO.selectByExampleWithoutBLOBs(e);
	}
	public List getDocsOfVersion(List<ProjectList> l,String versionId){
		if(l == null || l.size() == 0){
			return new ArrayList(0);
		}
		ProjectFilesExample e = new ProjectFilesExample();
		List<String> projects = new ArrayList<String>(l.size());
		for(ProjectList p : l){
			projects.add(p.getProjectId());
		}
		ProjectFilesExample.Criteria c = e.createCriteria();
		c.andProjectIdIn(projects);
		e.or(e.createCriteria().andVersionIdEqualTo(versionId));
		return projectFilesDAO.selectByExampleWithoutBLOBs(e);
	}
	
	@Override
	public void delete(Object obj) throws Exception {
		ProjectFiles pf = projectFilesDAO.selectByPrimaryKey(Long.parseLong(obj.toString()));
		projectFilesDAO.deleteByPrimaryKey(Long.parseLong(obj.toString()));
		ProjectFilesExample e = new ProjectFilesExample();
		e.createCriteria().andProjectIdEqualTo(pf.getProjectId());
		int count = projectFilesDAO.countByExample(e);
		if(count == 0){
			jdbcTemplate.update("update project_list set IS_IN_CONTRACT = '"+YesOrNo.N+"' where project_id = '"+pf.getProjectId()+"'");
		}
	}

	public void setProjectFilesDAO(ProjectFilesDAO projectFilesDAO) {
		this.projectFilesDAO = projectFilesDAO;
	}

	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

}
