package com.project.matchingsystem.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String sellerName;

    public Chatting(String roomId, String userName, String sellerName) {
        this.roomId = roomId;
        this.userName = userName;
        this.sellerName = sellerName;
    }

}
