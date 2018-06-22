package com.qcp.dfv.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by w.gs on 2016/3/7.
 */
public class NumberUtils {

    public static double getDouble(String d) {
        if (TextUtils.isEmpty(d)) {
            return 0;
        }
        try {
            return Double.valueOf(d);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String formatGprsWithUnit(int amount) {
        if (amount >= 1024) {
            return amount / 1024 + "GB";
        } else {
            return amount + "MB";
        }
    }


    public static String formatFloatGprsWithUnit(double amount) {
        if (amount >= 1024) {
            return new DecimalFormat("#,##0.00").format(amount / 1024.0) + "GB";
        } else {
            return new DecimalFormat("#,##0.00").format(amount) + "MB";
        }

    }

    public static String formatPrice(double price) {
        return new DecimalFormat("###0.00").format(price);
    }

    public static String formatPriceWithUnit(double price) {
        return new DecimalFormat("#,##0.00").format(price / 100.0) + "元";
    }

    public static String formatPriceWithUnit(int price) {
        if (price % 100 == 0) {
            return price / 100 + "元";
        } else {
            return formatPriceWithUnit(Double.valueOf(price));
        }
    }

    public static String formatPriceWith$(double price) {
        return "￥" + new DecimalFormat("#,##0.00").format(price / 100.0);
    }

    public static String formatDistance(int distance_m) {
        return new DecimalFormat("#,##0.00").format(distance_m / 1000.0);
    }

    public static String formatDistanceWithUnit(int distance_m) {
        return new DecimalFormat("#,##0.00").format(distance_m / 1000.0) + "km";
    }

    /**
     * <乘法运算并保留两位有效数字> <功能详细描述>
     *
     * @param factor1 String
     * @param factor2 String
     * @return String
     */
    public static String multiply(String factor1, String factor2) {
        BigDecimal fac1 = new BigDecimal(factor1);
        BigDecimal fac2 = new BigDecimal(factor2);
        BigDecimal result = fac1.multiply(fac2);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result.toString();
    }

    /**
     * <除法运算并保留两位有效数字> <功能详细描述>
     *
     * @param divisor1 String
     * @param divisor2 String
     * @return String
     */
    public static String divide(String divisor1, String divisor2) {
        BigDecimal div1 = new BigDecimal(divisor1);
        BigDecimal div2 = new BigDecimal(divisor2);
        BigDecimal result = div1.divide(div2, 2, RoundingMode.HALF_UP);
        return result.toString();
    }

    // 使用 String 类的静态 format()方法 来确定 double 数据类型的精度
    public static String userString(double n) {
        return String.format("%.2f", n);
    }

    /**
     * 判断是否是数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
