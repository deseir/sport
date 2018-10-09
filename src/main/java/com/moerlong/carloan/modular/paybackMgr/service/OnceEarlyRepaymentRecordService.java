package com.moerlong.carloan.modular.paybackMgr.service;

import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceEarlyRepaymentRecordVO;

import java.util.List;

public interface OnceEarlyRepaymentRecordService {
    /**
     * 保存
     * @param entity
     */
    public void save(OnceEarlyRepaymentRecord entity);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);

    /**
     * 更新
     * @param entity
     */
    public void update(OnceEarlyRepaymentRecord entity);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public OnceEarlyRepaymentRecord selectById(Long id);

    public OnceEarlyRepaymentRecord selectByRepaymentId(Long repaymentId);


    /**
     * 根据条件查询
     * @return
     */
    public List<OnceEarlyRepaymentRecordVO> listByCondition(String custName, String custMobile, String beginTime, String endTime);

    /**
     * 分页查询
     * @param pageSize	页面大小
     * @param pageNum	第几页
     * @return
     */
    public PageInfo<OnceEarlyRepaymentRecordVO> selectPage(int pageSize, int pageNum, String custName, String custMobile, String beginTime, String endTime);


}
