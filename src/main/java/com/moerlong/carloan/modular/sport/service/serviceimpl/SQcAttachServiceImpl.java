package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SQcAttachMapper;
import com.moerlong.carloan.modular.sport.entity.SQcAttach;
import com.moerlong.carloan.modular.sport.service.SQcAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SQcAttachServiceImpl implements SQcAttachService {

	@Autowired
	SQcAttachMapper mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SQcAttach entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SQcAttach entity) {
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
	public void update(SQcAttach entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SQcAttach entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SQcAttach selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SQcAttach> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SQcAttach> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SQcAttach> pageList = mapper.selectPage(param);
		PageInfo<SQcAttach> pageInfo = new PageInfo<SQcAttach>(pageList);
		return pageInfo;
	}

	public int deleteByIds(List list){
		return mapper.deleteByIds(list);
	}

}

