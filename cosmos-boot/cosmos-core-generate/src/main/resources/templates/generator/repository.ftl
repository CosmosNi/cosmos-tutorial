package ${basePackage}.<#if module??>${module}.</#if>${repositoryPackage};

import ${basePackage}<#if module??>${module}.</#if>.${entityPackage}.*;
<#if superRepository??>
   import ${superRepository};
</#if>


/**
* Created by $!{author} on $!{now}.
*/
public interface ${nameStrategy.entity(table.name)!}Repository <#if superRepository??>extends ${nameStrategy.trimPackage(superRepository)!}<${nameStrategy.entity(table.name)}></#if>{
}