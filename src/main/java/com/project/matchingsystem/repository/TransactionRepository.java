package com.project.matchingsystem.repository;

import com.project.matchingsystem.domain.Transaction;
import com.project.matchingsystem.dto.response.TransactionResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select new com.project.matchingsystem.dto.response.TransactionResponseDto(t.id, u.nickname, i.name, t.transactionStatus, t.createdAt, t.modifiedAt) from Transaction t join t.user u join t.item i where t.item.id = :itemId")
    List<TransactionResponseDto> findByItemIdWith(@Param("itemId") Long itemId);

    @Query("select t from Transaction t join fetch t.user join fetch t.item where t.item.id = :itemId")
    List<Transaction> findByItemId(@Param("itemId") Long itemId);

}
