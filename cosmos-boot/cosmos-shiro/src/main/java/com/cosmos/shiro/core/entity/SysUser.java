package com.cosmos.shiro.core.entity;

import com.cosmos.shiro.common.entity.BaseEntity;
import com.cosmos.shiro.core.enums.State;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * Created by nijiahui on 2019-06-24.
 */
@Entity
@Data
@Table(name = "sys_user")
@EntityListeners(AuditingEntityListener.class)
public class SysUser extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 盐值
     */
    @Column(name = "salt")
    private String salt;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private State state;

    public Long getUserId() {
        return this.getId();
    }

}
