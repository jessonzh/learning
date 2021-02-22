package com.jessonzh.learning.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/fx-service")
public class FxController {

    @Autowired
    private HkdExchangeCalcService hkdExchangeCalcService;

    @GetMapping(value = "select")
    public String selectOne(@RequestParam(value = "totalCny") Integer totalCny) {
        BigDecimal decimal = new BigDecimal(totalCny);
        return hkdExchangeCalcService.selectOne(decimal);
    }
}
