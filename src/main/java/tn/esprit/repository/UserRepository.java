package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.entities.DataPoint;
import tn.esprit.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select c from User c where c.username=:username")
	public User getUserByUsername(@Param("username") String username);

	@Query("select c from User c where c.userRole=:userRole")
	public List<User> getUserByRole(@Param("userRole") String userRole);

	@Query("select c from User c where c.username=:username and c.password=:password")
	public User getUserAutentificate(@Param("username") String username, @Param("password") String password);

	@Query("select c from User c where c.blocked=:isBlocked")
	public List<User> getLockedUsers(@Param("isBlocked") Boolean isBlocked);

	@Query("select c from User c where c.firstName=:firstName or c.lastName=:lastName")
	public List<User> getUserByFirstNameOrLastname(@Param("firstName") String firstName,
			@Param("lastName") String lastName);

	// @Query("select c.blocked as blocked ,count(1) as nbr from User c group by
	// c.blocked ")
	@Query(" SELECT "
			+ "CASE e.blocked "
			+ "WHEN true THEN 'blocked users' "
			+ "WHEN false THEN 'unblocked users' "
			+ "ELSE e.blocked "
			+ "END " 
			+", count(1) as y "
			+ "FROM User e group by e.blocked")
	public List getLockUnlockUser();

}
