package com.example.online_store_1.repository;

import com.example.online_store_1.models.Operation;
import com.example.online_store_1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Long> {
    List<Operation> findByUser(User user);
}
