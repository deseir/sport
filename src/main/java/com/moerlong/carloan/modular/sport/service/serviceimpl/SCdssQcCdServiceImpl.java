package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SCdssQcCdMapper;
import com.moerlong.carloan.modular.sport.entity.SCdssQcCd;
import com.moerlong.carloan.modular.sport.service.SCdssQcCdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SCdssQcCdServiceImpl implements SCdssQcCdService {

	@Autowired
	SCdssQcCdMapper mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SCdssQcCd entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SCdssQcCd entity) {
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
	public void update(SCdssQcCd entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SCdssQcCd entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SCdssQcCd selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SCdssQcCd> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SCdssQcCd> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SCdssQcCd> pageList = mapper.selectPage(param);
		PageInfo<SCdssQcCd> pageInfo = new PageInfo<SCdssQcCd>(pageList);
		return pageInfo;
	}

}

