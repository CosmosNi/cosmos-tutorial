package com.cosmos.log.core.service;

import com.cosmos.log.core.dto.SysLogDTO;

/**
 * Created by nijiahui on 2019-06-06.
 */
public interface SysLogService {
    void saveLog(SysLogDTO sysLogDTO);
}
