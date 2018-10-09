package com.moerlong.carloan.modular.loan.bussiness;

import com.moerlong.carloan.common.vo.ResultVO;

import java.math.BigDecimal;
import java.util.Map;

public interface ProcessBussiness {

    /**
     * 内勤资料录入提交（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> nqDataSaveSubmit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 验车师资料录入提交（验车师）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> ycSubmit(Long applyId, Long operatorId, Integer result, String resultTip);


    /**
     * 面审提交（面审）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> msSubmit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 面审主管审核（面审主管）
     * @param applyId
     * @param operatorId
     * @param result
     * @param resultTip
     * @return
     */
    public ResultVO<Object> mszgSubmit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 终审审核（终审）
     * @param applyId
     * @param operatorId
     * @param result
     * @param resultTip
     * @return
     */
    public ResultVO<Object> zsSubmit(Map<String ,Object> params);

    /**
     * 签署合同（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> signContract(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 安装GPS申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> gpsInstallApply(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 安装GPS完成（验车师）
     * @param applyId
     * @return
     */
    public ResultVO<Object> gpsInstallComplete(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * GPS安装完成确认（面审主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> gpsInstallConfirm(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 抵押申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> mortgageCarApply(Long applyId, Long operatorId, Integer result, String resultTip);


    /**
     * 确认抵押申请材料（抵押专员）
     * @param applyId
     * @param operatorId
     * @param result
     * @param resultTip
     * @return
     */
    public ResultVO<Object> mortgageCarConfirmApply(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 接收资料确认（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> mortgageCarConfirmPaper(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 接收资料确认（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> mortgageCarBussHanding(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 抵押办理完成（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> mortgageCarBussFinish(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 资料存留（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> dataKeep(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 放款申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payApply(Long applyId, Long operatorId, Integer result,
    		String receptionDepart,String receptionManager,
    		String bankCardNo, String bankName,
    		String custMobile,String receptionAmount,
    		String isPerCharge,String isReplaceCost,
    		String resultTip);

    /**
     * 放款内勤主管审核（内勤主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payNqzgAudit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 放款业务经理审核（业务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payBussAudit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 放款财务首次审核（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payFinFirstAudit(Long applyId, Long operatorId, String amount, Integer result, String resultTip);

    /**
     * 放款财务经理首次审核（财务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payFinBussFirstAudit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 放款前台财务确认（前台财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payReceptionConfirm(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 放款财务二次审核（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payFinSecondAudit(Long applyId, Long operatorId, String amount,Integer result, String resultTip);

    /**
     * 放款财务经理二次审核（财务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> payFinBussSecondAudit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 提前还款业务申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> onceEarlyApply(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 提前还款内勤主管审核（内勤主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> onceEarlyNqzgAudit(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 提前还款业务经理审核（业务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> onceEarlyBussApprove(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 提前还款财务确认（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> onceEarlyFinConfirm(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 提前还款财务经理审核（财务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> onceEarlyFinBussConfirm(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 解押申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> detentionCarApply(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 解押结清确认（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> detentionFinSettleConfirm(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 解押申请材料确认（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> detentionConfirmApply(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 解押申请材料确认接收（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> detentionConfirmPaper(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 解押已受理（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> detentionBussHanding(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 解押完成（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> detetionBussFinish(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * gps卸载申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> gpsUninstallApply(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * gps卸载申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> gpsUnistallFinish(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * gps卸载完成确认（面审主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> gpsUninstallConfirm(Long applyId, Long operatorId, Integer result, String resultTip);

    /**
     * 资料移交（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    public ResultVO<Object> dataTranfser(Long applyId, Long operatorId, Integer result, String resultTip);





}
