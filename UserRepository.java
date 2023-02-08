package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.inti.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	//public Users findByEmailaddress(String email);


//	// [USER,ADMIN,..]
//	@Query("from UserRoles where email = :email")
//	public List<String> getUserRoles(@Param("email") String email);
	  @Query("SELECT u FROM Users u WHERE u.usersEmail = ?1")
	    public Users findByEmail(String email);
}
