package com.jessonzh.learning.exchange;

import com.vip.vjtools.vjkit.collection.CollectionUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@Service("fxRateQueryService")
public class FxRateQueryService {

    private static final String HSBC_BASE_URL = "https://www.kylc.com/bank/rmbfx/b-hsbc.html";
    private static final String CMB_BASE_URL = "https://www.kylc.com/bank/rmbfx/b-cmb.html";

    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    protected static final Logger log = LoggerFactory.getLogger(FxRateQueryService.class);

    public BigDecimal getHsbcRateCny2Hkd() {
        return getRateCoreCny2Hkd(HSBC_BASE_URL);
    }

    public BigDecimal getCmbRateCny2Hkd() {
        return getRateCoreCny2Hkd(CMB_BASE_URL);
    }

    public BigDecimal getRateCoreCny2Hkd(String baseUrl) {
        HttpGet httpget = new HttpGet(baseUrl);

        InputStream inputStream;
        Document document;
        try {
            HttpResponse response = httpclient.execute(httpget);
            log.info(baseUrl);
            log.info(response.getStatusLine().toString());
            inputStream = response.getEntity().getContent();
            document = Jsoup.parse(inputStream, null, baseUrl);
            Element element = document.getElementById("bank_rate_table");
            Elements rows = element.select("tr");
            // 汇丰中国 港币牌价
            Element hkdRow = rows.get(1);
            // 购汇币种|现汇买入|现钞买入|现汇卖出|现钞卖出|发布时间
            String[] names = "购汇币种|现汇买入|现钞买入|现汇卖出|现钞卖出|发布时间".split("\\|");
            Elements tds = hkdRow.select("td");
            // 选择某一个td节点
            for (int i = 0; i < 6; i++) {
                Element td = tds.get(i);
                //获取文本信息
                String text = td.text();
                //输出到控制台
                log.info(names[i] + " : " +text);
            }
            if (CollectionUtil.isNotEmpty(tds)) {
                return new BigDecimal(tds.get(3).text());
            }
        } catch (IOException e) {
            log.error("获取汇率失败", e);
        }
        return null;
    }
}
