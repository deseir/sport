package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SCdssQt;

import java.util.List;
import java.util.Map;

public interface SCdssQtMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SCdssQt record);

    int insertSelective(SCdssQt record);

    SCdssQt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SCdssQt record);

    int updateByPrimaryKey(SCdssQt record);
    /**
     * 查询所有
     * @return
     */
    public List<SCdssQt> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SCdssQt> selectPage(Map<String,Object> param);
}