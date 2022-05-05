package com.Reservations.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Reservations.Model.Korisnik;
import com.Reservations.Model.Vlasnik;
@Repository
public interface VlasnikRepo extends JpaRepository<Vlasnik,String> {

	@Query(value="Select *  from vlasnici where korisnicko_ime=?1 and lozinka=?2" ,nativeQuery=true)
	 Vlasnik FindUserByUsernameAndPassword(String username,String password);
	 
	
}
