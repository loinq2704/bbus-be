package com.fpt.bbusbe.model.dto.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SignInRequest implements Serializable {

    private String phone;
    private String password;
    private String platform;        //web, mobile
    private String deviceToken;     // insert vào db để có các action push noti nào thì sẽ push theo deviceToken để đến được app đấy
    private String versionApp;

}
