package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssJslj;

import java.util.List;
import java.util.Map;

public interface SCdssJsljMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssJslj record);

    int insertSelective(SCdssJslj record);

    SCdssJslj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssJslj record);

    int updateByPrimaryKey(SCdssJslj record);
    /**
     * 查询所有
     * @return
     */
    public List<SCdssJslj> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssJslj> selectPage(Map<String,Object> param);
}