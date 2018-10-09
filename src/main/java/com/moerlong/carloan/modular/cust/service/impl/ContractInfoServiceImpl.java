package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.cust.entity.vo.ContractInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.ContractInfo;
import com.moerlong.carloan.modular.cust.dao.ContractInfoDao;
import com.moerlong.carloan.modular.cust.service.ContractInfoService;

@Service
public class ContractInfoServiceImpl implements ContractInfoService{

	@Autowired
	ContractInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(ContractInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(ContractInfo entity) {
		entity.setCreateTime(new Date());
		entity.setIsDeleted(0);
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
	public void update(ContractInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(ContractInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.updateWithOutNull(entity);
	}
	
	public ContractInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<ContractInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<ContractInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<ContractInfo> pageList = mapper.selectPage(param);
		PageInfo<ContractInfo> pageInfo = new PageInfo<ContractInfo>(pageList);
		return pageInfo;
	}
	public PageInfo<ContractInfoVo> selectContractInfos(int pageSize, int pageNum,Map<String,Object> queryMap) {
		PageHelper.startPage(pageNum, pageSize);
		List<ContractInfoVo> pageList = mapper.selectContractInfos(queryMap);
		PageInfo<ContractInfoVo> pageInfo = new PageInfo<ContractInfoVo>(pageList);
		return pageInfo;
	}

	public ContractInfo selectByApplyId(Long applyid) {
		return mapper.selectByApplyId(applyid);
	}

	public ContractInfo selectLastContract(){
		return  mapper.selectLastContract();
	}

}

