package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.UserRole;
import com.safatoyota32bit.backendproject.entity.role;
import com.safatoyota32bit.backendproject.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository <UserRole,Integer> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.user.userID = :userID AND ur.deleted = false")
    List<UserRole> findByUserIDAndNotDeleted(@Param("userID") int userID);
    @Query("SELECT ur.Role FROM UserRole ur WHERE ur.user = :user AND ur.deleted = false")
    List<role> findRolesByUser(users user);
    List<UserRole> findByUser(users user);

}
