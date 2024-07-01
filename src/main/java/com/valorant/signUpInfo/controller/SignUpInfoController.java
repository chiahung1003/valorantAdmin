package com.valorant.signUpInfo.controller;

import com.valorant.dataService.entity.SignUpInfo;
import com.valorant.dataService.service.SignUpInfoService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author KenLi
 * @date 2023-08-03
 */
@Controller
@RequestMapping("/signUpInfo")
public class SignUpInfoController {

    private SignUpInfoService signUpInfoService;

    @Autowired
    public void setSignUpInfoService(SignUpInfoService signUpInfoService) {
        this.signUpInfoService = signUpInfoService;
    }

    @GetMapping("init")
    public String init(Model model) {
        return "signUpInfo/signUpInfo";
    }

    @GetMapping("recordExport")
    public void recordExport(HttpServletResponse response) throws IOException {

        List<String> csvHeader = Arrays.asList(
                "報名序號", "報名日期", "UID", "姓名", "手機號碼", "身份證字號", "生日", "地址", "RIOT_ID");

        List<SignUpInfo> signUpInfoList = signUpInfoService.getSignUpInfoList();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        String fileName = "報名申請資料_" + ZonedDateTime.now(ZoneId.of("Asia/Taipei")).toInstant().toEpochMilli() + ".csv";

        CSVPrinter csvPrinter = new CSVPrinter(osw, CSVFormat.DEFAULT);

        csvPrinter.printRecord(csvHeader);

        for (SignUpInfo signUpInfo : signUpInfoList) {
            List<String> data = Arrays.asList(
                    signUpInfo.getSignUpInfoId().toString(),
                    sdf.format(signUpInfo.getCreateAt()),
                    signUpInfo.getUid(),
                    signUpInfo.getName(),
                    signUpInfo.getPhone(),
                    signUpInfo.getCustId(),
                    sdf2.format(signUpInfo.getBirthday()),
                    signUpInfo.getAddress(),
                    signUpInfo.getRiotId()
            );
            csvPrinter.printRecord(data);
        }
        csvPrinter.flush();

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = " + java.net.URLEncoder.encode(fileName, "UTF-8");
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bos.toByteArray());
        outputStream.close();
    }
}
