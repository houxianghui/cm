/*
 * Created on 2006-8-7
 *
 */
package com.eis.util;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.text.*;
import java.util.HashMap;

import com.eis.base.BaseException;
import com.eis.config.SysConfig;
import com.eis.exception.MessageException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class KeyVDatagram {

    protected Document m_document;
    protected Element m_root;
    private HashMap mainKeyMap;

    public KeyVDatagram() {
        m_document = DocumentHelper.createDocument();
        m_document.addElement("ap");
        m_root = m_document.getRootElement();
    }
    protected KeyVDatagram(Document doc) throws BaseException {
        m_document = doc;
        m_root = m_document.getRootElement();
    }

    public static KeyVDatagram ParseString(String xmlData) throws Exception {
        Document doc;
        doc = DocumentHelper.parseText(xmlData);
        return new KeyVDatagram(doc);
    }

    public static KeyVDatagram ParseStream(InputStream is) throws Exception {
        Document doc;
        SAXReader sar;
        sar = new SAXReader();
        doc = sar.read(is);
        return new KeyVDatagram(doc);
    }

    public String getXML() throws IOException {
        StringWriter sw = new StringWriter();
        OutputFormat fmt;
        fmt = new OutputFormat("\t", true, "gb18030");
        fmt.setTrimText(true);
        XMLWriter xw = new XMLWriter(sw, fmt);
        xw.write(m_document);
        return sw.toString();
    }

    public String getFieldOptional(String section, String field) {
        Element sect, fld;
        if (section == null) //表明从根节点取
            sect = m_root;
        else
            sect = m_root.element(section);
        if (sect == null) //没有该节
            sect = m_root.addElement(section);
        fld = sect.element(field);
        if (fld == null) //没有该域
            return null;
        return fld.getText();
    }

    public String getField(String section, String field) throws BaseException {
        String ret;
        ret = getFieldOptional(section, field);
        if (ret == null)
            throw new MessageException("缺少结点/ap/" + section + "/" + field);
        return ret;
    }

    public void setField(String section, String field, String value) {
        Element sect, fld;
        if (value == null) //值为空则不加入结点
            return;

        if (section == null) //表明从根节点取
            sect = m_root;
        else
            sect = m_root.element(section);
        if (sect == null) //没有该节
            sect = m_root.addElement(section);
        fld = sect.element(field);
        if (fld == null) //没有该域
            fld = sect.addElement(field);

        fld.setText(value);
    }


	public String getMainKeyMap(String code) {
		return (String)mainKeyMap.get(code);
	}
	public void setMainKeyMap(HashMap mainKeyMap) {
		this.mainKeyMap = mainKeyMap;
	}

}
