package com.coal.mtp.util;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义返回JSON 数据格中日期格式化处理
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class JodaDatetimeSerializer extends JsonSerializer<DateTime> {
    private static final String dateFormat = ("yyyy-MM-dd");

    @Override
    public void serialize(DateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        String formattedDate = DateTimeFormat.forPattern(dateFormat).print(date);
        gen.writeString(formattedDate);
    }
}
