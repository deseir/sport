package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SQcAttach;

import java.util.List;
import java.util.Map;

public interface SQcAttachMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SQcAttach record);

    int insertSelective(SQcAttach record);


    SQcAttach selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SQcAttach record);

    int updateByPrimaryKey(SQcAttach record);

    /**
     * 查询所有
     * @return
     */
    public List<SQcAttach> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SQcAttach> selectPage(Map<String,Object> param);

    public int deleteByIds(List list);
}