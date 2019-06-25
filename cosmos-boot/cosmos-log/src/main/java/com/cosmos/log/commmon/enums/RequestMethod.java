package com.cosmos.log.commmon.enums;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ProjectName: kdvp-backend-v1
 * @Package: com.kedacom.kisp.kdvp.enums
 * @ClassName: RequestMethod
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/21 13:04
 * @Version: 1.0
 */
@JSONType(serializeEnumAsJavaBean = true)

public enum RequestMethod {
    /**
     * 请求数据
     */
    GET("Get") {
        @Override
        public JSONObject getRestData(RestTemplate restTemplate, Map<String, Object> param, String uri, HttpEntity formEntity) {
            return restTemplate.exchange(uri, HttpMethod.GET, formEntity, JSONObject.class, param).getBody();
        }
    },

    POST("post") {
        @Override
        public JSONObject getRestData(RestTemplate restTemplate, Map<String, Object> param, String uri, HttpEntity formEntity) {

            return restTemplate.postForEntity(uri, formEntity, JSONObject.class).getBody();
        }

    },
    PUT("put") {
        @Override
        public JSONObject getRestData(RestTemplate restTemplate, Map<String, Object> param, String uri, HttpEntity formEntity) {

            return restTemplate.exchange(uri, HttpMethod.PUT, formEntity, JSONObject.class, param).getBody();
        }

    },
    DELETE("delete") {
        @Override
        public JSONObject getRestData(RestTemplate restTemplate, Map<String, Object> param, String uri, HttpEntity formEntity) {
            return restTemplate.exchange(uri, HttpMethod.DELETE, formEntity, JSONObject.class, param).getBody();
        }
    };

    @Getter
    private final String symbol;

    RequestMethod(String symbol) {
        this.symbol = symbol;
    }

    public abstract JSONObject getRestData(RestTemplate restTemplate, Map<String, Object> param, String uri, HttpEntity formEntity);
}
