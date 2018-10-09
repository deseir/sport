package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.cust.entity.vo.FamilyCustInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.FamilyBookSubInfo;
import com.moerlong.carloan.modular.cust.dao.FamilyBookSubInfoDao;
import com.moerlong.carloan.modular.cust.service.FamilyBookSubInfoService;

@Service
public class FamilyBookSubInfoServiceImpl implements FamilyBookSubInfoService{

	@Autowired
	FamilyBookSubInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(FamilyBookSubInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(FamilyBookSubInfo entity) {
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
	public void update(FamilyBookSubInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(FamilyBookSubInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public FamilyBookSubInfo selectById(Long id) {
		return mapper.selectById(id);
	}

    @Override
    public List<FamilyCustInfoPo> selectCustByCertId(String certId) {
        return mapper.selectCustByCertId(certId);
    }

    public List<FamilyBookSubInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<FamilyBookSubInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<FamilyBookSubInfo> pageList = mapper.selectPage(param);
		PageInfo<FamilyBookSubInfo> pageInfo = new PageInfo<FamilyBookSubInfo>(pageList);
		return pageInfo;
	}

}

