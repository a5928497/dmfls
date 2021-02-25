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

    //true为已存在,false为不存在
    public boolean is_existed(Integer clientId, Integer SCId) {
        boolean result = false;
        result = findAllBySCIdAndClientId(clientId, SCId).size() > 0;
        return result;
    }

    public List<OpenRecord> findAllBySCIdAndClientId(Integer clientId, Integer SCId) {
        List<OpenRecord> openRecords = openRecordRepo.findAllByClientIdAndSecuritiesId(clientId, SCId);
        return openRecords;
    }

    public List<OpenRecord> queryOpenRecordBySecurities(Integer SCId, String order) {
        switch (order) {
            case "OPEN":
                return openRecordRepo.findAllOpenBySecuritiesId(SCId);
            case "NOT_OPEN":
                return openRecordRepo.findNotOpenBySecuritiesId(SCId);
            default:
                return openRecordRepo.findAllBySecuritiesId(SCId);
        }
    }
}
