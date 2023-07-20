package com.jwt.controller;

import com.jwt.Dao.RoleDao;
import com.jwt.entity.Role;
import com.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping({"/createNewRole"})
    public Role cretaNewRole( @RequestBody Role role)
    {
        return this.roleService.createNewRole(role);
    }
}
