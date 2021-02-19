package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.ImportantNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IN_Repo extends JpaRepository<ImportantNotice,Integer> {

	@Query("select i from ImportantNotice i where i.securities.id = :scId")
	public List<ImportantNotice> findAllByScId(@Param("scId") Integer sc_id);
}
