package com.jwt.service;

import com.jwt.Dao.RoleDao;
import com.jwt.Dao.UserDoa;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDoa userDoa;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;
    public User resisterNewUser(User user)
    {
        return this.userDoa.save(user);
    }

    public void initRolesAdminAndUser ()
    {
        Role adminRole=new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("admin role");
        roleDao.save(adminRole);


        Role userRole=new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Defualt Role for any user");
        roleDao.save(userRole);


        User adminUser=new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserLastName("admin");
        adminUser.setUserPassword(getEncoderPassword("admin@pass"));
        Set<Role> adminRoles=new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        adminRoles.add((Role) adminRole);
        userDoa.save(adminUser);


        User user=new User();
        user.setUserFirstName("raj");
        user.setUserLastName("singh");
        user.setUserName("raj@123");
        user.setUserPassword(getEncoderPassword("raj@pass"));
        Set<Role> userRoles=new HashSet<>();
        userRoles.add((Role) userRole);
        userDoa.save(user);


    }

    public String getEncoderPassword(String password)
    {
        return passwordEncoder.encode(password);
    }
}
