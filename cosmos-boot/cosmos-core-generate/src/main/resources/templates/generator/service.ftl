package <#if webBasePackage??>${webBasePackage}<#else>${basePackage}</#if>.<#if module??>${module}.</#if>${servicePackage};

import ${basePackage}<#if module??>${module}.</#if>.${entityPackage}.*;
<#if superService??>
    import ${superService};
</#if>


/**
* Created by $!{author} on $!{now}.
*/
public interface ${nameStrategy.entity(table.name)!}Service <#if superService??>extends ${nameStrategy.trimPackage(superService)}<${nameStrategy.entity(table.name)}></#if> {
}