package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.total;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface totalRepository extends JpaRepository <total,Integer> {

    @Query("SELECT t FROM total t JOIN t.User u JOIN t.SalesType s WHERE  "
            + "LOWER(u.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
            + "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
            + "LOWER(s.saleType) LIKE LOWER(CONCAT('%', :filter, '%'))")
    Page<total> findByCriteria(String filter, Pageable pageable);

}
