package com.example.googlecontroller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.googlecontroller.UserData;
@Repository
public interface UserJpa extends JpaRepository<UserData, Integer> {
	UserData findByEmail(String email);

}
