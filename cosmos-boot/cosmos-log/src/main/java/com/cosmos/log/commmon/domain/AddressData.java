package com.cosmos.log.commmon.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.domain
 * @ClassName: AddressData
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 15:19
 * @Version: 1.0
 */
@Data
@ToString
public class AddressData {

    private String code;
    private DataInfo data;

    @Data
    @ToString
    public class DataInfo {
        private String ip;
        private String country;
        private String area;
        private String region;
        private String city;
        private String county;
        private String isp;
    }
}
