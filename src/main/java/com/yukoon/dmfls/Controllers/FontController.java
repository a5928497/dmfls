package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.*;
import com.yukoon.dmfls.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class FontController {
	@Autowired
	private SCServices scServices;
	@Autowired
	private DetailsService detailsService;
	@Autowired
	private INService inService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private OpenRecordService openRecordService;

	private final static String notice = "请填写正确的信息，以便多妈团队核实后返还相应的福利，若遇到问题请添加微信号dmzl9999与小助理取得联系，谢谢！";
	//进入主页
	@GetMapping("/index")
	public String index(Map<String,Object> map) {
		List<Securities> securities = scServices.findAllSC();
		List<ImportantNotice> importantNotices = inService.findAll();
		List<Details> details = detailsService.findAll();
		map.put("scs",securities);
		map.put("ins",importantNotices);
		map.put("details",details);
		return "front/index.html";
	}

//	进入开户信息登记页
	@GetMapping("/oprecord")
	public String opRecord(Map<String,Object>map) {
		List<Securities> securities = scServices.findAllSC();
		map.put("scs",securities);
		map.put("notice",notice);
		return "front/op_record.html";
	}

	//接受开户信息
	@ResponseBody
	@PostMapping("/oprecord")
	public String record(Client client, @RequestParam("securities") Integer[] securities) {
		Client old = clientService.findByEmailAndPhoneAndName(client.getEmail(),client.getPhone(),client.getName());
		List<OpenRecord> openRecords = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
		String message = "登记成功";
		String success = "";
		String exists = "";
		//客户是否第一次进行登记
		if (null == old) {
			client = clientService.save(client);
		}else {
			client = old;
		}
		for (Integer sc:securities){
			//查询记录是否存在，若不存在则添加
			if (!(openRecordService.is_existed(client.getId(),sc))){
				OpenRecord openRecord = new OpenRecord();
				Securities scs = new Securities();
				scs.setId(sc);
				openRecord.setIs_open(0).setClient(client).setSecurities(scs).setDate(date);
				openRecords.add(openRecord);
			}
		}
		openRecords = openRecordService.saveOpenRecords(openRecords);
//		return "redirect:/oprecord";
		return message;
	}
}
