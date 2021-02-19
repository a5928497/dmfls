package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.ImportantNotice;
import com.yukoon.dmfls.Entities.Securities;
import com.yukoon.dmfls.Services.SCServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class SCController {
	@Autowired
	private SCServices scServices;

	//获取券商对象
	@ModelAttribute
	public void  getSC(@RequestParam(value = "id",required = false)Integer id,Map<String,Object> map) {
		//若为修改
		if ( null != id) {
			Securities securities = scServices.findById(id);
			map.put("securities",securities);
		}
	}

	//查找所欲券商
	@GetMapping("/allSC")
	public String getAllSC(Map<String,Object>map) {
		map.put("SCs",scServices.findAllSC());
		return "backend/dashboard.html";
	}

	//前往增加券商
	@GetMapping("/sc")
	public String toAddSC(){
		return "backend/SC_input.html";
	}

	//新增券商
	@PostMapping("/sc")
	public String addSC(Securities securities) {
		securities = scServices.saveSC(securities);
		return "redirect:/allSC";
	}

	//前往编辑券商
	@GetMapping("/sc/{id}")
	public String toEditSC(@PathVariable("id")Integer id,Map<String,Object> map) {
		map.put("sc",scServices.findById(id));
		return "backend/SC_input.html";
	}

	//编辑券商
	@PutMapping("/sc")
	public String editSC(Securities securities) {
		securities = scServices.saveSC(securities);
		return "redirect:/allSC";
	}
}
