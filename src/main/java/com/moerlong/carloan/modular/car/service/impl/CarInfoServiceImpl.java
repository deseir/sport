package com.moerlong.carloan.modular.car.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.car.dao.*;
import com.moerlong.carloan.modular.car.entity.*;
import com.moerlong.carloan.modular.car.entity.vo.CarInfoVo;
import com.moerlong.carloan.modular.car.entity.vo.InitCarVerifyVo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.car.service.CarInfoService;

@Service
public class CarInfoServiceImpl implements CarInfoService{
	private final Logger log = LoggerFactory.getLogger(CarInfoServiceImpl.class);
	@Autowired
	CarInfoDao mapper;
	@Autowired
	ApplyInfoService applyInfoService;
	@Autowired
	CarDriverInfoDao carDriverInfoDao;
	@Autowired
	CarPeccancyInfoDao carPeccancyInfoDao;
	@Autowired
	CarTrafficInsureInfoDao carTrafficInsureInfoDao;
	@Autowired
	CarBussInsureInfoDao carBussInsureInfoDao;
	@Autowired
	CarTransferInfoDao carTransferInfoDao;
	@Autowired
    CarMortgageInfoDao carMortgageInfoDao;
	@Autowired
	CarInsureDetailInfoDao carInsureDetailInfoDao;
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CarInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			this.update(entity);
		}else {
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CarInfo entity) {
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
	public void update(CarInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CarInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CarInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CarInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CarInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CarInfo> pageList = mapper.selectPage(param);
		PageInfo<CarInfo> pageInfo = new PageInfo<CarInfo>(pageList);
		return pageInfo;
	}
	/**
	 * cjh
	 * 保存内勤资料录入车辆基本信息
	 *
	 * @return
	 */
	public Object addSaveOrUpdateCarInfo(CarInfoVo vo){
		try {
			Date now = new Date();
			ApplyInfo applyInfo = applyInfoService.selectById(vo.getApplyId());
			CarInfo carInfo = new CarInfo();
			if(vo.getCarInfoVoId() == null){
				//新增
				carInfo.setCustId(applyInfo.getCustId());
				carInfo.setCarConfigName(vo.getCarConfigName());
				carInfo.setCarNum(vo.getCarNum());
				carInfo.setCurrentLicDate(vo.getCurrentLicDate());
				carInfo.setCarType(vo.getCarType());
				carInfo.setCarBrand(vo.getCarBrand());
				carInfo.setCarModel(vo.getCarModel());
				carInfo.setVin(vo.getVin());
				carInfo.setEngineNo(vo.getEngineNo());
				carInfo.setCarColor(vo.getCarColor());
				carInfo.setCarImportType(vo.getCarImportType());
				carInfo.setFuelType(vo.getFuelType());
				carInfo.setDisplacement(vo.getDisplacement());
				carInfo.setManufacturer(vo.getManufacturer());
				carInfo.setCarUsage(vo.getCarUsage());
				carInfo.setGetType(vo.getGetType());
				carInfo.setProductDate(vo.getProductDate());
				carInfo.setFirstLicDate(vo.getFirstLicDate());
				carInfo.setMileage(vo.getMileage());

				carInfo.setRegisterPhotoUrl1(vo.getRegisterPhotoUrl1());
				carInfo.setRegisterPhotoUrl2(vo.getRegisterPhotoUrl2());
				carInfo.setRegisterPhotoUrl3(vo.getRegisterPhotoUrl3());
				carInfo.setRegisterPhotoUrl4(vo.getRegisterPhotoUrl4());
//				System.out.println("==================>>>>>>"+vo.getRegisterPhotoUrl1());
//				if(!"".equals(vo.getRegisterPhotoUrl1())&&vo.getRegisterPhotoUrl1()!=null){
//					carInfo.setRegisterPhotoUrl1("/resource"+vo.getRegisterPhotoUrl1().split("/resource")[1]);
//				}
//				if(!"".equals(vo.getRegisterPhotoUrl2())&&vo.getRegisterPhotoUrl2()!=null){
//					carInfo.setRegisterPhotoUrl2("/resource"+vo.getRegisterPhotoUrl2().split("/resource")[1]);
//				}
//				if(!"".equals(vo.getRegisterPhotoUrl3())&&vo.getRegisterPhotoUrl3()!=null){
//					carInfo.setRegisterPhotoUrl3("/resource"+vo.getRegisterPhotoUrl3().split("/resource")[1]);
//				}
//				if(!"".equals(vo.getRegisterPhotoUrl4())&&vo.getRegisterPhotoUrl4()!=null){
//					carInfo.setRegisterPhotoUrl4("/resource"+vo.getRegisterPhotoUrl4().split("/resource")[1]);
//				}
				carInfo.setCarLocationId(vo.getCarLocationId());
				carInfo.setCarLocationName(vo.getCarLocationName());
				carInfo.setCarCityId(vo.getCarCityId());
				carInfo.setCarCityName(vo.getCarCityName());
				carInfo.setCarSeriesId(vo.getCarSeriesId());
				carInfo.setCarSeriesName(vo.getCarSeriesName());
				carInfo.setCarModelId(vo.getCarModelId());
				carInfo.setCarModelName(vo.getCarModelName());
				carInfo.setRegistrationCarType(vo.getRegistrationCarType());
				carInfo.setCreateTime(now);
				carInfo.setIsDeleted(0);
				this.mapper.save(carInfo);
				//保存行驶证驾驶证信息表信息
				CarDriverInfo carDriverInfo=vo.getCarDriverInfo();
				if(carDriverInfo!=null){
					carDriverInfo.setCarId(carInfo.getId());
					carDriverInfo.setUserId(applyInfo.getCustId());
					carDriverInfo.setCreateTime(now);
					carDriverInfo.setIsDeleted(0);

					carDriverInfo.setDriverFrontPhoto(carDriverInfo.getDriverFrontPhoto());
					carDriverInfo.setDriverBackPhoto(carDriverInfo.getDriverBackPhoto());
					carDriverInfo.setVehicleFrontPhoto(carDriverInfo.getVehicleFrontPhoto());
					carDriverInfo.setVehicleBackPhoto(carDriverInfo.getVehicleBackPhoto());
					/*if(!"".equals(carDriverInfo.getDriverFrontPhoto())&&carDriverInfo.getDriverFrontPhoto()!=null){
						carDriverInfo.setDriverFrontPhoto("/resource"+carDriverInfo.getDriverFrontPhoto().split("/resource")[1]);
					}
					if(!"".equals(carDriverInfo.getDriverBackPhoto())&&carDriverInfo.getDriverBackPhoto()!=null){
						carDriverInfo.setDriverBackPhoto("/resource"+carDriverInfo.getDriverBackPhoto().split("/resource")[1]);
					}
					if(!"".equals(carDriverInfo.getVehicleFrontPhoto())&&carDriverInfo.getVehicleFrontPhoto()!=null){
						carDriverInfo.setVehicleFrontPhoto("/resource"+carDriverInfo.getVehicleFrontPhoto().split("/resource")[1]);
					}
					if(!"".equals(carDriverInfo.getVehicleBackPhoto())&&carDriverInfo.getVehicleBackPhoto()!=null){
						carDriverInfo.setVehicleBackPhoto("/resource"+carDriverInfo.getVehicleBackPhoto().split("/resource")[1]);
					}*/
					carDriverInfoDao.save(carDriverInfo);
				}
				//保存车辆违章信息
				CarPeccancyInfo carPeccancyInfo=vo.getCarPeccancyInfo();
				if(carPeccancyInfo!=null){
					carPeccancyInfo.setCarId(carInfo.getId());
					carPeccancyInfo.setCreateTime(now);
					carPeccancyInfo.setIsDeleted(0);
					carPeccancyInfoDao.save(vo.getCarPeccancyInfo());
				}
				//车辆交强险信息表
				CarTrafficInsureInfo carTrafficInsureInfo=vo.getCarTrafficInsureInfo();
				if(carTrafficInsureInfo!=null){
					carTrafficInsureInfo.setCarId(carInfo.getId());
					carTrafficInsureInfo.setCreateTime(now);
					carTrafficInsureInfo.setIsDeleted(0);

					carTrafficInsureInfo.setPhotoUrl(carTrafficInsureInfo.getPhotoUrl());
					/*if(!"".equals(carTrafficInsureInfo.getPhotoUrl())&&carTrafficInsureInfo.getPhotoUrl()!=null){
						carTrafficInsureInfo.setPhotoUrl("/resource"+carTrafficInsureInfo.getPhotoUrl().split("/resource")[1]);
					}*/
					carTrafficInsureInfoDao.save(vo.getCarTrafficInsureInfo());
				}
				//车辆商业险信息表
				CarBussInsureInfo carBussInsureInfo=vo.getCarBussInsureInfo();
				if(carBussInsureInfo!=null){
					carBussInsureInfo.setCarId(carInfo.getId());
					carBussInsureInfo.setCreateTime(now);
					carBussInsureInfo.setIsDeleted(0);

					carBussInsureInfo.setPhotoUrl(carBussInsureInfo.getPhotoUrl());
					/*if(!"".equals(carBussInsureInfo.getPhotoUrl())&&carBussInsureInfo.getPhotoUrl()!=null){
						carBussInsureInfo.setPhotoUrl("/resource"+carBussInsureInfo.getPhotoUrl().split("/resource")[1]);
					}*/
					carBussInsureInfoDao.save(vo.getCarBussInsureInfo());
				}
				//车辆转移登记记录表
				List<CarTransferInfo> carTransferInfoList =vo.getCarTransferInfolist();
				if(carTransferInfoList!=null&&carTransferInfoList.size()>0){
					for (CarTransferInfo carTransferInfo : carTransferInfoList) {
						carTransferInfo.setCarId(carInfo.getId());
						carTransferInfo.setCreateTime(now);
						carTransferInfo.setIsDeleted(0);
					}
					carTransferInfoDao.saveAll(carTransferInfoList);
				}
				//车辆抵押记录表
				List<CarMortgageInfo> carMortgageInfoList=vo.getCarMortgageInfoList();
				if(carMortgageInfoList!=null&&carMortgageInfoList.size()>0){
					for (CarMortgageInfo carMortgageInfo : carMortgageInfoList) {
						carMortgageInfo.setCarId(carInfo.getId());
						carMortgageInfo.setCreateTime(now);
						carMortgageInfo.setIsDeleted(0);
						carMortgageInfoDao.save(carMortgageInfo);
					}
					//carMortgageInfoDao.saveAll(carMortgageInfoList);
				}
				//车辆保险险种详细信息表
				List<CarInsureDetailInfo> carInsureDetailInfoList=vo.getCarInsureDetailInfoList();
				if(carInsureDetailInfoList!=null&&carInsureDetailInfoList.size()>0){
					for (CarInsureDetailInfo carInsureDetailInfo : carInsureDetailInfoList) {
						carInsureDetailInfo.setIsDeleted(0);
						carInsureDetailInfo.setCreateTime(now);
						carInsureDetailInfo.setInsureId(carBussInsureInfo.getId());
					}
					carInsureDetailInfoDao.saveAll(carInsureDetailInfoList);
				}

			}else{
				//更新
				carInfo.setId(vo.getCarInfoVoId());
				carInfo.setCustId(applyInfo.getCustId());
				carInfo.setCarConfigName(vo.getCarConfigName());
				carInfo.setCarNum(vo.getCarNum());
				carInfo.setCurrentLicDate(vo.getCurrentLicDate());
				carInfo.setCarType(vo.getCarType());
				carInfo.setCarBrand(vo.getCarBrand());
				carInfo.setCarModel(vo.getCarModel());
				carInfo.setVin(vo.getVin());
				carInfo.setEngineNo(vo.getEngineNo());
				carInfo.setCarColor(vo.getCarColor());
				carInfo.setCarImportType(vo.getCarImportType());
				carInfo.setFuelType(vo.getFuelType());
				carInfo.setDisplacement(vo.getDisplacement());
				carInfo.setManufacturer(vo.getManufacturer());
				carInfo.setCarUsage(vo.getCarUsage());
				carInfo.setGetType(vo.getGetType());
				carInfo.setProductDate(vo.getProductDate());
				carInfo.setFirstLicDate(vo.getFirstLicDate());
				carInfo.setMileage(vo.getMileage());

				carInfo.setRegisterPhotoUrl1(vo.getRegisterPhotoUrl1());
				carInfo.setRegisterPhotoUrl2(vo.getRegisterPhotoUrl2());
				carInfo.setRegisterPhotoUrl3(vo.getRegisterPhotoUrl3());

				/*if(!"".equals(vo.getRegisterPhotoUrl1())&&vo.getRegisterPhotoUrl1()!=null){
					carInfo.setRegisterPhotoUrl1("/resource"+vo.getRegisterPhotoUrl1().split("/resource")[1]);
				}
				if(!"".equals(vo.getRegisterPhotoUrl2())&&vo.getRegisterPhotoUrl2()!=null){
					carInfo.setRegisterPhotoUrl2("/resource"+vo.getRegisterPhotoUrl2().split("/resource")[1]);
				}
				if(!"".equals(vo.getRegisterPhotoUrl3())&&vo.getRegisterPhotoUrl3()!=null){
					carInfo.setRegisterPhotoUrl3("/resource"+vo.getRegisterPhotoUrl3().split("/resource")[1]);
				}
				if(!"".equals(vo.getRegisterPhotoUrl4())&&vo.getRegisterPhotoUrl4()!=null){
					carInfo.setRegisterPhotoUrl4("/resource"+vo.getRegisterPhotoUrl4().split("/resource")[1]);
				}*/
				carInfo.setCarLocationId(vo.getCarLocationId());
				carInfo.setCarLocationName(vo.getCarLocationName());
				carInfo.setCarCityId(vo.getCarCityId());
				carInfo.setCarCityName(vo.getCarCityName());
				carInfo.setCarSeriesId(vo.getCarSeriesId());
				carInfo.setCarSeriesName(vo.getCarSeriesName());
				carInfo.setCarModelId(vo.getCarModelId());
				carInfo.setCarModelName(vo.getCarModelName());
				carInfo.setRegistrationCarType(vo.getRegistrationCarType());
				carInfo.setCreateTime(now);
				carInfo.setIsDeleted(0);
				updateWithOutNull(carInfo);
				//保存行驶证驾驶证信息表信息
				CarDriverInfo carDriverInfo=vo.getCarDriverInfo();
				if (carDriverInfo!=null&&carDriverInfo.getId()==null){
					carDriverInfo.setCarId(carInfo.getId());
					carDriverInfo.setUserId(applyInfo.getCustId());
					carDriverInfo.setCreateTime(now);
					carDriverInfo.setIsDeleted(0);

					/*carDriverInfo.setDriverFrontPhoto(carDriverInfo.getDriverFrontPhoto());
					carDriverInfo.setDriverBackPhoto(carDriverInfo.getDriverBackPhoto());
					carDriverInfo.setVehicleFrontPhoto(carDriverInfo.getVehicleFrontPhoto());
					carDriverInfo.setVehicleBackPhoto(carDriverInfo.getVehicleBackPhoto());
					*//*if(!"".equals(carDriverInfo.getDriverFrontPhoto())&&carDriverInfo.getDriverFrontPhoto()!=null){
						carDriverInfo.setDriverFrontPhoto("/resource"+carDriverInfo.getDriverFrontPhoto().split("/resource")[1]);
					}
					if(!"".equals(carDriverInfo.getDriverBackPhoto())&&carDriverInfo.getDriverBackPhoto()!=null){
						carDriverInfo.setDriverBackPhoto("/resource"+carDriverInfo.getDriverBackPhoto().split("/resource")[1]);
					}
					if(!"".equals(carDriverInfo.getVehicleFrontPhoto())&&carDriverInfo.getVehicleFrontPhoto()!=null){
						carDriverInfo.setVehicleFrontPhoto("/resource"+carDriverInfo.getVehicleFrontPhoto().split("/resource")[1]);
					}
					if(!"".equals(carDriverInfo.getVehicleBackPhoto())&&carDriverInfo.getVehicleBackPhoto()!=null){
						carDriverInfo.setVehicleBackPhoto("/resource"+carDriverInfo.getVehicleBackPhoto().split("/resource")[1]);
					}*/
					carDriverInfoDao.save(carDriverInfo);
				}else if(carDriverInfo!=null&&carDriverInfo.getId()!=null){
					carDriverInfo.setUpdateTime(now);
					carDriverInfoDao.updateWithOutNull(carDriverInfo);
				}
				//保存车辆违章信息
				CarPeccancyInfo carPeccancyInfo=vo.getCarPeccancyInfo();
				if(carPeccancyInfo!=null&&carPeccancyInfo.getId()==null){
					carPeccancyInfo.setCarId(carInfo.getId());
					carPeccancyInfo.setCreateTime(now);
					carPeccancyInfo.setIsDeleted(0);
					carPeccancyInfoDao.save(vo.getCarPeccancyInfo());
				}else if(carPeccancyInfo!=null&&carPeccancyInfo.getId()!=null){
					carPeccancyInfo.setUpdateTime(now);
					carPeccancyInfoDao.updateWithOutNull(vo.getCarPeccancyInfo());
				}
				//车辆交强险信息表
				CarTrafficInsureInfo carTrafficInsureInfo=vo.getCarTrafficInsureInfo();
				if(carTrafficInsureInfo!=null&&carTrafficInsureInfo.getId()==null){
					carTrafficInsureInfo.setCarId(carInfo.getId());
					carTrafficInsureInfo.setCreateTime(now);
					carTrafficInsureInfo.setIsDeleted(0);
					//carTrafficInsureInfo.setPhotoUrl(carTrafficInsureInfo.getPhotoUrl());
					/*if(!"".equals(carTrafficInsureInfo.getPhotoUrl())&&carTrafficInsureInfo.getPhotoUrl()!=null){
						carTrafficInsureInfo.setPhotoUrl("/resource"+carTrafficInsureInfo.getPhotoUrl().split("/resource")[1]);
					}*/
					carTrafficInsureInfoDao.save(vo.getCarTrafficInsureInfo());
				}else if(carTrafficInsureInfo!=null&&carTrafficInsureInfo.getId()!=null){
					carTrafficInsureInfo.setUpdateTime(now);
					/*if(!"".equals(carTrafficInsureInfo.getPhotoUrl())&&carTrafficInsureInfo.getPhotoUrl()!=null){
						carTrafficInsureInfo.setPhotoUrl("/resource"+carTrafficInsureInfo.getPhotoUrl().split("/resource")[1]);
					}*/
					carTrafficInsureInfoDao.updateWithOutNull(vo.getCarTrafficInsureInfo());
				}
				//车辆商业险信息表
				CarBussInsureInfo carBussInsureInfo=vo.getCarBussInsureInfo();
				if (carBussInsureInfo!=null&&carBussInsureInfo.getId()==null){
					carBussInsureInfo.setCarId(carInfo.getId());
					carBussInsureInfo.setCreateTime(now);
					carBussInsureInfo.setIsDeleted(0);
					/*if(!"".equals(carBussInsureInfo.getPhotoUrl())&&carBussInsureInfo.getPhotoUrl()!=null){
						carBussInsureInfo.setPhotoUrl("/resource"+carBussInsureInfo.getPhotoUrl().split("/resource")[1]);
					}*/
					carBussInsureInfoDao.save(vo.getCarBussInsureInfo());
				}else if(carBussInsureInfo!=null&&carBussInsureInfo.getId()!=null){
					carBussInsureInfo.setUpdateTime(now);
					/*if(!"".equals(carBussInsureInfo.getPhotoUrl())&&carBussInsureInfo.getPhotoUrl()!=null){
						carBussInsureInfo.setPhotoUrl("/resource"+carBussInsureInfo.getPhotoUrl().split("/resource")[1]);
					}*/
					carBussInsureInfoDao.updateWithOutNull(vo.getCarBussInsureInfo());
				}
				//车辆转移登记记录表
				List<CarTransferInfo> carTransferInfoList =vo.getCarTransferInfolist();
				List<CarTransferInfo> carTransferInfoListNull = new ArrayList<CarTransferInfo>();
				List<CarTransferInfo> carTransferInfoListNotNull = new ArrayList<CarTransferInfo>();
				if(carTransferInfoList!=null){
					for (CarTransferInfo carTransferInfo : carTransferInfoList) {
						if(carTransferInfo.getId()==null){
							carTransferInfo.setCarId(carInfo.getId());
							carTransferInfo.setIsDeleted(0);
							carTransferInfo.setCreateTime(now);
							carTransferInfoListNull.add(carTransferInfo);
						}else{
							carTransferInfo.setCarId(carInfo.getId());
							carTransferInfo.setUpdateTime(now);
							carTransferInfoListNotNull.add(carTransferInfo);
						}
					}
				}
				if(carTransferInfoListNull.size()>0){
					carTransferInfoDao.saveAll(carTransferInfoListNull);
				}
				if(carTransferInfoListNotNull.size()>0){
					for (CarTransferInfo carTransferInfo : carTransferInfoListNotNull) {
						carTransferInfoDao.update(carTransferInfo);
					}
					//carTransferInfoDao.updateAll(carTransferInfoListNotNull);
				}
				//车辆抵押记录表
				List<CarMortgageInfo> carMortgageInfoList=vo.getCarMortgageInfoList();
				List<CarMortgageInfo> carMortgageInfoListNull = new ArrayList<CarMortgageInfo>();
				List<CarMortgageInfo> carMortgageInfoListNotNull = new ArrayList<CarMortgageInfo>();
				if(carMortgageInfoList!=null){
					for (CarMortgageInfo carMortgageInfo : carMortgageInfoList) {
						carMortgageInfo.setCarId(carInfo.getId());
						if(carMortgageInfo.getId()==null){
							carMortgageInfo.setIsDeleted(0);
							carMortgageInfo.setCreateTime(now);
							carMortgageInfoListNull.add(carMortgageInfo);
						}else{
							carMortgageInfo.setUpdateTime(now);
							carMortgageInfoListNotNull.add(carMortgageInfo);
						}
					}
				}
				if(carMortgageInfoListNull.size()>0){
					carMortgageInfoDao.saveAll(carMortgageInfoListNull);
				}
				if(carMortgageInfoListNotNull.size()>0){
					for (CarMortgageInfo carMortgageInfo : carMortgageInfoListNotNull) {
						carMortgageInfoDao.update(carMortgageInfo);
					}
					//carMortgageInfoDao.updateAll(carMortgageInfoListNotNull);
				}
				//车辆保险险种详细信息表
				List<CarInsureDetailInfo> carInsureDetailInfoList=vo.getCarInsureDetailInfoList();
				List<CarInsureDetailInfo> carInsureDetailInfoNull = new ArrayList<CarInsureDetailInfo>();
				List<CarInsureDetailInfo> carInsureDetailInfoNotNull = new ArrayList<CarInsureDetailInfo>();
				if(carInsureDetailInfoList!=null){
					for (CarInsureDetailInfo carInsureDetailInfo : carInsureDetailInfoList) {
						carInsureDetailInfo.setInsureId(carBussInsureInfo.getId());
						carInsureDetailInfo.setCreateTime(now);
						carInsureDetailInfo.setIsDeleted(0);
						if(carInsureDetailInfo.getId()==null){
							carInsureDetailInfoNull.add(carInsureDetailInfo);
						}else{
							carInsureDetailInfoNotNull.add(carInsureDetailInfo);
						}
					}
				}
				if(carInsureDetailInfoNull.size()>0){
					carInsureDetailInfoDao.saveAll(carInsureDetailInfoNull);
				}
				if(carInsureDetailInfoNotNull.size()>0){
					for (CarInsureDetailInfo carInsureDetailInfo : carInsureDetailInfoNotNull) {
						carInsureDetailInfoDao.update(carInsureDetailInfo);
					}
					//carInsureDetailInfoDao.updateAll(carInsureDetailInfoNotNull);
				}
			}
			return ResultVO.build(ErrorCode.SUCCESS);
		}catch (Exception e){
			log.error("====>[saveOrUpdateApplyInfo] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}
	/**
	 * cjh
	 * 获取内勤资料录入车辆基本信息根据ApplyId
	 *
	 * @return
	 */
	public CarInfoVo findCarInfoByApplyId(Long ApplyId){
		CarInfoVo carInfoVo = new CarInfoVo();
		CarInfo carInfo=this.mapper.selectCarInfoVoByApplyId(ApplyId);
		if(carInfo!=null) {
			CarDriverInfo carDriverInfo = carDriverInfoDao.findCarDriverInfoByCarId(carInfo.getId());
			CarPeccancyInfo carPeccancyInfo = carPeccancyInfoDao.findCarPeccancyInfoByCarId(carInfo.getId());
			CarTrafficInsureInfo carTrafficInsureInfo = carTrafficInsureInfoDao.findCarTrafficInsureInfoByCarId(carInfo.getId());
			CarBussInsureInfo carBussInsureInfo = carBussInsureInfoDao.findCarBussInsureInfoByCarId(carInfo.getId());
			List<CarTransferInfo> carTransferInfoList = carTransferInfoDao.findCarTransferInfoByCarId(carInfo.getId());
			List<CarMortgageInfo> carMortgageInfoList = carMortgageInfoDao.findCarMortgageInfoByCarId(carInfo.getId());
			if (carBussInsureInfo != null) {
				List<CarInsureDetailInfo> carInsureDetailInfoList = carInsureDetailInfoDao.findCarInsureDetailInfoByCarBussInsureInfoId(carBussInsureInfo.getId());
				carInfoVo.setCarInsureDetailInfoList(carInsureDetailInfoList);
			}
			carInfoVo.setCarInfoVoId(carInfo.getId());
			carInfoVo.setApplyId(ApplyId);
			carInfoVo.setCarConfigName(carInfo.getCarConfigName());
			carInfoVo.setCarNum(carInfo.getCarNum());
			carInfoVo.setCurrentLicDate(carInfo.getCurrentLicDate());
			carInfoVo.setCarType(carInfo.getCarType());
			carInfoVo.setCarBrand(carInfo.getCarBrand());
			carInfoVo.setCarModel(carInfo.getCarModel());
			carInfoVo.setVin(carInfo.getVin());
			carInfoVo.setEngineNo(carInfo.getEngineNo());
			carInfoVo.setCarColor(carInfo.getCarColor());
			carInfoVo.setCarImportType(carInfo.getCarImportType());
			carInfoVo.setFuelType(carInfo.getFuelType());
			carInfoVo.setDisplacement(carInfo.getDisplacement());
			carInfoVo.setManufacturer(carInfo.getManufacturer());
			carInfoVo.setCarUsage(carInfo.getCarUsage());
			carInfoVo.setGetType(carInfo.getGetType());
			carInfoVo.setProductDate(carInfo.getProductDate());
			carInfoVo.setFirstLicDate(carInfo.getFirstLicDate());
			carInfoVo.setRegisterPhotoUrl1(carInfo.getRegisterPhotoUrl1());
			carInfoVo.setRegisterPhotoUrl2(carInfo.getRegisterPhotoUrl2());
			carInfoVo.setRegisterPhotoUrl3(carInfo.getRegisterPhotoUrl3());
			carInfoVo.setRegisterPhotoUrl4(carInfo.getRegisterPhotoUrl4());
			carInfoVo.setCarLocationId(carInfo.getCarLocationId());
			carInfoVo.setCarLocationName(carInfo.getCarLocationName());
			carInfoVo.setCarCityId(carInfo.getCarCityId());
			carInfoVo.setCarCityName(carInfo.getCarCityName());
			carInfoVo.setCarSeriesId(carInfo.getCarSeriesId());
			carInfoVo.setCarSeriesName(carInfo.getCarSeriesName());
			carInfoVo.setCarModelId(carInfo.getCarModelId());
			carInfoVo.setCarModelName(carInfo.getCarModelName());
			carInfoVo.setMileage(carInfo.getMileage());
			carInfoVo.setCarDriverInfo(carDriverInfo);
			carInfoVo.setCarPeccancyInfo(carPeccancyInfo);
			carInfoVo.setCarTrafficInsureInfo(carTrafficInsureInfo);
			carInfoVo.setCarBussInsureInfo(carBussInsureInfo);
			carInfoVo.setCarTransferInfolist(carTransferInfoList);
			carInfoVo.setCarMortgageInfoList(carMortgageInfoList);
			carInfoVo.setRegistrationCarType(carInfo.getRegistrationCarType());
		}
		return carInfoVo;
	}

	/**
	 * 根据custId查询验车师初始化页面的车辆信息
	 * @param custId
	 * @return
	 */
	public InitCarVerifyVo selInitCarverifyByCustId(Long custId){
		return mapper.selInitCarverifyByCustId(custId);
	}

	@Override
	public CarInfo selectByCusId(Long cusid) {
		// TODO Auto-generated method stub
		return mapper.selectByCusId(cusid);
	}
}

