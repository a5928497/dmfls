package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.ImportantNotice;
import com.yukoon.dmfls.Entities.Securities;
import com.yukoon.dmfls.Services.INService;
import com.yukoon.dmfls.Services.SCServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class IN_Controller {
	@Autowired
	private INService in_service;
	@Autowired
	private SCServices scServices;

	//获取亮点对象
	@ModelAttribute
	public void  getIN(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
		//若为修改
		if ( null != id) {
			ImportantNotice importantNotice = in_service.findById(id);
			map.put("in",importantNotice);
		}
	}

	//查找券商下所有亮点
	@GetMapping("/allIn/{sc_id}")
	public String findAllScIn(@PathVariable("sc_id")Integer sc_id,Map<String,Object> map){
		map.put("ins",in_service.findByScId(sc_id));
		map.put("sc",scServices.findById(sc_id));
		return "backend/INs.html";
	}

	//前往增加亮点
	@GetMapping("/addin/{sc_id}")
	public String toAddIN(@PathVariable("sc_id")Integer sc_id,Map<String,Object> map){
		Securities securities = scServices.findById(sc_id);
		map.put("sc",securities);
		return "backend/IN_input.html";
	}

	//新增亮点
	@PostMapping("/in")
	public String addSC(ImportantNotice importantNotice) {
		importantNotice.setIs_show(1);
		importantNotice = in_service.save(importantNotice);
		Securities securities = importantNotice.getSecurities();
		return "redirect:/allIn/" + securities.getId();
	}

	//前往编辑亮点
	@GetMapping("/editin/{id}")
	public String toEditIN(@PathVariable("id")Integer id,Map<String,Object> map) {
		ImportantNotice importantNotice = in_service.findById(id);
		map.put("in",importantNotice);
		map.put("sc",importantNotice.getSecurities());
		return "backend/IN_input.html";
	}

	//编辑亮点
	@PutMapping("/in")
	public String editIN(ImportantNotice importantNotice) {
		importantNotice = in_service.save(importantNotice);
		return "redirect:/allIn/" + importantNotice.getSecurities().getId();
	}

	//删除亮点
	@GetMapping("/delin/{id}")
	public String delIN(@PathVariable("id")Integer id) {
		Integer sc_id = in_service.findById(id).getSecurities().getId();
		in_service.delById(id);
		return "redirect:/allIn/" + sc_id;
	}
}
