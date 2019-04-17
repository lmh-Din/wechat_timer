package com.wechat.timer.util;

import com.wechat.timer.enums.MsgType;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName XML2Map
 * @Author Liumh
 * @Date 2019/4/15 16:21
 * @Version v1.0
 */
@Slf4j
public class Transformer<T> {

    public static Map<String, Object> xmltoMap(HttpServletRequest request){
        Map<String, Object> elements = new HashMap<>();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(ins);
            Element element = doc.getRootElement();
            List<Element> list = element.elements();
            list.forEach(row -> {
                String name = row.getName();
                Object text = row.getText();
                if ("MsgType".equals(name)){
                    text = MsgType.valueOf(text.toString());
                }
                elements.put(name, text);
            });
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("xmlToMap：{}", elements);
        return elements;
    }

    public void mapToBean(Map<String, Object> source, T target){
        source.forEach((k, v) -> {
            Class clz = target.getClass();
            try {
                StringBuffer setMethodName = new StringBuffer("set");
                setMethodName.append(k);
                Method setFieldValueMethod = clz.getMethod(setMethodName.toString(), v.getClass());
                setFieldValueMethod.invoke(target, v);
            } catch (NoSuchMethodException e) {
                log.error("skip this method", e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

}
