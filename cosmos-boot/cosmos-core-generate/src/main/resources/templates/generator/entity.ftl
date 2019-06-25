package ${basePackage}.<#if module??>${module}.</#if>${entityPackage};

import javax.persistence.*;
import java.util.*;
import lombok.*;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
<#if superEntity??>
   import ${superEntity};
</#if>


/**
* Created by ${author!} on ${now!}.
* ${table.remarks!}
*/
@Entity
@Data
@Table(name = "${nameStrategy.table(table.name)}")
@EntityListeners(AuditingEntityListener.class)
public class ${nameStrategy.entity(table.name)!} <#if superEntity??>extends ${nameStrategy.trimPackage(superEntity)}</#if> {

<#list table.columns as item>
    /**
     * ${item.remarks}
     */
   @Column(name = "${item.name}")
    private ${nameStrategy.type(item.type)} ${nameStrategy.column(item.name)};
</#list>
}
