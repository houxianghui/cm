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
    			throw new MessageException("找不到合适的调用函数");
            }
        }
        catch (Throwable t) {
        	t.printStackTrace();
			throw new MessageException("调用函数异常!");
 
        }
	}

}
