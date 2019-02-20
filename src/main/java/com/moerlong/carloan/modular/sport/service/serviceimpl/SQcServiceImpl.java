package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SQcAttachMapper;
import com.moerlong.carloan.modular.sport.dao.SQcMapper;
import com.moerlong.carloan.modular.sport.entity.Huizong;
import com.moerlong.carloan.modular.sport.entity.SQc;
import com.moerlong.carloan.modular.sport.entity.SQcAttach;
import com.moerlong.carloan.modular.sport.service.SQcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SQcServiceImpl implements SQcService {

	@Autowired
	SQcMapper mapper;

	@Autowired
	SQcAttachMapper attachMapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SQc entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SQc entity) {
		entity.setCreatetime(new Date());
		entity.setIsdeleted(0);
		mapper.insert(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void deleteLogic(Integer id){
		mapper.deleteByPrimaryKey(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void update(SQc entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SQc entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SQc selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SQc> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SQc> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SQc> pageList = mapper.selectPage(param);
		Map<String,Object> map = new HashMap<>();

		for (SQc qc :pageList) {
			map.put("qcid",qc.getId());
			List<SQcAttach> attachList = attachMapper.selectPage(map);
			String picUrl = "/resource/pics/default.jpg";
			if(null != attachList && attachList.size()>0){
				picUrl = attachList.get(0).getPicurl();
				if(null == picUrl || "".equals(picUrl)){
					picUrl = "/resource/pics/default.jpg";
				}

			}
			qc.setPicUrl(picUrl);
		}

		PageInfo<SQc> pageInfo = new PageInfo<SQc>(pageList);
		return pageInfo;
	}

	/**
	 * 查询每个街道的汇总
	 * @param pageSize
	 * @param pageNum
	 * @param pids
	 * @return
	 */
	public PageInfo<Huizong> selectHuizong(int pageSize,int pageNum,List<String> pids){
		PageHelper.startPage(pageNum, pageSize);
		List<Huizong> list = mapper.selectHuizong(pids);
		Map<String,Object> params = new HashMap<>();
		for(Huizong huizong : list){
			params.put("deptpid",huizong.getDeptId());
			params.put("qcxz","正常使用");
			Integer normal = mapper.selectHuizongByQcxz(params);
			huizong.setNormal(normal);
			params.put("qcxz","损坏");
			Integer badCount = mapper.selectHuizongByQcxz(params);
			huizong.setBadCount(badCount);
			params.put("qcxz","维修");
			Integer weixiu = mapper.selectHuizongByQcxz(params);
			huizong.setWeixiu(weixiu);
			params.put("qcxz","拆除");
			Integer  chaichu= mapper.selectHuizongByQcxz(params);
			huizong.setChaichu(chaichu);
		}

		PageInfo<Huizong> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}

