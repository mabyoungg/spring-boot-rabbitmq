package org.example.springbootrabbitmq.domain.chat.chat.repository;

import org.example.springbootrabbitmq.domain.chat.chat.entity.ChatRoom;
import org.example.springbootrabbitmq.domain.member.member.entity.Member;
import org.example.springbootrabbitmq.standard.base.KwTypeV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatRoomRepositoryCustom {
    Page<ChatRoom> findByKw(KwTypeV2 kwType, String kw, Member owner, Boolean published, Pageable pageable);
}
