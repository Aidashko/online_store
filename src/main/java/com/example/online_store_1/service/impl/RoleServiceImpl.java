package com.example.online_store_1.service.impl;

import com.example.online_store_1.models.Role;
import com.example.online_store_1.repository.RoleRepo;
import com.example.online_store_1.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo repo;
    @Override
    public Role save(Role role) {
        return repo.save(role);
    }

    @Override
    public Role findByName(String name) {
        return repo.findByName(name);
    }
}
