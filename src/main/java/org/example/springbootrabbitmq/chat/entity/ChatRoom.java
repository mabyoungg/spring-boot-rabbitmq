package org.example.springbootrabbitmq.chat.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.springbootrabbitmq.global.jpa.entity.BaseTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class ChatRoom extends BaseTime {
    private String name;
}
