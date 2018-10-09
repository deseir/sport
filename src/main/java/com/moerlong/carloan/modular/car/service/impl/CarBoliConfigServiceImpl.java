package com.moerlong.carloan.modular.car.service.impl;

import com.moerlong.carloan.modular.car.dao.CarBoliConfigDao;
import com.moerlong.carloan.modular.car.entity.CarBoliConfig;
import com.moerlong.carloan.modular.car.service.CarBoliConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarBoliConfigServiceImpl implements CarBoliConfigService {

    @Autowired
    private CarBoliConfigDao mapper;


    @Override
    public void saveOrUpdate(CarBoliConfig record) {
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
    public void save(CarBoliConfig record) {
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
    public void update(CarBoliConfig record) {
        mapper.update(record);
    }

    @Override
    public void updateWithOutNull(CarBoliConfig record) {
        mapper.updateWithOutNull(record);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public CarBoliConfig selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public CarBoliConfig selectByApplyId(Long applyId) {
        return mapper.selectByApplyId(applyId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<CarBoliConfig> listAll() {
        return mapper.listAll();
    }
}
