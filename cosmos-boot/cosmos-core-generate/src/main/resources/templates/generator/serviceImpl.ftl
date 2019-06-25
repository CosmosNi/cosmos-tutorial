package <#if webBasePackage??>${webBasePackage}<#else>${basePackage}</#if>.<#if module??>${module}.</#if>${serviceImplPackage};

import ${basePackage}.<#if module??>${module}.</#if>${entityPackage}.*;
import <#if webBasePackage??>${webBasePackage}<#else>${basePackage}</#if>.<#if module??>${module}.</#if>${servicePackage!}.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<#if superServiceImpl??>
import ${superServiceImpl};
</#if>


/**
* Created by $!{author} on $!{now}.
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ${nameStrategy.entity(table.name)}ServiceImpl <#if superServiceImpl??>extends ${nameStrategy.trimPackage(superServiceImpl)}<${nameStrategy.entity(table.name)}></#if>  implements ${nameStrategy.entity(table.name)}Service  {
}