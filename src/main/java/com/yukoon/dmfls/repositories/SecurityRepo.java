package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.Securities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityRepo extends JpaRepository<Securities,Integer> {

    @Query("select s from Securities s where s.showFlag = :showFlag")
    public List<Securities> findAllisShow(@Param("showFlag")Boolean showFlag);
}
