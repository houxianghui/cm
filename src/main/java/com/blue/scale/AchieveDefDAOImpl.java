package com.blue.scale;

import com.blue.scale.AchieveDef;
import com.blue.scale.AchieveDefExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class AchieveDefDAOImpl extends SqlMapClientDaoSupport implements AchieveDefDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public AchieveDefDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int countByExample(AchieveDefExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("achieve_def.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int deleteByExample(AchieveDefExample example) {
        int rows = getSqlMapClientTemplate().delete("achieve_def.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int deleteByPrimaryKey(Integer achieveId) {
        AchieveDef key = new AchieveDef();
        key.setAchieveId(achieveId);
        int rows = getSqlMapClientTemplate().delete("achieve_def.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void insert(AchieveDef record) {
        getSqlMapClientTemplate().insert("achieve_def.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public void insertSelective(AchieveDef record) {
        getSqlMapClientTemplate().insert("achieve_def.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public List selectByExample(AchieveDefExample example) {
        List list = getSqlMapClientTemplate().queryForList("achieve_def.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public AchieveDef selectByPrimaryKey(Integer achieveId) {
        AchieveDef key = new AchieveDef();
        key.setAchieveId(achieveId);
        AchieveDef record = (AchieveDef) getSqlMapClientTemplate().queryForObject("achieve_def.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByExampleSelective(AchieveDef record, AchieveDefExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("achieve_def.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByExample(AchieveDef record, AchieveDefExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("achieve_def.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByPrimaryKeySelective(AchieveDef record) {
        int rows = getSqlMapClientTemplate().update("achieve_def.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    public int updateByPrimaryKey(AchieveDef record) {
        int rows = getSqlMapClientTemplate().update("achieve_def.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table achieve_def
     *
     * @ibatorgenerated Mon Jul 29 11:36:07 CST 2013
     */
    private static class UpdateByExampleParms extends AchieveDefExample {
        private Object record;

        public UpdateByExampleParms(Object record, AchieveDefExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}