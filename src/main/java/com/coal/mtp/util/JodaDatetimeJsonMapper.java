package com.coal.mtp.util;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JodaDatetimeJsonMapper extends ObjectMapper {
	public JodaDatetimeJsonMapper() {
		SimpleModule module = new SimpleModule("JSONModule", new Version(1, 0, 0, ""));
		module.addSerializer(DateTime.class, new JodaDatetimeSerializer());
		module.addDeserializer(DateTime.class, new JodaDatetimeDeserializer());
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		registerModule(module);
	}
}
