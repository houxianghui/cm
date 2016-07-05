package com.yly.func;



import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yly.issue.MWsIssuetbForm;

public class ParaTools {
	public ParaTools(){
		super();
		}

	public static void setPara(Para P,String[] paras,MWsIssuetbForm form) {
        try {
        	for(String s : paras){
                Field f = FieldUtils.getField(form.getClass(), s, true);
                Field targetF = FieldUtils.getField(P.getClass(),s,true);
                if(f.getType()==String.class){
                	String v=(String)FieldUtils.readField(f, form);
                    ReflectionUtils.setField(targetF, P, v);
                }else{
                	int v=(int)FieldUtils.readField(f, form);
                    ReflectionUtils.setField(targetF, P, v);
                }
            }
        }
        catch (Throwable t) {
        	t.printStackTrace();
        }
	}

}
