package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.Entities.Details;
import com.yukoon.dmfls.repositories.DetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsService {
	@Autowired
	private DetailsRepo detailsRepo;

	//查找所有详情
	public List<Details> findAll(){
		return detailsRepo.findAll();
	}

	//查找所有展示的详情
	public List<Details> findAllIsshow(){
		return detailsRepo.findAllisShow(true);
	}

	//根据详情id寻找
	public Details findById(Integer id) {
		return detailsRepo.findOne(id);
	}

	//根据详情id寻找
	public List<Details> findByScId(Integer detailsId) {
		return detailsRepo.findAllByScId(detailsId);
	}

	//修改详情
	public Details save(Details details) {
		details = detailsRepo.save(details);
		return details;
	}

	//删除详情
	public void delById(Integer id) {
		detailsRepo.delete(id);
	}
}
