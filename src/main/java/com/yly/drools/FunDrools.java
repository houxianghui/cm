package com.yly.drools;

import org.kie.api.cdi.KSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Component;

import com.eis.exception.MessageException;
@Component 
public class FunDrools {
	@KSession(value = "ksession-dtables")
    StatelessKieSession kSession;


	public void getFunc(Func func) throws MessageException {
        try { 
    	    kSession.execute(func);
            System.out.println("Func is "+func.getFunc());
            System.out.println("Para is "+func.getPara());
            if(func.getFunc()==null||func.getPara()==null){
    			throw new MessageException("�Ҳ������ʵĵ��ú���");
            }
        }
        catch (Throwable t) {
        	t.printStackTrace();
			throw new MessageException("���ú����쳣!");
 
        }
	}

}
