package com.cosmos.log.core.dto;

import com.cosmos.log.commmon.enums.BusinessStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.dto
 * @ClassName: SysLogDTO
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 14:19
 * @Version: 1.0
 */
@Data
@Builder
public class SysLogDTO {

    private String operSystem;

    private String operModule;

    private String operFunction;

    private String operMethod;

    private String operName;

    private String operUrl;

    private String operIp;

    private String operLocation;

    private String operParam;

    @Enumerated(EnumType.STRING)
    private BusinessStatus operStatus;

    private String errorMsg;

    private Long executionTime;

    private String description;
}
