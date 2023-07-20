package com.jwt.service;


import com.jwt.Dao.RoleDao;
import com.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;
    public Role createNewRole(Role role){
      return roleDao.save(role);
    }
}
