package com.cosmos.shiro.core.vo;

import com.cosmos.shiro.common.vo.BaseVO;
import com.cosmos.shiro.core.enums.State;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by nijiahui on 2019-06-24.
 */
@Data
public class SysUserVO extends BaseVO {


    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
