package com.cosmos.core.generate.support;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: code-strategy
 * @Package: com.cosmos.support
 * @ClassName: Table
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/14 9:05
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class Table {
    private String name;
    private String remarks;
    private List<TableColumn> columns=new ArrayList();
    private boolean exists;
}
