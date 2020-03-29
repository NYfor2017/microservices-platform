package com.ny.nyredis.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 此时定义的序列化操作表示可以序列化所有类的对象，当然，这个对象所在的类一定要实现序列化接口
 *
 * @author N.Y
 * @date 2020-03-27 18:29
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
    //做一个空数组，而不是null
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    //对象与字节数组之间的转换器，为了方便进行对象与字节数组之间的转换
    private Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    private Converter<byte[], Object> deserializingConverter = new DeserializingConverter();

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        //没有序列化的对象出现时，直接返回空数组
        if (obj == null) {
            return EMPTY_BYTE_ARRAY;
        }
        //将对象变成字节数组
        return this.serializingConverter.convert(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        //此时没有对象的内容信息
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return this.deserializingConverter.convert(bytes);
    }
}
