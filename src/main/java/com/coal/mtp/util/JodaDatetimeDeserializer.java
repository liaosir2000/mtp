package com.coal.mtp.util;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 自定义返回JSON 数据格中日期格式化处理
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class JodaDatetimeDeserializer extends JsonDeserializer<DateTime> {
    private static final String dateFormat = ("yyyy-MM-dd");

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = jp.getText();
        DateTime dateTime = DateTimeFormat.forPattern(dateFormat).parseDateTime(text);
        return dateTime;
    }
}
