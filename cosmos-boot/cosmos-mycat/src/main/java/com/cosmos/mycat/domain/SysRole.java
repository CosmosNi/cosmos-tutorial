package com.cosmos.mycat.domain;

import com.cosmos.mycat.core.entity.BaseEntity;
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
@Table(name = "sys_role")
@EntityListeners(AuditingEntityListener.class)
public class SysRole extends BaseEntity {


    /**
     * 角色名称
     */
   @Column(name = "role_name")
    private String roleName;
}
