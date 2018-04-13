package com.trip.hotel.test.web;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmailReport {
    private static final Log logger = LogFactory.getLog(EmailReport.class);

    public static void main(String[] args) throws IOException {

        String from = "IBU-Hotel-Android-UI-Test-Report-no-reply@ctrip.com";
        String[] to = {"fbyan@ctrip.com", "yefeiyang@Ctrip.com>"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String subject = "Android Hotel TestReport - " + format.format(new Date());
//        String emailableReportFile = "D:/Users/yefeiyang/.jenkins/workspace/AppHotelTest/target/surefire-reports/html/index.html";
        String emailableReportFile = "/Users/fabinyan/Documents/Ctrip/Test/surefire-reports/emailable-report.html";
        String text = FileUtils.readFileToString(new File(emailableReportFile), "utf-8");
        String type = "html";
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        json.put("subject", subject);
        json.put("text", text);
        json.put("mimeSubType", type);


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

            // read the response
            InputStream in = new BufferedInputStream(con.getInputStream());
            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            logger.info("Response: " + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            boolean success = jsonObject.optBoolean("success");
            if (success) {
                logger.info("Email sent!");
            } else {
                logger.warn("Failed to send report!");
            }
        } finally {
            con.disconnect();
        }

    }
}
