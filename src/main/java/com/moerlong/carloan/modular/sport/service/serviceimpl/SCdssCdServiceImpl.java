package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SCdssCdMapper;
import com.moerlong.carloan.modular.sport.dao.SPrjBaseMapper;
import com.moerlong.carloan.modular.sport.entity.SCdssCd;
import com.moerlong.carloan.modular.sport.entity.SPrjBase;
import com.moerlong.carloan.modular.sport.service.SCdssCdService;
import com.moerlong.carloan.modular.sport.service.SPrjBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SCdssCdServiceImpl implements SCdssCdService {

	@Autowired
	SCdssCdMapper mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SCdssCd entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SCdssCd entity) {
		entity.setCreatetime(new Date());
		entity.setIsdelete(0);
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
	public void update(SCdssCd entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SCdssCd entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SCdssCd selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SCdssCd> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SCdssCd> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SCdssCd> pageList = mapper.selectPage(param);
		PageInfo<SCdssCd> pageInfo = new PageInfo<SCdssCd>(pageList);
		return pageInfo;
	}

}

