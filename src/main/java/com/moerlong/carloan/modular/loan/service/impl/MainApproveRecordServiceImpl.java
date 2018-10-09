package com.moerlong.carloan.modular.loan.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.dao.MainApproveRecordDao;
import com.moerlong.carloan.modular.loan.service.MainApproveRecordService;

@Service
public class MainApproveRecordServiceImpl implements MainApproveRecordService{

	@Autowired
	MainApproveRecordDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(MainApproveRecord entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(MainApproveRecord entity) {
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
	public void update(MainApproveRecord entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(MainApproveRecord entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public MainApproveRecord selectById(Long id) {
		return mapper.selectById(id);
	}

    @Override
    public List<MainApproveRecord> selectByApplyId(Long applyId) {
        return mapper.selectByApplyId(applyId);
    }
    public List<MainApproveRecord> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<MainApproveRecord> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<MainApproveRecord> pageList = mapper.selectPage(param);
		PageInfo<MainApproveRecord> pageInfo = new PageInfo<MainApproveRecord>(pageList);
		return pageInfo;
	}

	public List<MainApproveRecord> getTop10(Map<String,Object> param){
		return mapper.getTop10(param);
	}

}

