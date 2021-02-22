package com.jessonzh.learning.exchange;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;

public class HttpGetExample {

    public static void main(String args[]) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://www.kylc.com/bank/rmbfx/b-hsbc.html");
        HttpResponse response = httpclient.execute(httpget);

        System.out.println(response.getStatusLine());

        InputStream inputStream = response.getEntity().getContent();
        Document document = Jsoup.parse(inputStream, null, "https://www.kylc.com/bank/rmbfx/b-hsbc.html");
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
            System.out.println(names[i] + " : " +text);
        }
    }
}


