package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.Entities.OpenRecord;
import com.yukoon.dmfls.repositories.OpenRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenRecordService {
    @Autowired
    private OpenRecordRepo openRecordRepo;

    public List<OpenRecord> saveOpenRecords(List<OpenRecord> openRecords) {
        openRecords = openRecordRepo.save(openRecords);
        return openRecords;
    }
}
