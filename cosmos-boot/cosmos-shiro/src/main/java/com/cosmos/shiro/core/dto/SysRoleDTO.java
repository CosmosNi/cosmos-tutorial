package com.cosmos.shiro.core.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by nijiahui on 2019-06-24.
 */
@Data
public class SysRoleDTO {


    private String roleName;

    private List<Long> menuIds;
}
