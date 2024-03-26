package com.shownf.reptile.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "exp")
@Getter
@Setter
public class UserExpConfig {
    int heart;
    int comment;
    int reply;
    int post;
    int selection;
    int sale;
}