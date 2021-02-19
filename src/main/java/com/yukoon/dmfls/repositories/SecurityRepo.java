package com.yukoon.dmfls.repositories;

import com.yukoon.dmfls.Entities.Securities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepo extends JpaRepository<Securities,Integer> {

}
