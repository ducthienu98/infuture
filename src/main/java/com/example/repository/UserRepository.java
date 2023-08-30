package com.example.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
	@Query("SELECT COUNT(f) FROM Follow f WHERE f.followed.user_id = :userId")
    int getFollowedsCount(@Param("userId") int userId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.user_id = :userId")
    int getFollowersCount(@Param("userId") int userId);
    
    List<User> findByFullnameContainingIgnoreCase(String fullname);
}
