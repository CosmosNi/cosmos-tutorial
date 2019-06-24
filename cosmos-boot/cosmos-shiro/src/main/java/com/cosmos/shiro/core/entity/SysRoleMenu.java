package com.cosmos.shiro.core.entity;

import com.cosmos.shiro.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


/**
 * Created by nijiahui on 2019-06-24.
 */
@Entity
@Data
@Table(name = "sys_role_menu")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class SysRoleMenu extends BaseEntity {

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 权限ID
     */
    @Column(name = "menu_id")
    private Long menuId;
}
