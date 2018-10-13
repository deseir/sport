package com.moerlong.carloan.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.moerlong.carloan.common.persistence.dao.DeptMapper;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.util.Convert;
import com.moerlong.carloan.modular.system.dao.DeptDao;
import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.modular.system.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

    @Resource
    DeptMapper deptMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    DeptDao deptDao;
    @Resource
    UserMgrDao userMgrDao;
    @Override
    public void deleteDept(Integer deptId) {

        Dept dept = deptMapper.selectById(deptId);

        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<Dept> subDepts = deptMapper.selectList(wrapper);
        for (Dept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }

    @Override
    public String getAllSubDept(Integer userId) {
        //User user = userMapper.selectById(userId);
       // User user = userMgrDao.selectUserById(userId);
        StringBuilder sb = new StringBuilder();
        List<Dept> list = new ArrayList<>();
        String[] strArray = Convert.toStrArray(",",ShiroKit.getUser().getDeptList().get(0).toString());
        for (String s : strArray) {
            sb.append(s).append(",");
            List<Dept> tempList = deptDao.selectAllSubDept(Integer.parseInt(s));
            for (Dept dept : tempList) {
                sb.append(dept.getId()).append(",");
            }
        }
        return sb.toString().substring(0, sb.length()-1);
    }

	@Override
	public String selectAllDept(Integer userId) {
		 StringBuilder sb = new StringBuilder();
		 List<Dept> tempList = deptDao.selectAllSubDept(0);
         for (Dept dept : tempList) {
             sb.append(dept.getId()).append(",");
         }
		return sb.toString().substring(0, sb.length()-1);
	}

    @Override
    public List<Dept> getAllSubDeptByDeptId(Integer deptId) {
        return deptDao.getAllSubDeptByDeptId(deptId);
    }

}
