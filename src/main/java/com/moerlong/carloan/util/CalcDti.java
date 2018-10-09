package com.moerlong.carloan.util;

import java.math.BigDecimal;

public class CalcDti {

    /**
     *
     *@param applyAmount 本次拟贷款本金
     * @param applyPeriod 本次拟贷款期限
     * @param incomeConfirmAmount 认收金额
     * @param creditLoanMonthAmount 征信认定的负债
     * @return 计算出的dit值
     */

    public static String getDti(BigDecimal applyAmount,BigDecimal applyPeriod,BigDecimal incomeConfirmAmount,BigDecimal creditLoanMonthAmount){




        //本次拟月还款本金=本次拟贷款本金/本次拟贷款期限
        BigDecimal monthMonth = applyAmount.divide(applyPeriod);

        //本次拟月还款利率 = 本次拟贷款本金 * 0.83%
        BigDecimal monthLlv =applyAmount.multiply(new BigDecimal(0.83)).divide(new BigDecimal(100));

        //本次拟服务费 = 本次拟贷款本金 * 3%/本次拟贷款期限
        BigDecimal castFee =  applyAmount.multiply(new BigDecimal(0.03)).divide(applyPeriod);

        //流量费
        BigDecimal liuliangFee = new BigDecimal(100);

        //本次拟贷月供 = 本次拟月还款本金+本次拟月还款利率+流量费+本次拟服务费
        BigDecimal loanAmountTmp =  monthMonth.add(monthLlv).add(liuliangFee).add(castFee);

        //(征信认定的负债+本次拟贷款月供）/收入认定金额
        if(incomeConfirmAmount !=null && creditLoanMonthAmount !=null){
            BigDecimal totalLoan = creditLoanMonthAmount.add(loanAmountTmp);
            BigDecimal dti = totalLoan.divide(incomeConfirmAmount);
            return dti.toString();
        }else {
           return "0.00";
        }

    }
}
