package com.valorant.dataService.service;

import com.valorant.dataService.entity.SignUpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author KenLi
 * @date 2023-07-10
 */
@Service
public class SignUpInfoService {

    @Value("${valorant.admin.data.url}")
    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<SignUpInfo> getSignUpInfoList() {
        String url = this.url;
        url += "v1/admin/signUpInfos";
        ResponseEntity<SignUpInfo[]> response = restTemplate.getForEntity(url, SignUpInfo[].class);
        if (response.hasBody()) {
            SignUpInfo[] signUpInfos = response.getBody();
            assert signUpInfos != null;
            return Arrays.asList(signUpInfos);
        }
        return null;
    }

}
