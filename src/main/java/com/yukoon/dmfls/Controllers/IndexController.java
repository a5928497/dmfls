package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.Details;
import com.yukoon.dmfls.Entities.ImportantNotice;
import com.yukoon.dmfls.Entities.Securities;
import com.yukoon.dmfls.Services.DetailsService;
import com.yukoon.dmfls.Services.INService;
import com.yukoon.dmfls.Services.SCServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
	@Autowired
	private SCServices scServices;
	@Autowired
	private DetailsService detailsService;
	@Autowired
	private INService inService;

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
}
