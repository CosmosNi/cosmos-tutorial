package com.cosmos.shiro.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro.common.vo
 * @ClassName: BaseVO
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 15:50
 * @Version: 1.0
 */
@Data
public class BaseVO implements Serializable {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private String createdBy;
}
