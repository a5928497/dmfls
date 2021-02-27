package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailsRepo extends JpaRepository<Details,Integer> {

	@Query("select d from Details d where d.securities.id = :scId")
	public List<Details> findAllByScId(@Param("scId") Integer sc_id);

	@Query("select d from Details d where d.showFlag = :showFlag")
	public List<Details> findAllisShow(@Param("showFlag")Boolean showFlag);
}
