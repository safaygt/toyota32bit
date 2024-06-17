package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.User;
import com.safatoyota32bit.backendproject.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository <UserRole,Integer> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.User.userID = :userID AND ur.deleted = false")
    List<UserRole> findByUserIDAndNotDeleted(@Param("userID") int userID);

    List<UserRole> findByUser(User user);

}
