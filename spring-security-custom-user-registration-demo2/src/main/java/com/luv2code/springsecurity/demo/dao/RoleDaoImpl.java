package com.luv2code.springsecurity.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springsecurity.demo.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findRoleByName(String rollname) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Query<Role> query=session.createQuery("from Role r where r.name=:rolename",Role.class);
		query.setParameter("rolename",rollname);
		Role role=null;
		try {
		role=query.getSingleResult();
		}catch (Exception e) {
			role=null;
		}
		
		return role;
	}

}
