package com.jessonzh.learning.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("hkdExchangeCalcService")
public class HkdExchangeCalcService {

    private static final Logger log = LoggerFactory.getLogger(HkdExchangeCalcService.class);

    @Autowired
    private FxRateQueryService fxRateQueryService;

    /**
     * 汇丰中国现汇卖出价格
     */
    public BigDecimal hsbcExchangeRate;

    /**
     * 招商银行 现汇卖出价格
     */
    public BigDecimal cmbExchangeRate;

    public BigDecimal getCmdRemaining(BigDecimal totalCny) {
        return ((totalCny.subtract(new BigDecimal("150")))
                .divide(cmbExchangeRate, 2, BigDecimal.ROUND_HALF_DOWN))
                .subtract(new BigDecimal("10"));
    }

    public BigDecimal getHsbcRemaining(BigDecimal totalCny) {
        return totalCny.divide(hsbcExchangeRate, 2, BigDecimal.ROUND_HALF_DOWN);
    }

    public String selectOne(BigDecimal totalCny) {
        cmbExchangeRate = fxRateQueryService.getCmbRateCny2Hkd();
        hsbcExchangeRate = fxRateQueryService.getHsbcRateCny2Hkd();
        int flag = 0;
        for (int i = 1; i < 200000; i++) {
            BigDecimal total = new BigDecimal(i);
            if (getCmdRemaining(total).compareTo(getHsbcRemaining(total)) >= 0) {
                flag = i;
                break;
            }
        }

        return    ("招商银行 现汇卖出：" + cmbExchangeRate + "</br>")
                + ("汇丰银行 现汇卖出：" + hsbcExchangeRate + "</br>")
                + ("低于 " + flag + " CNY，优先使用汇丰银行</br>")
                + ("高于 " + flag + " CNY，优先使用永隆银行</br>")
                + totalCny +" CNY汇入永隆银行 剩余： " + getCmdRemaining(totalCny) + " HKD</br>"
                + totalCny +" CNY汇入香港汇丰 剩余： " + getHsbcRemaining(totalCny) + " HKD";
    }
}
