package com.moerlong.carloan.modular.cust.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.cust.dao.FamilyBookSubInfoDao;
import com.moerlong.carloan.modular.cust.entity.FamilyBookSubInfo;
import com.moerlong.carloan.modular.cust.entity.vo.FamilyBookInfoVo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.FamilyBookInfo;
import com.moerlong.carloan.modular.cust.dao.FamilyBookInfoDao;
import com.moerlong.carloan.modular.cust.service.FamilyBookInfoService;

@Service
public class FamilyBookInfoServiceImpl implements FamilyBookInfoService{
	private final Logger log = LoggerFactory.getLogger(FamilyBookInfoServiceImpl.class);
	@Autowired
	FamilyBookInfoDao mapper;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	FamilyBookSubInfoDao familyBookSubInfoDao;
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(FamilyBookInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(FamilyBookInfo entity) {
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
	public void update(FamilyBookInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(FamilyBookInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public FamilyBookInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<FamilyBookInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<FamilyBookInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<FamilyBookInfo> pageList = mapper.selectPage(param);
		PageInfo<FamilyBookInfo> pageInfo = new PageInfo<FamilyBookInfo>(pageList);
		return pageInfo;
	}
	/**
	 * 户口本信息录入
	 * cjh
	 */
	public Object familyBookInfoAdd(FamilyBookInfoVo vo){
		try {
			Date now = new Date();
			FamilyBookInfo info = new FamilyBookInfo();
			FamilyBookSubInfo familyBookSubInfo = new FamilyBookSubInfo();
			ApplyInfo applyInfo = applyInfoService.selectById(vo.getApplyId());
			if(vo.getFamilyBookInfoId() == null){
				//新增
				info.setCustId(applyInfo.getCustId());
				info.setRelationship(vo.getRelationship());
				info.setMasterName(vo.getMasterName());
				info.setMasterSex(vo.getMasterSex());
				info.setCertId(vo.getCertId());
				info.setFirstPagePhotoUrl(vo.getFirstPagePhotoUrl());
				/*System.out.println("______________setFirstPagePhotoUrl-------------->>>>>"+vo.getFirstPagePhotoUrl().split("/resource")[1]);
				if(!"".equals(vo.getFirstPagePhotoUrl())&&vo.getFirstPagePhotoUrl()!=null){
					String [] FirstPagePhotoUrl=vo.getFirstPagePhotoUrl().split("/resource");
					info.setFirstPagePhotoUrl("/resource"+FirstPagePhotoUrl[1]);
				}*/
				info.setCreateTime(now);
				info.setIsDeleted(0);
				List<FamilyBookSubInfo> list=vo.getFamilyBookSubInfolist();
				this.mapper.save(info);
				List<FamilyBookSubInfo> listNull = new ArrayList<FamilyBookSubInfo>();//用于存储新增的户口关系
				List<FamilyBookSubInfo> listNotNull = new ArrayList<FamilyBookSubInfo>();//用于存储更新的户口关系
				for (FamilyBookSubInfo bookSubInfo : list) {//把FamilyBookInfo的id保存进去，还需要判断FamilyBookSubInfo是新增还是更新
					bookSubInfo.setBookId(info.getId());
					bookSubInfo.setCreateTime(now);
					bookSubInfo.setIsDeleted(0);
					if(bookSubInfo.getId()==null){
						listNull.add(bookSubInfo);
					}else{
						listNotNull.add(bookSubInfo);
					}
				}
				if(listNull.size()>0){
					familyBookSubInfoDao.saveAll(listNull);//增加所有id为空的FamilyBookSubInfo
				}
				if(listNotNull.size()>0){
					for (FamilyBookSubInfo bookSubInfos : listNotNull) {
						bookSubInfos.setBookPhotoUrl(bookSubInfos.getBookPhotoUrl());
						/*System.out.println("______________setBookPhotoUrl-------------->>>>>"+bookSubInfos.getBookPhotoUrl().split("/resource")[1]);
						if (!"".equals(bookSubInfos.getBookPhotoUrl())&&bookSubInfos.getBookPhotoUrl()!=null){
							String [] BookPhotoUrl=bookSubInfos.getBookPhotoUrl().split("/resource");
							bookSubInfos.setBookPhotoUrl("/resource"+BookPhotoUrl[1]);
						}*/
						familyBookSubInfoDao.update(bookSubInfos);
					}
					//familyBookSubInfoDao.updateAll(listNotNull);//增加所有id为空的FamilyBookSubInfo
				}
			}else{
				//更新
				info.setId(vo.getFamilyBookInfoId());
				info.setCustId(applyInfo.getCustId());
				info.setRelationship(vo.getRelationship());
				info.setMasterName(vo.getMasterName());
				info.setMasterSex(vo.getMasterSex());
				info.setCertId(vo.getCertId());
				info.setFirstPagePhotoUrl(vo.getFirstPagePhotoUrl());
				/*System.out.println("______________setFirstPagePhotoUrl-------------->>>>>"+vo.getFirstPagePhotoUrl().split("/resource")[1]);
				if(!"".equals(vo.getFirstPagePhotoUrl())&&vo.getFirstPagePhotoUrl()!=null){
					String [] FirstPagePhotoUrl=vo.getFirstPagePhotoUrl().split("/resource");
					info.setFirstPagePhotoUrl("/resource"+FirstPagePhotoUrl[1]);
				}*/
				info.setUpdateTime(now);
				info.setIsDeleted(0);
				updateWithOutNull(info);
				List<FamilyBookSubInfo> list=vo.getFamilyBookSubInfolist();
				List<FamilyBookSubInfo> listNull = new ArrayList<FamilyBookSubInfo>();//用于存储新增的户口关系
				List<FamilyBookSubInfo> listNotNull = new ArrayList<FamilyBookSubInfo>();//用于存储更新的户口关系
				for (FamilyBookSubInfo bookSubInfo : list) {//把FamilyBookInfo的id保存进去，还需要判断FamilyBookSubInfo是新增还是更新
					bookSubInfo.setBookId(info.getId());
					bookSubInfo.setCreateTime(now);
					bookSubInfo.setIsDeleted(0);
					if(bookSubInfo.getId()==null){
						listNull.add(bookSubInfo);
					}else{
						listNotNull.add(bookSubInfo);
					}
				}
				if(listNull.size()>0){
					familyBookSubInfoDao.saveAll(listNull);//增加所有id为空的FamilyBookSubInfo
				}
				if(listNotNull.size()>0){
					for (FamilyBookSubInfo subInfo : listNotNull) {
						subInfo.setBookPhotoUrl(subInfo.getBookPhotoUrl());
						/*System.out.println("______________setBookPhotoUrl-------------->>>>>"+subInfo.getBookPhotoUrl().split("/resource")[1]);
						if (!"".equals(subInfo.getBookPhotoUrl())&&subInfo.getBookPhotoUrl()!=null){
							String [] BookPhotoUrl=subInfo.getBookPhotoUrl().split("/resource");
							subInfo.setBookPhotoUrl("/resource"+BookPhotoUrl[1]);
						}*/
						familyBookSubInfoDao.update(subInfo);
					}
					//familyBookSubInfoDao.updateAll(listNotNull);//增加所有id不为空的FamilyBookSubInfo
				}
			}
			return ResultVO.build(ErrorCode.SUCCESS);
		}catch (Exception e){
			log.error("====>[saveOrUpdateApplyInfo] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}
	/**
	 * 根据applyID户口本信息获取
	 * cjh
	 */
	public FamilyBookInfoVo familyBookInfoGetByApplyId(Long param){
		ApplyInfo aapply=applyInfoService.selectById(param);
		FamilyBookInfoVo familyBookInfoVo =new FamilyBookInfoVo();
		familyBookInfoVo.setApplyId(param);
		List<FamilyBookInfo> familyBookInfoVolist=mapper.familyBookInfoGetByApplyId(aapply.getCustId());
		if(familyBookInfoVolist.size()>0){
			List<FamilyBookSubInfo> familyBookSubInfolist=familyBookSubInfoDao.selectByBookId(familyBookInfoVolist.get(0).getId());
			familyBookInfoVo.setFamilyBookInfoId(familyBookInfoVolist.get(0).getId());
			familyBookInfoVo.setRelationship(familyBookInfoVolist.get(0).getRelationship());
			familyBookInfoVo.setMasterName(familyBookInfoVolist.get(0).getMasterName());
			familyBookInfoVo.setMasterSex(familyBookInfoVolist.get(0).getMasterSex());
			familyBookInfoVo.setCertId(familyBookInfoVolist.get(0).getCertId());
			familyBookInfoVo.setFirstPagePhotoUrl(familyBookInfoVolist.get(0).getFirstPagePhotoUrl());
			familyBookInfoVo.setFamilyBookSubInfolist(familyBookSubInfolist);
		}
		return familyBookInfoVo;
	}
}

