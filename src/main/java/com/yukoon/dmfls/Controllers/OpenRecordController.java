package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.OpenRecord;
import com.yukoon.dmfls.Services.OpenRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class OpenRecordController {
    @Autowired
    private OpenRecordService openRecordService;

    @GetMapping("/SCall/{SCId}")
    public String findAllBySC(@PathVariable("SCId")Integer SCId, Map<String,Object> map) {
        List<OpenRecord> openRecords = openRecordService.queryOpenRecordBySecurities(SCId,"ALL");
        map.put("openRecords",openRecords);
        map.put("order","ALL");
        map.put("scId",SCId);
        return "backend/open_records.html";
    }

    @GetMapping("/SCopen/{SCId}")
    public String findAllOpenBySC(@PathVariable("SCId")Integer SCId,Map<String,Object> map) {
        List<OpenRecord> openRecords = openRecordService.queryOpenRecordBySecurities(SCId,"OPEN");
        map.put("openRecords",openRecords);
        map.put("order","OPEN");
        map.put("scId",SCId);
        return "backend/open_records.html";
    }

    @GetMapping("/SCnotopen/{SCId}")
    public String findAllNotOpenBySC(@PathVariable("SCId")Integer SCId,Map<String,Object> map) {
        List<OpenRecord> openRecords = openRecordService.queryOpenRecordBySecurities(SCId,"NOT_OPEN");
        map.put("openRecords",openRecords);
        map.put("order","NOT_OPEN");
        map.put("scId",SCId);
        return "backend/open_records.html";
    }

    @ResponseBody
    @GetMapping("/confirmopen/{orId}")
    public String comfirmOpen(@PathVariable("orId")Integer orId){
        OpenRecord openRecord = openRecordService.findById(orId);
        openRecord.setIs_open(1);
        openRecordService.save(openRecord);
        return null;
    }

}
