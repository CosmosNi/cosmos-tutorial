package com.cosmos.shiro.core.entity;

import com.cosmos.shiro.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


/**
* Created by nijiahui on 2019-06-24.
* 
*/
@Entity
@Data
@Table(name = "sys_menu")
@EntityListeners(AuditingEntityListener.class)
public class SysMenu extends BaseEntity {

    /**
     * 权限名称
     */
   @Column(name = "name")
    private String name;
    /**
     * 权限标识
     */
   @Column(name = "perms")
    private String perms;
}
