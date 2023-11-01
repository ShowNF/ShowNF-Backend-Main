package com.shownf.reptile.bean.small;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CreateUniqueNicknameBean {
    public static String exec() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // 100000부터 999999까지의 랜덤 숫자 생성

        return "user" + randomNumber;
    }

}
