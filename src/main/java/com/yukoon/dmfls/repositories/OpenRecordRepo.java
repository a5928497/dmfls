package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.OpenRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpenRecordRepo extends JpaRepository<OpenRecord,Integer> {
    @Query()
    public List<OpenRecord> findAllByClientIdAndSecuritiesId(Integer clientId,Integer securitiesId);

    @Query("select o from OpenRecord o where o.securities.id = :scId and o.is_open = 1")
    public  List<OpenRecord> findAllOpenBySecuritiesId(@Param("scId")Integer scId);

    @Query("select o from OpenRecord o where o.securities.id = :scId and o.is_open = 0")
    public  List<OpenRecord> findNotOpenBySecuritiesId(@Param("scId")Integer scId);

    @Query()
    public List<OpenRecord> findAllBySecuritiesId(Integer securitiesId);
}
