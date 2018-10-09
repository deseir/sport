package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SPrjBase;

import java.util.List;
import java.util.Map;

public interface SPrjBaseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SPrjBase record);

    int insertSelective(SPrjBase record);

    SPrjBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SPrjBase record);

    int updateByPrimaryKey(SPrjBase record);
    /**
     * 查询所有
     * @return
     */
    public List<SPrjBase> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SPrjBase> selectPage(Map<String,Object> param);
}