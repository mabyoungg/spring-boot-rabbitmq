package org.example.springbootrabbitmq.domain.chat.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.springbootrabbitmq.domain.member.member.entity.Member;
import org.example.springbootrabbitmq.global.jpa.entity.BaseTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class ChatRoom extends BaseTime {
    @ManyToOne
    private Member owner;
    private String name;
    @Column(columnDefinition = "BOOLEAN default true")
    private boolean published;
}
