package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssQcCd;

import java.util.List;
import java.util.Map;

public interface SCdssQcCdMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssQcCd record);

    int insertSelective(SCdssQcCd record);

    SCdssQcCd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssQcCd record);

    int updateByPrimaryKey(SCdssQcCd record);

    /**
     * 查询所有
     * @return
     */
    public List<SCdssQcCd> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssQcCd> selectPage(Map<String,Object> param);
}