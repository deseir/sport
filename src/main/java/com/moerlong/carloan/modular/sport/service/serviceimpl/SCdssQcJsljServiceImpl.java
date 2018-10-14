package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SCdssQcJsljMapper;
import com.moerlong.carloan.modular.sport.entity.SCdssQcJslj;
import com.moerlong.carloan.modular.sport.service.SCdssQcJsljService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SCdssQcJsljServiceImpl implements SCdssQcJsljService {

	@Autowired
	SCdssQcJsljMapper mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SCdssQcJslj entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SCdssQcJslj entity) {
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
	public void update(SCdssQcJslj entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SCdssQcJslj entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SCdssQcJslj selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SCdssQcJslj> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SCdssQcJslj> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SCdssQcJslj> pageList = mapper.selectPage(param);
		PageInfo<SCdssQcJslj> pageInfo = new PageInfo<SCdssQcJslj>(pageList);
		return pageInfo;
	}

}

