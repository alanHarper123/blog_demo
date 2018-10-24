package com.qiujintao.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

// use to replace the default serializer that com.fasterxml.jackson.databind.ObjectMapper used to serialize the byte array as Base64 string.
// this serializer serialize the byte array use String constructor
public class ByteArraySerializer extends JsonSerializer<byte[]> {

	@Override
	public void serialize(byte[] bytes, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		jgen.writeString(new String(bytes));
	}

}
