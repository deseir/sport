package com.moerlong.carloan.modular.sport.dao;

import com.moerlong.carloan.modular.sport.entity.Huizong;
import com.moerlong.carloan.modular.sport.entity.SQc;

import java.util.List;
import java.util.Map;

public interface SQcMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SQc record);

    int insertSelective(SQc record);

    SQc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SQc record);

    int updateByPrimaryKey(SQc record);

    /**
     * 查询所有
     * @return
     */
    public List<SQc> listAll();
    /**
     * 分页查询
     * @param param	查询参数
     * @return
     */
    public List<SQc> selectPage(Map<String,Object> param);

    /**
     * 查询汇总
     * @param pids
     * @return
     */
    List<Huizong> selectHuizong(List<String> pids);

    /**
     * 根据器材现状统计数量
     * @param param
     * @return
     */
    Integer selectHuizongByQcxz(Map<String,Object> param);
}