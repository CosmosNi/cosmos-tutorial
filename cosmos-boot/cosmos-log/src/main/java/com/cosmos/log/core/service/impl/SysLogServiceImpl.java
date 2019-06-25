package com.cosmos.log.core.service.impl;

import com.cosmos.log.core.dto.SysLogDTO;
import com.cosmos.log.core.entity.SysLog;
import com.cosmos.log.core.repository.SysLogRepository;
import com.cosmos.log.core.service.SysLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: cosmos-log
 * @Package: com.cosmos.log.service.impl
 * @ClassName: SysLogServiceImpl
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/6 14:23
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public void saveLog(SysLogDTO sysLogDTO) {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(sysLogDTO, sysLog);
        sysLogRepository.save(sysLog);
    }
}
