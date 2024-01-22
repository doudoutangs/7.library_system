package com.library.core.util;

import java.math.BigDecimal;

public class CalcTool {
    /**
     * 乘法，保留两位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static double multiplyCalc(int a, Double b) {
        BigDecimal n1 = new BigDecimal(a);
        BigDecimal n2 = new BigDecimal(b);
        return n1.multiply(n2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
