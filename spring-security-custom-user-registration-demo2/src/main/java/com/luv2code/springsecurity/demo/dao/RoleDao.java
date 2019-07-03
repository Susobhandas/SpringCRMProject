package com.luv2code.springsecurity.demo.dao;

import java.util.Collection;
import java.util.List;

import com.luv2code.springsecurity.demo.entity.Role;

public interface RoleDao {

	Role findRoleByName(String string);

}
