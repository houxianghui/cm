package com.yly.func;

import java.lang.reflect.Method;
import com.yly.drools.Func;

public class CallFunc {
	public CallFunc() {
		super();

	}
	public static int callId(Func func,Para para) throws Exception{
		JacobInter j=new JacobInter();
		Method m = j.getClass().getDeclaredMethod(func.getFunc(),Para.class);
		int result=(int)m.invoke(j,para);
		return result;
	}
	
}
