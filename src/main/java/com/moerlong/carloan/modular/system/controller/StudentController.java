package com.moerlong.carloan.modular.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.moerlong.carloan.common.constant.factory.PageFactory;
import com.moerlong.carloan.common.persistence.dao.StudentMapper;
import com.moerlong.carloan.modular.system.dao.StudentDao;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.model.OperationLog;
import com.moerlong.carloan.common.persistence.model.Student;
import com.moerlong.carloan.core.log.LogObjectHolder;
import com.moerlong.carloan.core.support.BeanKit;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.common.controller.BaseController;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.moerlong.carloan.modular.system.warpper.LogWarpper;

/**
 * 学生控制器
 *
 * @author hwl
 * @Date 2017-09-04 16:08:54
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    private String PREFIX = "/system/student/";
    
    @Resource
    StudentDao studentDao;
    
    @Resource
    StudentMapper studentMapper;

    /**
     * 跳转到学生首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "student.html";
    }

    /**
     * 跳转到添加学生
     */
    @RequestMapping("/student_add")
    public String studentAdd() {
        return PREFIX + "student_add.html";
    }

    /**
     * 跳转到修改学生
     */
    @RequestMapping("/student_update/{studentId}")
    public String studentUpdate(@PathVariable Integer studentId, Model model) {
    	if (ToolUtil.isEmpty(studentId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
    	Student student = this.studentMapper.selectById(studentId);
    	LogObjectHolder.me().set(student);
    	
    	 Map<String, Object> studentMap = BeanKit.beanToMap(student);
         model.addAttribute("student", studentMap);
        return PREFIX + "student_edit.html";
    }

    /**
     * 获取学生列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String logname) {
//    	 List<Map<String, Object>> students = this.studentDao.selectStudentList();
//         return super.warpObject(new StudentWarpper(students));
    	Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        List<Map<String, Object>> result = studentDao.getStudentByPage(logname,page, page.getOrderByField(), page.isAsc());
        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增学生
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Student student,BindingResult bindingResult) {
    	 if (bindingResult.hasErrors()) {
             throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
         }
    	 
    	 setCommon(student, 0);
    	 this.studentMapper.insert(student);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除学生
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer studentId) {
    	if (ToolUtil.isEmpty(studentId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
    	
    	this.studentMapper.deleteById(studentId);
        return SUCCESS_TIP;
    }


    /**
     * 修改学生
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@Valid Student student,BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
    	
    	Student data = this.studentMapper.selectById(student);
    	if(data==null){
    		throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
    	}
    	
    	setCommon(student, 1);
    	this.studentMapper.updateById(student);
    	
        return super.SUCCESS_TIP;
    }

    /**
     * 学生详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
    
    /**
     * 保存信息时时间的设置   0代表创建  其它值代表更新
     * @param student
     * @param isUpd
     */
    private void setCommon(Student student,int isUpd){
    	Date date = new Date();
    	if(isUpd==0){
    		student.setCrtTime(date);
    	}
    	
    	student.setUpdTime(date);
    }
}
