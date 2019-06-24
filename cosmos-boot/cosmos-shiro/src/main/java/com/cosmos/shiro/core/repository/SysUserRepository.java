package com.cosmos.shiro.core.repository;

import com.cosmos.shiro.common.repository.BaseRepository;
import com.cosmos.shiro.core.entity.SysUser;

import java.util.Optional;


/**
 * Created by $!{author} on $!{now}.
 */
public interface SysUserRepository extends BaseRepository<SysUser> {
    Optional<SysUser> findByUsername(String userName);

}