/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * A {@link Converter} which uses Jackson for reading and writing entities.
 */
public class SeerApiJacksonConverter implements Converter {

    private static final String MIME_TYPE = "application/json; charset=UTF-8";

    private final ObjectMapper _mapper;

    public SeerApiJacksonConverter() {
        _mapper = new ObjectMapper();

        // do not write null values
        _mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        _mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        _mapper.setVisibilityChecker(_mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        // set Date objects to output in readable customized format
        _mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        _mapper.setDateFormat(dateFormat);
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        try {
            JavaType javaType = _mapper.getTypeFactory().constructType(type);
            return _mapper.readValue(body.in(), javaType);
        }
        catch (IOException e) {
            throw new ConversionException(e);
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        try {
            String json = _mapper.writeValueAsString(object);
            return new TypedByteArray(MIME_TYPE, json.getBytes("UTF-8"));
        }
        catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Return the internal ObjectMapper
     * @return
     */
    protected ObjectMapper getMapper() {
        return _mapper;
    }
}
