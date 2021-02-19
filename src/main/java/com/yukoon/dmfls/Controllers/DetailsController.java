package com.yukoon.dmfls.Controllers;

import com.yukoon.dmfls.Entities.Details;
import com.yukoon.dmfls.Entities.Securities;
import com.yukoon.dmfls.Services.DetailsService;
import com.yukoon.dmfls.Services.SCServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class DetailsController {
	@Autowired
	private SCServices scServices;
	@Autowired
	private DetailsService detailsService;

	//获取亮点对象
	@ModelAttribute
	public void  getDetails(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map) {
		//若为修改
		if ( null != id) {
			Details details = detailsService.findById(id);
			map.put("details",details);
		}
	}

	//查找券商下所有亮点
	@GetMapping("/allDetails/{sc_id}")
	public String findAllScDetails(@PathVariable("sc_id")Integer sc_id,Map<String,Object> map){
		map.put("details",detailsService.findByScId(sc_id));
		map.put("sc",scServices.findById(sc_id));
		return "backend/details.html";
	}

	//前往增加亮点
	@GetMapping("/adddetails/{sc_id}")
	public String toAddDetails(@PathVariable("sc_id")Integer sc_id, Map<String,Object> map){
		Securities securities = scServices.findById(sc_id);
		map.put("sc",securities);
		return "backend/details_input.html";
	}

	//新增亮点
	@PostMapping("/details")
	public String addDetails(Details details) {
		details = detailsService.save(details);
		Securities securities = details.getSecurities();
		System.out.println(securities);
		return "redirect:/allDetails/" + securities.getId();
	}

	//前往编辑亮点
	@GetMapping("/editdetails/{id}")
	public String toEditIN(@PathVariable("id")Integer id,Map<String,Object> map) {
		Details details = detailsService.findById(id);
		map.put("details",details);
		map.put("sc",details.getSecurities());
		return "backend/details_input.html";
	}

	//编辑亮点
	@PutMapping("/details")
	public String editDetails(Details details) {
		details = detailsService.save(details);
		return "redirect:/allDetails/" + details.getSecurities().getId();
	}

	//删除亮点
	@GetMapping("/deldetails/{id}")
	public String delIN(@PathVariable("id")Integer id) {
		Integer sc_id = detailsService.findById(id).getSecurities().getId();
		detailsService.delById(id);
		return "redirect:/allIn/" + sc_id;
	}
}
