package com.project.matchingsystem.dto.response;

import com.project.matchingsystem.domain.Transaction;
import com.project.matchingsystem.enums.TransactionStatusEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionResponseDto {

    private final Long id;
    private final String nickname;
    private final String itemName;
    private final String status;
    private final String createdAt;
    private final String modifiedAt;

    public TransactionResponseDto(Long id, String nickname, String itemName, TransactionStatusEnum status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.nickname = nickname;
        this.itemName = itemName;
        this.status = status.name();
        this.createdAt = createdAt.toString();
        this.modifiedAt = modifiedAt.toString();
    }

    public TransactionResponseDto(Transaction transaction, String nickname) {
        this.id = transaction.getId();
        this.nickname = nickname;
        this.itemName = transaction.getItem().getName();
        this.status = transaction.getTransactionStatus().name();
        this.createdAt = transaction.getCreatedAt().toString();
        this.modifiedAt = transaction.getModifiedAt().toString();
    }

}
