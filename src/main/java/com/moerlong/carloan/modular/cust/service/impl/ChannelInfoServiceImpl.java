package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.ChannelInfo;
import com.moerlong.carloan.modular.cust.dao.ChannelInfoDao;
import com.moerlong.carloan.modular.cust.service.ChannelInfoService;

@Service
public class ChannelInfoServiceImpl implements ChannelInfoService{

	@Autowired
	ChannelInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(ChannelInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(ChannelInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void delete(Long id) {
		mapper.delete(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void deleteLogic(Long id){
		mapper.deleteLogic(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void update(ChannelInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(ChannelInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public ChannelInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<ChannelInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<ChannelInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChannelInfo> pageList = mapper.selectPage(param);
		PageInfo<ChannelInfo> pageInfo = new PageInfo<ChannelInfo>(pageList);
		return pageInfo;
	}

    @Override
    public PageInfo<ChannelInfo> selectPage(int pageSize, int pageNum, String channelName) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChannelInfo> pageList = mapper.selectPage(channelName);
		PageInfo<ChannelInfo> pageInfo = new PageInfo<ChannelInfo>(pageList);
		return pageInfo;
    }

}

