package com.stefanini.internship.notificationserver.version2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotifyConf {

    private String title;
    private String body;
    private String icon;
    private String click_action;
    private String ttlInSeconds;

    public PushNotifyConf() {
    }

    public PushNotifyConf(String title, String body, String icon, 
                                    String click_action, String ttlInSeconds) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.click_action = click_action;
        this.ttlInSeconds = ttlInSeconds;
    }
}
