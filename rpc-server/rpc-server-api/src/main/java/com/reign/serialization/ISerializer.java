package com.reign.serialization;

/**
 * @ClassName: ISerializer
 * @Description: 实现序列化的通用接口
 * @Author: wuwx
 * @Date: 2019-09-18 09:58
 **/
public interface ISerializer {

    <T> byte[] serialize(Object object);

    <T> T deserialize(byte[] bytes,Class<T> clazz);

}
