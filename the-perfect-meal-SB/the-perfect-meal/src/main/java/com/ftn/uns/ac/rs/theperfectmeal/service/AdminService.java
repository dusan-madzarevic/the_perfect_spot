package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.helper.ServiceInterface;
import com.ftn.uns.ac.rs.theperfectmeal.model.Admin;
import com.ftn.uns.ac.rs.theperfectmeal.model.User;
import com.ftn.uns.ac.rs.theperfectmeal.repository.AdminRepository;



@Service
public class AdminService implements ServiceInterface<Admin>{
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin save(Admin a) {
		Admin saved = this.adminRepository.save(a);
		
		return saved;
	}

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	public Page<Admin> findAll(Pageable pageable){
		return adminRepository.findAll(pageable);
	}
	
	@Override
	public Admin findOne(Long id) {
		return adminRepository.findById(id).orElse(null);

	}
	
	
	@Override
	public Admin create(Admin entity) throws Exception {
		return adminRepository.save(entity);
	}

	@Override
	public Admin update(Admin entity, Long id) throws Exception {
		Admin user = adminRepository.findById(id).orElse(null);

		if (user != null) {
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());

		} else {
			throw new Exception("User with given id doesn't exist.");
		}

		return adminRepository.save(user);
	}

	@Override
	public void delete(Long id) throws Exception {
		Admin user = adminRepository.findById(id).orElse(null);

		if (user != null) {
			adminRepository.delete(user);
		} else {
			throw new Exception("User with given id doesn't exist.");
		}
	}

	public User findOneByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
