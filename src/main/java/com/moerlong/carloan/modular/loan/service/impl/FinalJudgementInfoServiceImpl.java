package com.moerlong.carloan.modular.loan.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.FinalJudgementInfo;
import com.moerlong.carloan.modular.loan.dao.FinalJudgementInfoDao;
import com.moerlong.carloan.modular.loan.service.FinalJudgementInfoService;

@Service
public class FinalJudgementInfoServiceImpl implements FinalJudgementInfoService{

	@Autowired
	FinalJudgementInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(FinalJudgementInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			entity.setAuditTime(new Date());
			this.updateWithOutNull(entity);
		}else {
			entity.setAuditTime(new Date());
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(FinalJudgementInfo entity) {
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
	public void update(FinalJudgementInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(FinalJudgementInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public FinalJudgementInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<FinalJudgementInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<FinalJudgementInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<FinalJudgementInfo> pageList = mapper.selectPage(param);
		PageInfo<FinalJudgementInfo> pageInfo = new PageInfo<FinalJudgementInfo>(pageList);
		return pageInfo;
	}

	@Override
	public FinalJudgementInfo selectByApplyId(Long applyId) {
		return mapper.selectByApplyId(applyId);
	}

}

