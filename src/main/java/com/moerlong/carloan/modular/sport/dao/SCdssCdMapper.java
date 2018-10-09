package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssCd;

import java.util.List;
import java.util.Map;

public interface SCdssCdMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssCd record);

    int insertSelective(SCdssCd record);

    SCdssCd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssCd record);

    int updateByPrimaryKey(SCdssCd record);
    /**
     * 查询所有
     * @return
     */
    public List<SCdssCd> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssCd> selectPage(Map<String,Object> param);
}