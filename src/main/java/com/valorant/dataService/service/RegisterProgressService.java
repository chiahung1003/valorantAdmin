package com.valorant.dataService.service;

import com.valorant.dataService.entity.RegisterProgress;
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
public class RegisterProgressService {

    @Value("${valorant.admin.data.url}")
    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RegisterProgress> getRegisterProgress() {
        String getRegisterProgressUrl = url + "v1/admin/registerProgress";
        ResponseEntity<RegisterProgress[]> response = restTemplate.getForEntity(getRegisterProgressUrl, RegisterProgress[].class);
        if (response.hasBody()) {
            RegisterProgress[] registerProgresses = response.getBody();
            assert registerProgresses != null;
            return Arrays.asList(registerProgresses);
        }
        return null;
    }

    public void updateRegisterProgress(List<RegisterProgress> registerProgressList) {
        String updateRegisterProgressUrl = url + "v1/admin/registerProgress";
        restTemplate.postForEntity(updateRegisterProgressUrl, registerProgressList, String.class);
    }
}
