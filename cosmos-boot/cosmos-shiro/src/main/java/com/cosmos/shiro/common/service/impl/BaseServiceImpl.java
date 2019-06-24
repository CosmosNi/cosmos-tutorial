package com.cosmos.shiro.common.service.impl;

import com.cosmos.shiro.common.repository.BaseRepository;
import com.cosmos.shiro.common.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ProjectName: cosmos-server
 * @Package: com.cosmos.service.web.impl
 * @ClassName: BaseServiceImpl
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/22 14:48
 * @Version: 1.0
 */
public class BaseServiceImpl<T, K, H> implements BaseService<T, K, H> {
    @Autowired
    protected BaseRepository<T> repository;


    public BaseServiceImpl() {
    }

    /**
     * 获取集合
     *
     * @return
     */
    @Override
    public List<K> list() {
        List<T> list = repository.findAll();
        return list.stream().map(t -> transformVO(t)).collect(Collectors.toList());
    }


    @Override
    public K findById(Long id) {

        return transformVO((T) this.repository.findById(id));
    }

    @Override
    public void saveDTO(H h) {
        this.repository.save(transformEntity(h));
    }

    @Override
    public void save(T t) {
        this.repository.save(t);
    }


    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Page<K> findAll(Pageable pageRequest) {
        Page result = this.repository.findAll(pageRequest);
        result.getContent().stream().forEach(t -> transformVO((T) t));
        return result;
    }

    @Override
    public Page<K> findAll(Example<T> example, Pageable pageRequest) {

        Page result = this.repository.findAll(example, pageRequest);
        result.getContent().stream().forEach(t -> transformVO((T) t));
        return result;
    }

    @Override
    public Page<K> findAll(Specification<T> specification, Pageable pageable) {

        Page result = this.repository.findAll(specification, pageable);
        result.getContent().stream().forEach(t -> transformVO((T) t));
        return result;
    }


    private K transformVO(T t) {

        try {
            ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class<K>) ptype.getActualTypeArguments()[1];
            K k = (K) clazz.newInstance();
            BeanUtils.copyProperties(t, k);
            return k;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private T transformEntity(H h) {

        try {
            ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class<T>) ptype.getActualTypeArguments()[0];
            T t = (T) clazz.newInstance();
            BeanUtils.copyProperties(h, t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
