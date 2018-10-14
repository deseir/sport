package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssQcQt;

import java.util.List;
import java.util.Map;

public interface SCdssQcQtMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssQcQt record);

    int insertSelective(SCdssQcQt record);


    SCdssQcQt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssQcQt record);

    int updateByPrimaryKey(SCdssQcQt record);

    /**
     * 查询所有
     * @return
     */
    public List<SCdssQcQt> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssQcQt> selectPage(Map<String,Object> param);
}