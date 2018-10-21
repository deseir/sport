package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SAttach;

import java.util.List;
import java.util.Map;

public interface SAttachMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SAttach record);

    int insertSelective(SAttach record);

    SAttach selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SAttach record);

    int updateByPrimaryKey(SAttach record);
    /**
     * 查询所有
     * @return
     */
    public List<SAttach> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SAttach> selectPage(Map<String,Object> param);
}