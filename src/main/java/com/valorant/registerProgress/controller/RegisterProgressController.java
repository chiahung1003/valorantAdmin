package com.valorant.registerProgress.controller;

import com.valorant.dataService.entity.RegisterProgress;
import com.valorant.dataService.service.RegisterProgressService;
import com.valorant.registerProgress.form.RegisterProgressForm;
import com.valorant.registerProgress.form.RegisterProgressVo;
import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KenLi
 * @date 2023-07-25
 */
@Controller
@RequestMapping("/registerProgress/")
public class RegisterProgressController {

    private RegisterProgressService registerProgressService;

    @Autowired
    public void setRegisterProgressService(RegisterProgressService registerProgressService) {
        this.registerProgressService = registerProgressService;
    }

    @GetMapping("init")
    public String getRegisterProgress(Model model) {
        List<RegisterProgress> registerProgressList = registerProgressService.getRegisterProgress();

        RegisterProgressForm registerProgressForm = new RegisterProgressForm();
        List<RegisterProgressVo> registerProgressVoList = new ArrayList<>();

        for (RegisterProgress registerProgress : registerProgressList) {
            RegisterProgressVo registerProgressVo = new RegisterProgressVo();
            registerProgressVo.setArea(ESAPI.encoder().encodeForHTML(registerProgress.getArea()));
            registerProgressVo.setCount(ESAPI.encoder().encodeForHTML(registerProgress.getCount()));
            registerProgressVoList.add(registerProgressVo);
        }

        registerProgressForm.setRegisterProgressVoList(registerProgressVoList);
        model.addAttribute("registerProgressForm", registerProgressForm);

        return "registerProgress/registerProgressSetting";
    }

    @PostMapping("update")
    public String updateRegisterProgress(Model model, RegisterProgressForm registerProgressForm) {

        List<RegisterProgressVo> registerProgressVoList = registerProgressForm.getRegisterProgressVoList();

        List<RegisterProgress> registerProgressList = new ArrayList<>();
        for (RegisterProgressVo registerProgressVo : registerProgressVoList) {
            String area = ESAPI.encoder().encodeForHTML(registerProgressVo.getArea());
            String count = ESAPI.encoder().encodeForHTML(registerProgressVo.getCount());

            RegisterProgress registerProgress = new RegisterProgress();
            registerProgress.setArea(area);
            registerProgress.setCount(count);
            registerProgressList.add(registerProgress);
        }
        registerProgressService.updateRegisterProgress(registerProgressList);

        registerProgressVoList.clear();
        registerProgressList = registerProgressService.getRegisterProgress();
        for (RegisterProgress registerProgress : registerProgressList) {
            RegisterProgressVo registerProgressVo = new RegisterProgressVo();
            registerProgressVo.setArea(ESAPI.encoder().encodeForHTML(registerProgress.getArea()));
            registerProgressVo.setCount(ESAPI.encoder().encodeForHTML(registerProgress.getCount()));
            registerProgressVoList.add(registerProgressVo);
        }

        registerProgressForm.setRegisterProgressVoList(registerProgressVoList);
        model.addAttribute("registerProgressForm", registerProgressForm);
        model.addAttribute("result", "success");

        return "registerProgress/registerProgressSetting";
    }
}
