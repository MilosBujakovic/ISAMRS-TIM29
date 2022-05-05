package com.Reservations.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Reservations.Model.Instruktor;
import com.Reservations.Model.Korisnik;
@Repository
public interface InstruktorRepo extends JpaRepository<Instruktor,String> {

	@Query(value="Select *  from instruktor where korisnicko_ime=?1 and lozinka=?2" ,nativeQuery=true)
	 Instruktor FindUserByUsernameAndPassword(String username,String password);
	 
	
}
