package com.project.matchingsystem.domain;

import com.project.matchingsystem.enums.TransactionStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatusEnum transactionStatus;

    public Transaction(Item item, User user) {
        this.item = item;
        this.user = user;
        this.transactionStatus = TransactionStatusEnum.HOLD;
    }

    public void updateStatusToComplete() {
        this.transactionStatus = TransactionStatusEnum.COMPLETE;
    }

    public void updateStatusToCancel() {
        this.transactionStatus = TransactionStatusEnum.CANCEL;
    }

    public void updateStatusToDelete() {
        this.transactionStatus = TransactionStatusEnum.DELETE;
    }

}
