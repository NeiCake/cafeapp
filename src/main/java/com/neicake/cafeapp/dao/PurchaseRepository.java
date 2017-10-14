package com.neicake.cafeapp.dao;

import com.neicake.cafeapp.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    List<Purchase> findAllByDateBetween(Date start, Date end);
}
