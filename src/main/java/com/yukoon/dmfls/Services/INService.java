package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.Entities.ImportantNotice;
import com.yukoon.dmfls.repositories.IN_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INService {
	@Autowired
	private IN_Repo in_repo;

	//查找所有亮点
	public List<ImportantNotice> findAll() {
		return in_repo.findAll();
	}

	//根据亮点id寻找
	public ImportantNotice findById(Integer id) {
		return in_repo.findOne(id);
	}

	//根据券商id寻找
	public List<ImportantNotice> findByScId(Integer scId) {
		return in_repo.findAllByScId(scId);
	}

	//修改亮点
	public ImportantNotice save(ImportantNotice importantNotice) {
		importantNotice = in_repo.save(importantNotice);
		return importantNotice;
	}

	//删除亮点
	public void delById(Integer id) {
		in_repo.delete(id);
	}
}
