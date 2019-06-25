package com.cosmos.log.core.entity;

import com.cosmos.log.commmon.base.entity.BaseEntity;
import com.cosmos.log.commmon.enums.BusinessStatus;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * Created by nijiahui on 2019-06-06.
 */
@Entity
@Data
@Table(name = "sys_log")
@EntityListeners(AuditingEntityListener.class)
public class SysLog extends BaseEntity {
    /**
     * 所属系统
     */
    @Basic
    private String operSystem;
    /**
     * 所属模块
     */
    @Basic
    private String operModule;
    /**
     * 功能模块
     */
    @Basic
    private String operFunction;
    /**
     * 方法名称
     */
    @Basic
    private String operMethod;
    /**
     * 操作人员
     */
    @Basic
    private String operName;
    /**
     * 请求URL
     */
    @Basic
    private String operUrl;
    /**
     * 主机地址
     */
    @Basic
    private String operIp;
    /**
     * 操作地点
     */
    @Basic
    private String operLocation;
    /**
     * 请求参数
     */
    @Basic
    private String operParam;
    /**
     * 操作状态（0正常 1异常）
     */
    @Enumerated(EnumType.STRING)
    private BusinessStatus operStatus;
    /**
     * 错误消息
     */
    @Basic
    private String errorMsg;
    /**
     * 执行时间
     */
    @Basic
    private Long executionTime;
    /**
     * 描述
     */
    @Basic
    private String description;
}
