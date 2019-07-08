package com.cosmos.es.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.es.entity
 * @ClassName: Dept
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 16:24
 * @Version: 1.0
 */

/**
 * indexName：对应索引库名称
 * type：对应在索引库中的类型
 * shards：分片数量，默认5
 * replicas：副本数量，默认1
 */
@Document(indexName = "dept", type = "dept_type")
@Data
@Builder
public class Dept {

    @Id
    private Long id;
    private String deptName;
    private String deptType;
    private String deptSource;
}
