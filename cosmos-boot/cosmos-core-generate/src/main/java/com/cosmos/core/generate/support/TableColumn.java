package com.cosmos.core.generate.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: code-strategy
 * @Package: com.cosmos.support
 * @ClassName: TableColumn
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/14 9:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableColumn {
    private String name;
    private String type;
    private String remarks;
}
