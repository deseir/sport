package com.moerlong.carloan.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.Map;

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

    public PageInfo<Dept> selectPage(int pageSize, int pageNum, Map<String,Object> param) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dept> pageList = deptDao.selectPage(param);
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(pageList);
        return pageInfo;
    }

    public PageInfo<Dept> selectPage2(int pageSize, int pageNum, Map<String,Object> param) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dept> pageList = deptDao.selectPage2(param);
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(pageList);
        return pageInfo;
    }

    public List<Dept> selectByDeptName(Map<String, Object> param){
        return deptDao.selectByDeptName(param);
    }

    public int upSfxj (Map<String,Object> param){
        return deptDao.upSfxj(param);
    }

}
