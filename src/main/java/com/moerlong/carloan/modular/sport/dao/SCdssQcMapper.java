package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssQc;

import java.util.List;
import java.util.Map;

public interface SCdssQcMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssQc record);

    int insertSelective(SCdssQc record);

    SCdssQc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssQc record);

    int updateByPrimaryKey(SCdssQc record);
    /**
     * 查询所有
     * @return
     */
    public List<SCdssQc> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssQc> selectPage(Map<String,Object> param);
}