package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.SQjpic;

import java.util.List;
import java.util.Map;

public interface SQjpicMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SQjpic record);

    int insertSelective(SQjpic record);

    SQjpic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SQjpic record);

    int updateByPrimaryKey(SQjpic record);

    /**
     * 查询所有
     * @return
     */
    public List<SQjpic> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SQjpic> selectPage(Map<String,Object> param);
}