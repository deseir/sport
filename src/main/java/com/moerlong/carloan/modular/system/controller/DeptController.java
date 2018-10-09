package com.moerlong.carloan.modular.system.controller;

import com.moerlong.carloan.common.constant.Dict;
import com.moerlong.carloan.common.node.ZTreeNode;
import com.moerlong.carloan.common.persistence.dao.DeptMapper;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.core.util.Convert;
import com.moerlong.carloan.modular.system.service.IDeptService;
import com.moerlong.carloan.modular.system.warpper.DeptWarpper;
import com.moerlong.carloan.common.annotion.Permission;
import com.moerlong.carloan.common.annotion.log.BussinessLog;
import com.moerlong.carloan.common.constant.factory.ConstantFactory;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.core.log.LogObjectHolder;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.system.dao.DeptDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 * @author hwl
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/system/dept/";

    @Resource
    DeptDao deptDao;

    @Resource
    DeptMapper deptMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    IDeptService deptService;

    /**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept.html";
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "dept_add.html";
    }

    /**
     * 跳转到修改部门
     */
    @Permission
    @RequestMapping("/dept_update/{deptId}")
    public String deptUpdate(@PathVariable Integer deptId, Model model) {
        Dept dept = deptMapper.selectById(deptId);
        model.addAttribute(dept);
        model.addAttribute("pName", ConstantFactory.me().getSingleDeptName(dept.getPid()));
        LogObjectHolder.me().set(dept);
        return PREFIX + "dept_edit.html";
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptDao.tree();
        //tree.add(ZTreeNode.createParent());
        return tree;
    }

    @RequestMapping(value = "/deptTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> deptTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userMapper.selectById(userId);
        if(theUser == null){
            List<ZTreeNode> roleTreeList = this.deptDao.tree();
            return roleTreeList;
        }else{
            String deptid = theUser.getDeptid();
            if (ToolUtil.isEmpty(deptid)) {
                List<ZTreeNode> roleTreeList = this.deptDao.tree();
                return roleTreeList;
            } else {
                String[] strArray = Convert.toStrArray(",", deptid);
                List<ZTreeNode> deptTreeListByUserId = this.deptDao.deptTreeListByDeptId(strArray);
                return deptTreeListByUserId;
            }
        }
    }

    /**
     * 新增部门
     */
    @BussinessLog(value = "添加部门", key = "simplename", dict = Dict.DeptDict)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(Dept dept) {
        if (ToolUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        deptSetPids(dept);
        return this.deptMapper.insert(dept);
    }

    /**
     * 获取所有部门列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.deptDao.list(condition);
        return super.warpObject(new DeptWarpper(list));
    }

    /**
     * 部门详情
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("deptId") Integer deptId) {
        return deptMapper.selectById(deptId);
    }

    /**
     * 修改部门
     */
    @BussinessLog(value = "修改部门", key = "simplename", dict = Dict.DeptDict)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public Object update(Dept dept) {
        if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        deptSetPids(dept);
        deptMapper.updateById(dept);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除部门
     */
    @BussinessLog(value = "删除部门", key = "deptId", dict = Dict.DeleteDict)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam Integer deptId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getSingleDeptName(deptId));

        deptService.deleteDept(deptId);

        return SUCCESS_TIP;
    }

    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0)) {
            dept.setPid(0);
            dept.setPids("[0],");
        } else {
            int pid = dept.getPid();
            Dept temp = deptMapper.selectById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}
