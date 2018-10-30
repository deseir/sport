package com.moerlong.carloan.modular.system.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.annotion.Permission;
import com.moerlong.carloan.common.annotion.log.BussinessLog;
import com.moerlong.carloan.common.constant.Dict;
import com.moerlong.carloan.common.constant.factory.ConstantFactory;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.node.ZTreeNode;
import com.moerlong.carloan.common.persistence.dao.DeptMapper;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.core.log.LogObjectHolder;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.core.util.Convert;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.system.dao.DeptDao;
import com.moerlong.carloan.modular.system.service.IDeptService;
import com.moerlong.carloan.modular.system.warpper.DeptWarpper;
import com.moerlong.carloan.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
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

    @Value("${file.identity_pic_urls}")
    private String idPicUrls;

    /**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("idPicUrls",idPicUrls);
        return PREFIX + "dept.html";
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/dept_add")
    public String deptAdd(Model model) {
        model.addAttribute("idPicUrls",idPicUrls);
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
        model.addAttribute("idPicUrls",idPicUrls);
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

    @ApiOperation(value = "查询所有的子部门")
    @RequestMapping(value = "/getAllSubDept", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getAllSubDept(@RequestParam Integer deptId) {
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("data", deptService.getAllSubDeptByDeptId(deptId));
            res.put("status", 0);
            res.put("errMsg", "操作成功");
        } catch (Throwable e) {
            res.put("status", 1);
            res.put("errMsg", e.getMessage());
        }
        return res;
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Object selectPage(@RequestParam Map<String,Object> queryMap) {
        Map<String, Object> res = new HashMap<>();
        Integer pageNum = 1; //页数从1开始
        Integer pageSize = 10; //页面大小

        try {
            if(queryMap!=null) {
                if(queryMap.get("pageNum")!=null&&!"".equals(queryMap.get("pageNum"))) {
                    pageNum = Integer.parseInt((String)queryMap.get("pageNum"));
                }
                if(queryMap.get("pageSize")!=null) {
                    pageSize = Integer.parseInt((String)queryMap.get("pageSize"));
                }
            }

            Object pageInfo = this.deptService.selectPage(pageSize, pageNum, queryMap);

            res.put("data", pageInfo);
            res.put("status", 0);
            res.put("errMsg", "操作成功");
        } catch (Throwable e) {
            res.put("status", 1);
            res.put("errMsg", e.getMessage());
        }

        return res;
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(paramType = "body", name = "queryMap", required = false, dataType = "Map", value = "查询条件")
    @RequestMapping(value = "/dept/pageQuery", method = RequestMethod.POST)
    @ResponseBody
    public Object pageQuery(@RequestParam Map<String,Object> queryMap) {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        Map<String, Object> res = new HashMap<>();
        Integer pageNum = (offset / limit + 1); //页数从1开始
        Integer pageSize = limit; //页面大小

        try {
            PageInfo<Dept> pageInfo = this.deptService.selectPage(pageSize, pageNum, queryMap);
            res.put("total",pageInfo.getTotal());
            res.put("rows",pageInfo.getList());
            return CommonUtil.obj2json(res);
        } catch (Throwable e) {
            res.put("status", 1);
            res.put("errMsg", e.getMessage());
        }

        return res;
    }
}
