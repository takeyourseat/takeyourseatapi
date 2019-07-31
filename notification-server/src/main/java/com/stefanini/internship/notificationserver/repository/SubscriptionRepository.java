package com.stefanini.internship.notificationserver.repository;


import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionDao, Long> {

	SubscriptionDao findByUsername(String username);

	SubscriptionDao deleteByUsername(String username);


}
