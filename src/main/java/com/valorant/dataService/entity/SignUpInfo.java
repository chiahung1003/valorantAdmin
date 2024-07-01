package com.valorant.dataService.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SignUpInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long signUpInfoId;

    private String uid;

    private String name;

    private String phone;

    private String custId;

    private String email;

    private Date birthday;

    private String address;

    private String riotId;

    private Date createAt;

}
