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
        		String v;
                Field f = FieldUtils.getField(form.getClass(), s, true);
                if(f.getType()==String.class){
                	v=(String)FieldUtils.readField(f, form);
                }else{
                	v=String.valueOf(FieldUtils.readField(f, form));
                }
                Field targetF = FieldUtils.getField(P.getClass(),s,true);
                ReflectionUtils.setField(targetF, P, v);
            }
        }
        catch (Throwable t) {
        	t.printStackTrace();
        }
	}

}
