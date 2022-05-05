package com.Reservations.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Reservations.Model.Administrator;
@Repository
public interface AdminRepo extends JpaRepository<Administrator,String> {

	@Query(value="Select *  from administratori where korisnicko_ime=?1 and lozinka=?2" ,nativeQuery=true)
	 Administrator findByUsernameAndPassword(String username,String password);
	
	
}
