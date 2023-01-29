package com.project.matchingsystem.chatting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.matchingsystem.domain.Chatting;
import com.project.matchingsystem.domain.User;
import com.project.matchingsystem.exception.ErrorCode;
import com.project.matchingsystem.repository.ChattingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;
    private final ChattingRepository chattingRepository;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findMyRoom(String nickName) {
        ArrayList<ChatRoom> list = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms.values()) {
            if (chatRoom.getUserName().equals(nickName)||chatRoom.getSellerName().equals(nickName)) {
                list.add(chatRoom);
            }
        }
        return list;
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    @Transactional
    public ChatRoom createRoom(User user, String sellerName) {
        if (chattingRepository.existsByUserNameAndSellerName(user.getNickname(), sellerName)) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_CHATTING.getMessage());
        }

        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .userName(user.getNickname())
                .sellerName(sellerName)
                .build();
        chatRooms.put(randomId, chatRoom);
        chattingRepository.save(new Chatting(randomId,user.getNickname(),sellerName));
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
