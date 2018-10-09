package com.moerlong.carloan.modular.car.service.impl;

import com.moerlong.carloan.modular.car.dao.CarExteriorConfigDao;
import com.moerlong.carloan.modular.car.entity.CarExteriorConfig;
import com.moerlong.carloan.modular.car.service.CarExteriorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarExteriorConfigServiceImpl implements CarExteriorConfigService {

    @Autowired
    private CarExteriorConfigDao mapper;

    @Override
    public void saveOrUpdate(CarExteriorConfig record) {
        if(record.getId()!=null && this.selectById(record.getId())!=null) {
            record.setUpdateTime(new Date());
            this.updateWithOutNull(record);
        }else {
            record.setIsDelete(0);
            record.setCreateTime(new Date());
            this.save(record);
        }
    }

    @Override
    public void save(CarExteriorConfig record) {
        mapper.save(record);
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public void deleteLogic(Long id) {
        mapper.deleteLogic(id);
    }

    @Override
    public void update(CarExteriorConfig record) {
        mapper.update(record);
    }

    @Override
    public void updateWithOutNull(CarExteriorConfig record) {
        mapper.updateWithOutNull(record);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public CarExteriorConfig selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public CarExteriorConfig selectByApplyId(Long applyId) {
        return mapper.selectByApplyId(applyId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<CarExteriorConfig> listAll() {
        return mapper.listAll();
    }
}
