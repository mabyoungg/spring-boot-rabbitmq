package org.example.springbootrabbitmq.chat.repository;

import org.example.springbootrabbitmq.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
