package com.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	public Users findByEmail(String email);
}
