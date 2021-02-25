package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.OpenRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpenRecordRepo extends JpaRepository<OpenRecord,Integer> {
    @Query()
    public List<OpenRecord> findAllByClientIdAndSecuritiesId(Integer clientId,Integer securitiesId);
}
