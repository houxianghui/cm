package com.yly.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FuncDrools {
	public FuncDrools(){
		super();
	}

	public static void main(String[] args) {
		Func funcFact = new Func();
		funcFact.setApplyAttr("101");
		funcFact.setManufacId("TY");
		funcFact.setProdId("psam");
		funcFact.setOperAct("I");
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-dtables");
            kSession.insert(funcFact);
            kSession.fireAllRules();
            System.out.println("Func is "+funcFact.getFunc());
            System.out.println("Para is "+funcFact.getPara());
        }
        
        catch (Throwable t) {
        	t.printStackTrace();
  
        }
	}

}
