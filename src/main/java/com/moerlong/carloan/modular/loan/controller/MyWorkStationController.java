package com.moerlong.carloan.modular.loan.controller;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.support.DateTimeKit;
import com.moerlong.carloan.core.support.HttpKit;
import com.moerlong.carloan.modular.loan.bussiness.MyWorkStationBussiness;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.modular.system.service.IDeptService;
import com.moerlong.carloan.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

//@Controller
public class MyWorkStationController {

    private final Logger log = LoggerFactory.getLogger(ApplyInfoController.class);

    @Autowired
    private MyWorkStationBussiness myWorkStationBussiness;

    @Autowired
    private UserMgrDao userMgrDao;
    @Autowired
    IDeptService deptService;
    @Resource
    private ApplyInfoService applyInfoService;
    //我的待办列表界面
    @RequestMapping("/myWork/todoWorkListPage")
    public String getLoanApplyPage(){
        return "/loan/mywork/todoWork.html";
    }

    //我的经办列表界面
    @RequestMapping("/myWork/handledWorkListPage")
    public String getHandledLoanApplyPage(){
        return "/loan/mywork/handledWork.html";
    }

    //审批进度界面
    @RequestMapping(value = "/myWork/getApproveRecordListPage", method = RequestMethod.GET)
    public String getApproveRecordListPage(@RequestParam Long applyId, Model model){
        model.addAttribute("applyId", applyId);
        //查询用户的基本信息
        ApplyInfo applyInfo=applyInfoService.selectById(Long.valueOf(applyId));
        List<MainApproveRecord> list = myWorkStationBussiness.getApproveRecordList(applyId);
        //当前节点和操作人和操作时间
        String currentNode = "";
        String currentNodeOperator = "";
        Date currentNodeTime = null;
        //所有前置节点和操作人和操作时间
        List frontFirstNode = new ArrayList();
        List frontFirstNodeOperator = new ArrayList();
        List frontFirstNodeTime = new ArrayList();
        //所有后置节点和操作人
        List nextNode= new ArrayList();
        List nextNodeOperator = new ArrayList();
        currentNode= list.get(0).getProcessNodeDesc();
        currentNodeOperator= list.get(0).getOperatorName();
        currentNodeTime = list.get(0).getOperatorTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(list.size()==1){//新建,只有一个节点,没有前置节点,后置节点为内勤
        }else if(list.size()>=2){//有前置节点,和当前节点
            for (MainApproveRecord mainApproveRecord : list) {
                if(!mainApproveRecord.getProcessNodeDesc().equals(currentNode)){
                    String processNodeDesc= mainApproveRecord.getProcessNodeDesc();
                    String operatorName=mainApproveRecord.getOperatorName();
                    Date operatorTime =mainApproveRecord.getOperatorTime();
                    frontFirstNode.add(processNodeDesc);
                    frontFirstNodeOperator.add(operatorName);
                    if(operatorTime!=null){
                        frontFirstNodeTime.add(sdf.format(operatorTime));
                    }
                }
            }
        }
        model.addAttribute("currentNode", currentNode);
        model.addAttribute("currentNodeOperator", currentNodeOperator);
        model.addAttribute("currentNodeTime",sdf.format(currentNodeTime));

        model.addAttribute("frontFirstNode", frontFirstNode);
        model.addAttribute("frontFirstNodeOperator", frontFirstNodeOperator);
        if(frontFirstNodeTime.size()>0){
            model.addAttribute("frontFirstNodeTime",frontFirstNodeTime);
        }else{
            model.addAttribute("frontFirstNodeTime","");
        }
        climStaris("3",applyInfo.getDeptId().toString(),nextNode,nextNodeOperator);
        int Len=nextNode.indexOf(currentNode);
        nextNode= nextNode.subList(Len+1,nextNode.size());
        nextNodeOperator= nextNodeOperator.subList(Len+1,nextNodeOperator.size());
        model.addAttribute("nextNode", nextNode);
        model.addAttribute("nextNodeOperator", nextNodeOperator);
        return "/loan/mywork/approveRecord.html";
    }
    public Map climStaris(String roseId,String deptId,List nextNode, List nextNodeOperator){
        Map map = new HashMap();
        if (roseId.equals("3")&&nextNode.size()==0){
            String nextNodeOperato="";
            nextNode.add("内勤资料录入");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
                else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("4",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("4")&&nextNode.size()==1){
            String nextNodeOperato="";
            nextNode.add("验车");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("6",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("6")&&nextNode.size()==2){
            String nextNodeOperato="";
            nextNode.add("面审");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("5",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("5")&&nextNode.size()==3){
            String nextNodeOperato="";
            nextNode.add("面审主管审核");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("7",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("7")&&nextNode.size()==4){
            String nextNodeOperato="";
            nextNode.add("总部终审");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==5){
            String nextNodeOperato="";
            nextNode.add("签订合同");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==6){
            String nextNodeOperato="";
            nextNode.add("GPS安装申请");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==7){
            String nextNodeOperato="";
            nextNode.add("抵押申请");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==8){
            String nextNodeOperato="";
            nextNode.add("确认抵押申请材料");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==9){
            String nextNodeOperato="";
            nextNode.add("接收抵押资料确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==10){
            String nextNodeOperato="";
            nextNode.add("抵押已受理");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==11){
            String nextNodeOperato="";
            nextNode.add("抵押办理成功");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("4",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("4")&&nextNode.size()==12){
            String nextNodeOperato="";
            nextNode.add("GPS安装");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("5",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("5")&&nextNode.size()==13){
            String nextNodeOperato="";
            nextNode.add("GPS安装确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==14){
            String nextNodeOperato="";
            nextNode.add("资料存留");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==15){
            String nextNodeOperato="";
            nextNode.add("请款申请");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("2",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("2")&&nextNode.size()==16){
            String nextNodeOperato="";
            nextNode.add("请款内勤主管审核");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("9",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("9")&&nextNode.size()==17){
            String nextNodeOperato="";
            nextNode.add("请款业务经理审核");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("11",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("11")&&nextNode.size()==18){
            String nextNodeOperato="";
            nextNode.add("请款财务首次审核");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("10",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("10")&&nextNode.size()==19){
            String nextNodeOperato="";
            nextNode.add("请款财务经理首次审核");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("12",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("12")&&nextNode.size()==20){
            String nextNodeOperato="";
            nextNode.add("前台财务确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("11",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("11")&&nextNode.size()==21){
            String nextNodeOperato="";
            nextNode.add("请款财务二次审核成功");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("10",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("10")&&nextNode.size()==22){
            String nextNodeOperato="";
            nextNode.add("请款财务经理二次审核成功（放款完成)");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("2",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("2")&&nextNode.size()==23){
            String nextNodeOperato="";
            nextNode.add("提前还款内勤主管审核成功");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("9",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("9")&&nextNode.size()==24){
            String nextNodeOperato="";
            nextNode.add("提前还款业务经理审核成功");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("11",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("11")&&nextNode.size()==25){
            String nextNodeOperato="";
            nextNode.add("提前还款财务确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("10",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("10")&&nextNode.size()==26){
            String nextNodeOperato="";
            nextNode.add("提前还款财务经理确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==27){
            String nextNodeOperato="";
            nextNode.add("解押申请");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("11",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("11")&&nextNode.size()==28){
            String nextNodeOperato="";
            nextNode.add("结清确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==29){
            String nextNodeOperato="";
            nextNode.add("确认解押申请材料");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==30){
            String nextNodeOperato="";
            nextNode.add("解押已受理");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("8",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("8")&&nextNode.size()==31){
            String nextNodeOperato="";
            nextNode.add("解押办理成功");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==32){
            String nextNodeOperato="";
            nextNode.add("拆卸GPS申请");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("4",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("4")&&nextNode.size()==33){
            String nextNodeOperato="";
            nextNode.add("拆卸GPS");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("5",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("5")&&nextNode.size()==34){
            String nextNodeOperato="";
            nextNode.add("拆卸GPS确认");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        if (roseId.equals("3")&&nextNode.size()==35){
            String nextNodeOperato="";
            nextNode.add("资料移交(债务完结)");
            List<String> nameList =  userMgrDao.getByRoleId(roseId,deptId);
            for (String s : nameList) {
                if(nameList.get(nameList.size()-1).equals(s)&&!nameList.get(0).equals(s)){
                    nextNodeOperato = nextNodeOperato+"/"+s;
                } else if(nameList.get(0).equals(s)){
                    nextNodeOperato =s;
                }else{
                    nextNodeOperato = nextNodeOperato+"/"+s;
                }
            }
            nextNodeOperator.add(nextNodeOperato);
            return climStaris("3",deptId,nextNode,nextNodeOperator);
        }
        return map;
    }
    @RequestMapping(value = "/myWork/getTodoWorkList", method = RequestMethod.POST)
    @ResponseBody
    public Object getTodoWorkList(@RequestParam(required = false) String custName, @RequestParam(required = false) String custIdNo,
                                  @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        Map<String, Object> res = new HashMap<>();
        Integer pageNum = (offset / limit + 1); //页数从1开始
        Integer pageSize = limit; //页面大小
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try {

            PageInfo<LoanApplyInfoVo> pageInfo = myWorkStationBussiness.getMyTodoWorkList(pageSize, pageNum, operatorId, custName, custIdNo, beginTime,endTime);

            List<LoanApplyInfoVo> list = pageInfo.getList();
            for (LoanApplyInfoVo infoVo : list){
              List<String> nameList =  userMgrDao.getByRoleId(infoVo.getNextNodeRole(),infoVo.getDeptId().toString());
              if(null != nameList){
                  infoVo.setNextNodeRoleNames(nameList);
              }
            }
            res.put("total",pageInfo.getTotal());
            res.put("rows",list);
            return pageInfo.getList();
        } catch (Throwable e) {
            this.log.error(e.getMessage(), e);
            res.put("total",0);
            res.put("rows",null);
        }

        return res;

    }

    @RequestMapping(value = "/myWork/getHandledWorkList", method = RequestMethod.POST)
    @ResponseBody
    public Object getHandledWorkList(@RequestParam(required = false) String custName, @RequestParam(required = false) String custIdNo,
                                  @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {

        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        Map<String, Object> res = new HashMap<>();
        Integer pageNum = (offset / limit + 1); //页数从1开始
        Integer pageSize = limit; //页面大小
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        try {

            PageInfo<LoanApplyInfoVo> pageInfo = myWorkStationBussiness.getMyHandledWorkList(pageSize, pageNum, operatorId, custName, custIdNo, beginTime,endTime);

            res.put("total",pageInfo.getTotal());
            res.put("rows",pageInfo.getList());
            return CommonUtil.obj2json(res);
        } catch (Throwable e) {
            this.log.error(e.getMessage(), e);
            res.put("total",0);
            res.put("rows",null);
        }

        return res;

    }

    @RequestMapping(value = "/myWork/getApproveRecordList", method = RequestMethod.POST)
    @ResponseBody
    public Object getApproveRecordList(Long applyId) {

        try {

            List<MainApproveRecord> list = myWorkStationBussiness.getApproveRecordList(applyId);

            return ResultVO.build(ErrorCode.SUCCESS, list);
        } catch (Throwable e) {
            this.log.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

}
