package com.trip.hotel.test.web;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailReport {
    private static final Log logger = LogFactory.getLog(EmailReport.class);

    public static void main(String[] args) throws IOException {

        PropertyConfigurator.configure("log4j.properties");
        logger.info("发送Report邮件");

        String baseURL = args[0];

        // 读Overview.html
        Document doc = Jsoup.connect(baseURL + "overview.html").get();
        Element body = doc.body();

        // 最后加上报告的完整链接
        body.append(String.format("<br/><div>详细信息见：<a href='%1$s'>%1$s</a></div>", baseURL));

        // 超链接的相对路径改为绝对路径
        Elements tagAs = body.getElementsByTag("a");
        for (int i = 0, count = tagAs.size(); i < count; i++) {
            Element a = tagAs.get(i);
            String href = a.attr("href");
            if (!href.startsWith("http")) {
                a.attr("href", baseURL + href);
            }
        }

        // 删除外部样式表，改用内部样式表。简化。em改用px。
        Element head = doc.head();
        head.getElementsByTag("link").remove();
        String css = "#meta                    {font-size: 12px; text-align: right; float: right;}\n" +
                ".passed                  {background-color: #44aa44;}\n" +
                ".skipped                 {background-color: #ffaa00;}\n" +
                ".failed                  {background-color: #ff4444;}\n" +
                ".failedConfig            {background-color: #800000; color: #ffffff}\n" +
                ".skippedConfig           {background-color: #cc6600; color: #ffffff}\n" +
                ".totalLabel              {font-weight: bold; background-color: #ffffff;}\n" +
                "\n" +
                ".header                  {font-size: 16px; font-weight: bold; text-align: left; background-color: #999999;}\n" +
                ".suiteLinks              {float: right; font-weight: normal; vertical-align: middle;}\n" +
                ".suiteLinks a            {color: #ffffff; margin-left: 6px;}\n" +
                ".totalLabel              {font-weight: bold; background-color: #ffffff;}\n" +
                "\n" +
                ".suite                   {background-color: #999999; font-weight: bold;}\n" +
                ".test                    {background-color: #eeeeee; padding-left: 32px;}\n" +
                ".test .passed            {background-color: #88ee88;}\n" +
                ".test .skipped           {background-color: #ffff77;}\n" +
                ".test .failed            {background-color: #ff8888;}\n" +
                ".group                   {background-color: #cccccc; color: #000000; font-weight: bold;}\n" +
                "\n" +
                "\n" +
                ".overviewTable           {width: 100%; line-height: 24px; border-spacing: 1px;}\n" +
                ".overviewTable td        {padding: 0 17px;}\n" +
                ".overviewTable th        {padding: 0 8px; }\n" +
                ".overviewTable .duration {width: 90px;}\n" +
                ".overviewTable .passRate {width: 90px;}\n" +
                ".overviewTable .number   {width: 80px;}\n" +
                ".overviewTable tr        {height: 17px;}";
        Element style = head.appendElement("style");
        style.attr("type", "text/css");
        style.html(css);

        // 为了多个table的列对齐，合并到一个Table里面。
        Elements table = body.getElementsByTag("table");
        Element firstTable = table.get(0);
        Elements tbodys = body.getElementsByTag("tbody");
        int tableBodyCount = tbodys.size();
        if (tableBodyCount > 1) {
            for (int i = 1; i < tableBodyCount; i++) {
                Element tbody = tbodys.get(i);
                firstTable.append("<tbody><tr/><tr/></tbody>");
                firstTable.appendChild(tbody);
            }
        }

        // Outlook中不支持td中的float，改位置。
        Elements suites = body.getElementsByClass("header suite");
        for (int i = 0, count = suites.size(); i < count; i++) {
            Element suite = suites.get(i);
            Element suiteLink = suite.getElementsByClass("suiteLinks").get(0);
            suite.append(String.format("(%1$s)", suiteLink.html()));
            suiteLink.remove();
        }

        // 组装发邮件请求数据
        String from = "IBU-Hotel-Android-UI-Test-Report-no-reply@ctrip.com";
        String[] to = {"fbyan@ctrip.com", "yefeiyang@Ctrip.com>"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String subject = "Android Hotel TestReport - " + format.format(new Date());
        String text = doc.html();
        text = "<!DOCTYPE HTML><html>" + text.substring(text.indexOf("<head>")); //改成html5的标准
        String type = "html";
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        json.put("subject", subject);
        json.put("text", text);
        json.put("mimeSubType", type);

        // 调发邮件的接口
        URL object = new URL("http://andromeda.ibu.ctripcorp.com/api/mail/send");
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoInput(true);
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {

            os.write(json.toString().getBytes("UTF-8"));
            os.close();

            InputStream in = new BufferedInputStream(con.getInputStream());
            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            logger.info("Response: " + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            boolean success = jsonObject.optBoolean("success");
            if (success) {
                logger.info("Email sent!");
            } else {
                logger.warn("Failed to Send Report Email!");
            }
        } finally {
            con.disconnect();
        }

    }
}
