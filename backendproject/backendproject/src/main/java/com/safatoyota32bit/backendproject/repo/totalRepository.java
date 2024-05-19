package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.total;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface totalRepository extends JpaRepository <total,Integer> {

    @Query("SELECT t FROM total t WHERE "
            + "LOWER(t.user.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
            + "LOWER(t.user.lastName) LIKE LOWER(CONCAT('%', :filter, '%')) OR "
            + "LOWER(t.salesType.saleType) LIKE LOWER(CONCAT('%', :filter, '%'))")
    Page<total> findByCriteria(String filter, Pageable pageable);

}
