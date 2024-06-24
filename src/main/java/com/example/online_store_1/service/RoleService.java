package com.example.online_store_1.service;

import com.example.online_store_1.models.Role;

public interface RoleService {
    Role save(Role role);
    Role findByName(String name);
}
