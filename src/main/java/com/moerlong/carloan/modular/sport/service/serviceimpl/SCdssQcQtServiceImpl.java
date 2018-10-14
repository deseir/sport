package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SCdssQcQtMapper;
import com.moerlong.carloan.modular.sport.entity.SCdssQcQt;
import com.moerlong.carloan.modular.sport.service.SCdssQcQtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SCdssQcQtServiceImpl implements SCdssQcQtService {

	@Autowired
	SCdssQcQtMapper mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SCdssQcQt entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SCdssQcQt entity) {
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
	public void update(SCdssQcQt entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SCdssQcQt entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SCdssQcQt selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SCdssQcQt> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SCdssQcQt> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SCdssQcQt> pageList = mapper.selectPage(param);
		PageInfo<SCdssQcQt> pageInfo = new PageInfo<SCdssQcQt>(pageList);
		return pageInfo;
	}

}

