package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssQcJslj;

import java.util.List;
import java.util.Map;

public interface SCdssQcJsljMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssQcJslj record);

    int insertSelective(SCdssQcJslj record);

    SCdssQcJslj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssQcJslj record);

    int updateByPrimaryKey(SCdssQcJslj record);

    /**
     * 查询所有
     * @return
     */
    public List<SCdssQcJslj> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssQcJslj> selectPage(Map<String,Object> param);
}