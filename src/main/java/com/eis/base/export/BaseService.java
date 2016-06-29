/**
 * 
 */
package com.eis.base.export;

import com.eis.base.export.core.IHibernateGenericDao;
import com.eis.base.export.core.IbatisGenericDao;



/**
 * @author David
 *
 */
public abstract class BaseService{
	
    
	protected IHibernateGenericDao sqlDao_h;
	protected IbatisGenericDao     sqlDao_i;

	public IHibernateGenericDao getSqlDao_h() {
		return sqlDao_h;
	}

	public void setSqlDao_h(IHibernateGenericDao sqlDao_h) {
		this.sqlDao_h = sqlDao_h;
	}

	public IbatisGenericDao getSqlDao_i() {
		return sqlDao_i;
	}

	public void setSqlDao_i(IbatisGenericDao sqlDao_i) {
		this.sqlDao_i = sqlDao_i;
	}
	
	public static final Integer TXNFLAG_FH=1;
	public static final Integer TXNFLAG_SAV=0;
	public static final Integer TXNFLAG_COMM_ISS=2;
	public static final Integer TXNFLAG_TIME_ISS=3;
}
