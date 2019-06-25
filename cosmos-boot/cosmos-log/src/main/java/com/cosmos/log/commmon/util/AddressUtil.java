package com.cosmos.log.commmon.util;

import com.alibaba.fastjson.JSONObject;
import com.cosmos.log.commmon.domain.AddressData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.util
 * @ClassName: AddressUtil
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 14:50
 * @Version: 1.0
 */
@Slf4j
public class AddressUtil {

    private static final String addressUrl = "http://ip.taobao.com/service/getIpInfo.php";
    private static RestTemplate restTemplate;

    public static String getAddress(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        init();
        String url = addressUrl + "?ip=" + ip;
        String data = restTemplate.postForObject(url, null, String.class);
        if (StringUtils.isEmpty(data)) {
            log.error("获取地理位置异常 {}", ip);
            return "未知";
        }
        AddressData addressData = JSONObject.parseObject(data, AddressData.class);
        StringBuilder sb = new StringBuilder();
        sb.append(addressData.getData().getCountry());
        sb.append(addressData.getData().getArea());
        sb.append(addressData.getData().getRegion());
        sb.append(addressData.getData().getCity());
        sb.append(addressData.getData().getCounty());
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(AddressUtil.getAddress("218.192.3.42"));

    }

    /**
     * 初始化restTemlate
     */
    private static void init() {
        if (restTemplate == null) {
            synchronized (RestTemplate.class) {
                SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
                requestFactory.setConnectTimeout(10000);
                requestFactory.setReadTimeout(10000);
                if (restTemplate == null) {
                    restTemplate = new RestTemplate(requestFactory);
                }
            }
        }
    }
}
