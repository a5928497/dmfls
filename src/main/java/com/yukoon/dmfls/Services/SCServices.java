package com.yukoon.dmfls.Services;

import com.yukoon.dmfls.Entities.Securities;
import com.yukoon.dmfls.repositories.SecurityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SCServices {
    @Autowired
    private SecurityRepo securityRepo;

    //查找所有券商并按照显示顺序排序
    public List<Securities> findAllSC() {
        List<Securities> securities = securityRepo.findAll();
        Collections.sort(securities);
        return securities;
    }

    //查找所有显示的券商并排序
    public List<Securities> findAllShowSC() {
        List<Securities> securities = securityRepo.findAllisShow(true);
        Collections.sort(securities);
        return securities;
    }

    //按照ID查找券商
    public Securities findById(Integer id) {
        return securityRepo.findOne(id);
    }

    //增加&修改券商，顺带重排展示序列
    public Securities saveSC(Securities securities) {
        if (securities.getOpBtnText() == "") {
            securities.setOpBtnText(null);
        }
        securities = scOrderResort(securities);
        return securityRepo.save(securities);
    }

    //删除券商
    public void delSC(Securities securities) {
        securityRepo.delete(securities);
    }

    //展示顺序重排列
    public Securities scOrderResort(Securities securities) {
        List<Securities> old = findAllSC();
        Collections.sort(old);
        Integer order = securities.getOrder();
        //若sc没有排序则自动排列在末端
        if (null == order) {
            securities.setOrder(old.size() + 1);
        }
        //若带有序号则重新排列
        else {
            int count = order;
            boolean flag = true;
            for (Securities sc : old) {
                //只变更相关的顺序
                if (sc.getOrder() >= order && (sc.getOrder() == order || sc.getOrder() == count) && sc != securities) {
                    sc.setOrder(sc.getOrder() + 1);
                    count++;
                }
            }
            System.out.println(order);

        }
        return securities;
    }

}
