package com.moerlong.carloan.modular.car.service.impl;

import com.moerlong.carloan.modular.car.dao.CarInteriorCollocationDao;
import com.moerlong.carloan.modular.car.entity.CarInteriorCollocation;
import com.moerlong.carloan.modular.car.service.CarInteriorCollocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarInteriorCollocationServiceImpl implements CarInteriorCollocationService {

    @Autowired
    private CarInteriorCollocationDao mapper;

    @Override
    public void saveOrUpdate(CarInteriorCollocation record) {
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
    public void save(CarInteriorCollocation record) {
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
    public void update(CarInteriorCollocation record) {
        mapper.update(record);
    }

    @Override
    public void updateWithOutNull(CarInteriorCollocation record) {
        mapper.updateWithOutNull(record);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public CarInteriorCollocation selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public CarInteriorCollocation selectByApplyId(Long applyId) {
        return mapper.selectByApplyId(applyId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<CarInteriorCollocation> listAll() {
        return mapper.listAll();
    }
}
