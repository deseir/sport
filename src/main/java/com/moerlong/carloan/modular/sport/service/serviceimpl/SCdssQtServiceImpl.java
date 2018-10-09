package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SCdssQtMapper;
import com.moerlong.carloan.modular.sport.entity.SCdssQc;
import com.moerlong.carloan.modular.sport.entity.SCdssQt;
import com.moerlong.carloan.modular.sport.service.SCdssQtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SCdssQtServiceImpl implements SCdssQtService {

	@Autowired
	SCdssQtMapper mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SCdssQt entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SCdssQt entity) {
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
	public void update(SCdssQt entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SCdssQt entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SCdssQt selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SCdssQt> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SCdssQt> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SCdssQt> pageList = mapper.selectPage(param);
		PageInfo<SCdssQt> pageInfo = new PageInfo<SCdssQt>(pageList);
		return pageInfo;
	}

}

