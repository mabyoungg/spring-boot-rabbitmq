package org.example.springbootrabbitmq.global.initData;

import lombok.RequiredArgsConstructor;
import org.example.springbootrabbitmq.domain.chat.chat.entity.ChatRoom;
import org.example.springbootrabbitmq.domain.chat.chat.service.ChatService;
import org.example.springbootrabbitmq.domain.member.member.entity.Member;
import org.example.springbootrabbitmq.domain.member.member.service.MemberService;
import org.example.springbootrabbitmq.global.app.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class All {
    @Autowired
    @Lazy
    private All self;
    private final MemberService memberService;
    private final ChatService chatService;

    @Bean
    @Order(2)
    public ApplicationRunner initAll() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        new File(AppConfig.getTempDirPath()).mkdirs();

        if (memberService.findByUsername("system").isPresent()) return;

        Member memberSystem = memberService.join("system", "1234").getData();
        memberSystem.setRefreshToken("system");

        Member memberAdmin = memberService.join("admin", "1234").getData();
        memberAdmin.setRefreshToken("admin");

        Member memberGarage = memberService.join("garage", "1234").getData();
        memberGarage.setRefreshToken("garage");

        Member memberUser1 = memberService.join("user1", "1234").getData();
        memberUser1.setRefreshToken("user1");

        Member memberUser2 = memberService.join("user2", "1234").getData();
        memberUser2.setRefreshToken("user2");

        Member memberUser3 = memberService.join("user3", "1234").getData();
        memberUser3.setRefreshToken("user3");

        Member memberUser4 = memberService.join("user4", "1234").getData();
        memberUser4.setRefreshToken("user4");

        ChatRoom room1 = chatService.createRoom(memberUser1, "room1");
        ChatRoom room2 = chatService.createRoom(memberUser2, "room2");
        ChatRoom room3 = chatService.createRoom(memberUser3, "room3");

        chatService.writeMessage(room1, memberUser1, "message1");
        chatService.writeMessage(room1, memberUser1, "message2");
        chatService.writeMessage(room1, memberUser1, "message3");

        chatService.writeMessage(room1, memberUser2, "message4");
        chatService.writeMessage(room1, memberUser2, "message5");

        chatService.writeMessage(room1, memberUser3, "message6");

        chatService.writeMessage(room2, memberUser1, "message7");
        chatService.writeMessage(room2, memberUser2, "message8");

        chatService.writeMessage(room3, memberUser1, "message9");
    }
}