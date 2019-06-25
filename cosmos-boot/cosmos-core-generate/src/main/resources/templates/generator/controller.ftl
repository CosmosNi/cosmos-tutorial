package ${basePackage}.<#if module??>${module}.</#if>${controllerPackage};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import com.kedacom.kidp.base.data.common.audit.support.AccessLog;
import com.kedacom.kidp.base.web.controller.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${entityClass!};
import ${voClass!};
import ${dtoClass!};

@RestController
@RequestMapping("${mappingPath!}")
@Slf4j
@AccessLog
@Api
public class ${controllerName!} extends BaseCrudController<${nameStrategy.trimPackage(entityClass)}, ${nameStrategy.trimPackage(dtoClass)}, ${nameStrategy.trimPackage(voClass)}>{

}