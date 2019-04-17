package com.wechat.timer;

import com.wechat.timer.entity.TextMsg;
import com.wechat.timer.enums.MsgType;
import com.wechat.timer.util.Transformer;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName TransformerTest
 * @Author Liumh
 * @Date 2019/4/17 10:34
 * @Version v1.0
 */
public class TransformerTest {

    @Test
    public void testMaptoBean(){
        Map<String, Object> source = new HashMap<>();
        source.put("ToUserName","toUser");
        source.put("FromUserName","fromUser");
        source.put("CreateTime",1348831860);
        source.put("MsgType", MsgType.TEXT);

        TextMsg textMsg = new TextMsg();

        Transformer<TextMsg> transformer = new Transformer<>();

        transformer.mapToBean(source, textMsg);

        System.out.println(textMsg.toString());
    }
}
