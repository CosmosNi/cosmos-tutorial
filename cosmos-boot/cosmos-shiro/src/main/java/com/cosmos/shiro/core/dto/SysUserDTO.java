package com.cosmos.shiro.core.dto;

import com.cosmos.shiro.core.enums.State;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * Created by nijiahui on 2019-06-24.
 */
@Data
public class SysUserDTO {


    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private List<Long> roleIds;
}
