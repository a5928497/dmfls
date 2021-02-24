package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.OpenRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenRecordRepo extends JpaRepository<OpenRecord,Integer> {
}
