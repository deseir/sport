package com.moerlong.carloan.modular.sport.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.sport.dao.SQjpicMapper;
import com.moerlong.carloan.modular.sport.entity.SQjpic;
import com.moerlong.carloan.modular.sport.service.SQjPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SQjpicServiceImpl implements SQjPicService {

	@Autowired
	SQjpicMapper mapper;

	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(SQjpic entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(SQjpic entity) {
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
	public void update(SQjpic entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKey(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(SQjpic entity) {
		entity.setUpdatetime(new Date());
		mapper.updateByPrimaryKeySelective(entity);
	}
	
	public SQjpic selectById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<SQjpic> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<SQjpic> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<SQjpic> pageList = mapper.selectPage(param);
		Map<String,Object> map = new HashMap<>();
		PageInfo<SQjpic> pageInfo = new PageInfo<SQjpic>(pageList);
		return pageInfo;
	}

}

