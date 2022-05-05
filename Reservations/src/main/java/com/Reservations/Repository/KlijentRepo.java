package com.Reservations.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Reservations.Model.Klijent;
import com.Reservations.Model.Korisnik;
@Repository
public interface KlijentRepo extends JpaRepository<Klijent,String> {

	@Query(value="Select *  from klijent where korisnicko_ime=?1 and lozinka=?2" ,nativeQuery=true)
	 Klijent FindUserByUsernameAndPassword(String username,String password);
	 
	
}
