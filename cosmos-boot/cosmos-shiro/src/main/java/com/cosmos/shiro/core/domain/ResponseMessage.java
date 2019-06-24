package com.cosmos.shiro.core.domain;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cosmos.shiro.common.enums.CodeEnum;
import com.cosmos.shiro.common.enums.HttpStatusEnum;
import com.google.common.collect.Sets;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro.core.domain
 * @ClassName: ResponseMessage
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 11:11
 * @Version: 1.0
 */
@ApiModel(
        description = "响应结果"
)
public class ResponseMessage<T> implements Serializable {
    private static final long serialVersionUID = 8992436576262574064L;
    @ApiModelProperty("错误消息")
    protected String message;
    @ApiModelProperty("成功时响应内容")
    protected T result;
    @ApiModelProperty(
            value = "状态码",
            required = true
    )
    protected int status;
    @ApiModelProperty("业务自定义状态码")
    protected String code;
    @ApiModelProperty("响应内容的字段")
    protected LinkedHashSet<String> fields;
    @ApiModelProperty(
            value = "时间戳",
            required = true,
            dataType = "Long"
    )
    protected Long timestamp;
    @ApiModelProperty(
            hidden = true
    )
    protected transient Map<Class<?>, Set<String>> includes;
    @ApiModelProperty(
            hidden = true
    )
    protected transient Map<Class<?>, Set<String>> excludes;

    public ResponseMessage() {
    }

    public static <T> ResponseMessage<T> error(String message) {
        return error(500, CodeEnum.UNKNOWN.getValue(), (String) message);
    }

    public static <T> ResponseMessage<T> error(String message, Object... args) {
        return error(500, message, (Object[]) args);
    }

    public static <T> ResponseMessage<T> error(int status, String message) {
        return error(status, CodeEnum.UNKNOWN.getValue(), message);
    }

    public static <T> ResponseMessage<T> error(int status, String message, Object... args) {
        return error(status, CodeEnum.UNKNOWN.getValue(), message, args);
    }

    public static <T> ResponseMessage<T> error(int status, String code, String message) {
        return error(status, code, message, (Object[]) null);
    }

    public static <T> ResponseMessage<T> error(int status, String code, String message, Object... args) {
        ResponseMessage<T> msg = new ResponseMessage();
        msg.message = String.format(message, args);
        msg.status(status);
        msg.code(code);
        return msg.putTimeStamp();
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    public static <T> ResponseMessage<T> ok(T result) {
        return (new ResponseMessage()).result(result).putTimeStamp().code(CodeEnum.SUCCESS.getValue()).status(200);
    }

    private ResponseMessage<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public ResponseMessage<T> result(T result) {
        this.result = result;
        return this;
    }

    public ResponseMessage<T> include(Class<?> type, String... fields) {
        return this.include(type, (Collection) Arrays.asList(fields));
    }

    public ResponseMessage<T> include(Class<?> type, Collection<String> fields) {
        if (this.includes == null) {
            this.includes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            fields.forEach((field) -> {
                if (field.contains(".")) {
                    String[] tmp = field.split("[.]", 2);

                    try {
                        Field field1 = type.getDeclaredField(tmp[0]);
                        if (field1 != null) {
                            this.include(field1.getType(), tmp[1]);
                        }
                    } catch (Throwable var5) {
                        ;
                    }
                } else {
                    this.getStringListFromMap(this.includes, type).add(field);
                }

            });
            return this;
        } else {
            return this;
        }
    }

    public ResponseMessage<T> exclude(Class type, Collection<String> fields) {
        if (this.excludes == null) {
            this.excludes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            fields.forEach((field) -> {
                if (field.contains(".")) {
                    String[] tmp = field.split("[.]", 2);

                    try {
                        Field field1 = type.getDeclaredField(tmp[0]);
                        if (field1 != null) {
                            this.exclude(field1.getType(), tmp[1]);
                        }
                    } catch (Throwable var5) {
                        ;
                    }
                } else {
                    this.getStringListFromMap(this.excludes, type).add(field);
                }

            });
            return this;
        } else {
            return this;
        }
    }

    public ResponseMessage<T> exclude(Collection<String> fields) {
        if (this.excludes == null) {
            this.excludes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            if (this.getResult() != null) {
                Class type = this.getResult().getClass();
                this.exclude(type, fields);
                return this;
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public ResponseMessage<T> include(Collection<String> fields) {
        if (this.includes == null) {
            this.includes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            if (this.getResult() != null) {
                Class type = this.getResult().getClass();
                this.include(type, fields);
                return this;
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public ResponseMessage<T> exclude(Class type, String... fields) {
        return this.exclude(type, (Collection) Arrays.asList(fields));
    }

    public ResponseMessage<T> exclude(String... fields) {
        return this.exclude((Collection) Arrays.asList(fields));
    }

    public ResponseMessage<T> include(String... fields) {
        return this.include((Collection) Arrays.asList(fields));
    }

    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
        return (Set) map.computeIfAbsent(type, (k) -> {
            return new HashSet();
        });
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }

    public ResponseMessage<T> status(int status) {
        this.status = status;
        return this;
    }

    public ResponseMessage<T> code(String code) {
        this.code = code;
        return this;
    }

    public ResponseMessage<T> fields(LinkedHashSet<String> fields) {
        this.fields = fields;
        return this;
    }

    public ResponseMessage<T> field(String field) {
        if (this.fields == null) {
            synchronized (this) {
                if (this.fields == null) {
                    this.fields = Sets.newLinkedHashSet();
                }
            }
        }

        this.fields.add(field);
        return this;
    }

    public <E> E getResult(Class<E> clazz) {
        return BeanUtil.toBean(this.result, clazz);
    }

    public boolean is1xxInformational() {
        return HttpStatusEnum.valueOf(this.status).is1xxInformational();
    }

    public boolean is2xxSuccessful() {
        return HttpStatusEnum.valueOf(this.status).is2xxSuccessful();
    }

    public boolean is3xxRedirection() {
        return HttpStatusEnum.valueOf(this.status).is3xxRedirection();
    }

    public boolean is4xxClientError() {
        return HttpStatusEnum.valueOf(this.status).is4xxClientError();
    }

    public boolean is5xxServerError() {
        return HttpStatusEnum.valueOf(this.status).is5xxServerError();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LinkedHashSet<String> getFields() {
        return this.fields;
    }

    public void setFields(LinkedHashSet<String> fields) {
        this.fields = fields;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<Class<?>, Set<String>> getIncludes() {
        return this.includes;
    }

    public Map<Class<?>, Set<String>> getExcludes() {
        return this.excludes;
    }
}
