package org.example.springbootrabbitmq.domain.chat.chat.repository;

import org.example.springbootrabbitmq.domain.chat.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomId(long roomId);
}
