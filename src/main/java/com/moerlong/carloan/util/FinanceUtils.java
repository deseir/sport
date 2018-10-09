package com.moerlong.carloan.util;

import java.math.BigDecimal;

public class FinanceUtils {

    /**
     * PMT 财务公式
     * (ir + (ir/(Math.pow(1+ir, np) - 1)))*pv;
     * @param ir	interest rate per month
     * @param np	number of periods (months)
     * @param pv	present value
     * @return
     */
    public static BigDecimal PMT(BigDecimal ir, int np, BigDecimal pv){

        BigDecimal d = ir.add(new BigDecimal(1)).pow(np).subtract(new BigDecimal(1));
        //BigDecimal ret = ir.add(ir.divide(d)).multiply(pv);
        BigDecimal ret = ir.multiply(d.add(new BigDecimal(1))).multiply(pv).divide(d, 10, BigDecimal.ROUND_HALF_UP);
        return ret;
    }
}
