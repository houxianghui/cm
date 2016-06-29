package com.blue.scale;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.XMLReader;

import com.blue.enums.Steps;
import com.eis.util.CheckUtil;
import com.thoughtworks.xstream.XStream;

/**
 * 用来将excel的模板生成xml并保持到数据库中
 * 将excel保存成xml格式，拷贝有效数据行出来，替换掉ss: 和 x:
 * @author blue
 * mid 100 100
 * small 0 0
 * big 200 200
 */
public class ScaleMake {
	public static void main(String[] args) throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read("f:/scale-big.xml");
		Element root = doc.getRootElement();
		List<Element> rows = root.element("Table").elements("Row");
		
		Scale scale = new Scale();
		List<Step> result = new ArrayList<Step>();
		List<Action> actions = new ArrayList<Action>();
		List<AchieveDef> achieveList = new ArrayList<AchieveDef>(); 
		int count=1;
		int achieveId = 200;
		int actionId = 200;
		try {
			for(Element e:rows){
				count++;
				List<Element> cells = e.elements("Cell");
				int length = cells.size();
				if(length == 0){
					continue;
				}
				AchieveDef achieve = new AchieveDef();
				achieve.setCheckStyle(getCellText(cells, --length));
				achieve.setAchieve(getCellText(cells, --length));
				achieve.setAchieveId(achieveId++);
				Action action = null;
				if(length>3){//length>0 说明是新的活动项，前面的achieve需要加入此list
					achieveList = new ArrayList<AchieveDef>();
					
					action = new Action();
					ActionDef actionDef = new ActionDef();
					action.setActionDef(actionDef);
					
					actionDef.setCommitLimit(getCellText(cells, --length));
					actionDef.setAction(getCellText(cells, --length));
					--length;
					actionDef.setName(getCellText(cells, --length));
					actionDef.setActionId(actionId++);
					action.setAchieveDefs(achieveList);
				}else{
					achieveList.add(achieve);//在确定list归属之后再放入
					continue;
				}
				achieveList.add(achieve);//在确定list归属之后再放入
				
				if(length>=1){//说明是阶段的新定义
					actions = new ArrayList<Action>();
					
					Step step = new Step();
					step.setStep(getCellText(cells, --length).split("-")[0]);
					step.setActions(actions);
					result.add(step);
				}else{
					actions.add(action);
					continue;
				}
				actions.add(action);
			}
			scale.setSteps(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发生在"+count);
		}finally{
			XStream x = new XStream();
			System.out.println(x.toXML(scale));
		}
	}
	private static String getCellText(List<Element> cells,int i){
		Element e = cells.get(i).element("Data");
		if(e == null){
			return cells.get(i).getText();
		}
		List<Element> children = e.elements();
		while(children!=null && children.size()>0){
			e = (Element)children.get(0);
			children = e.elements();
		}
		return e.getText();
	}
//	public static void makeBig(){
//		Scale s = new Scale();
//		List<Step> stepList = new ArrayList<Step>();
//		Steps[] steps = Steps.values();
//		for(Steps step:steps){
//			Step t = new Step();
//			t.setStep(step.toString());
//			ActionDef ad = new ActionDef();
//			ad.setActionId(1);
//			ad.setName("项目立项申请");
//			ad.setAction("1、客户化需求确认表可代替项目立项申请单\r\n3、需单独编写项目立项申请单，大型项目由分管领导审批；");
//			ad.setCommitLimit("项目立项申请时提交");
//			List<ActionDef> l = new ArrayList<ActionDef>();
//			l.add(ad);
//			t.setActionDef(l);
//			stepList.add(t);
//		}
//		s.setSteps(stepList);
//		XStream x = new XStream();
//		System.out.println(x.toXML(s));
//	}
}
