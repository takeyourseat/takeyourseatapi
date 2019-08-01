package com.stefanini.internship.notificationserver.repository;


import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionDao, Long> {

	Optional<SubscriptionDao> findByUsername(String username);

	List<SubscriptionDao> findAllByUsername(String username);

	Optional<SubscriptionDao> deleteByUsername(String username);


}
