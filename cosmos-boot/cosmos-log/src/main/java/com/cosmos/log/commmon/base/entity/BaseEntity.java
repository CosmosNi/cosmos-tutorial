package com.cosmos.log.commmon.base.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: cosmos-server
 * @Package: com.cosmos.web.entity
 * @ClassName: BaseEntity
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/15 10:59
 * @Version: 1.0
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @CreatedDate
    private Date createdTime;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private Date updatedTime;
    @LastModifiedBy
    private String updatedBy;
    @Version
    private Long version;
}
