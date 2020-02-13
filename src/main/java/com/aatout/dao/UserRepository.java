package com.aatout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aatout.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	
	
	
	/*@Query("From AppUser Ap Where Ap.supprime= false and Ap.enabled = true")
	List<AppUser>listUser();*/
	@Query("From AppUser Ap Where Ap.supprime= false and Ap.enabled = true and Ap.id =:id")
	public AppUser listUserById(@Param("id") Long id);
	
	
	
	@Query("Select nom, prenom From AppUser")
	List<AppUser> nomUtilisateurs();
	
	public AppUser findBySupprime(Boolean supprime );
	public AppUser findById(Long id);
	public AppUser findByUsername(String username);
	
	//public AppUser findByUsernameAndSupprimeIsFalseAndStatusIsFalse(String username);
	
	public AppUser findBySupprimeIsFalseAndEnabledIsTrueAndEmailIs(String email);
	
	public AppUser findByConfirmationToken(String confirmationToken);
	
	public List<AppUser> findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsTrueAndAdminstratifIsTrue();
	
	public List<AppUser> findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsFalseAndAdminstratifIsTrue();
	
public List<AppUser> findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsTrueAndAdminstratifIsFalse();
	
	public List<AppUser> findBySupprimeIsFalseAndStatusIsFalseAndEnabledIsTrueAndActiveIsTrueAndAccountNonLockedIsFalseAndAdminstratifIsFalse();
	
	public List<AppUser> findBySupprimeIsFalseAndEnabledIsTrueAndActiveIsFalse();
	
	public List<AppUser> findBySupprimeIsFalseAndEnabledIsTrueAndNomLike(String mc);
	
	 	@Modifying
	    @Query("update AppUser u set u.password = :password where u.id = :id")
	    void updatePassword(@Param("password") String password, @Param("id") Long id);

}
