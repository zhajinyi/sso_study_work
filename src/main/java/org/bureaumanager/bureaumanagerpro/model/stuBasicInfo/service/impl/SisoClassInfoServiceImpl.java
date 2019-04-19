package org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.bureaumanager.bureaumanagerpro.base.BaseServiceImpl;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.dao.SisoClassInfoMapper;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.pojo.dto.SisoClassInfoDto;
import org.bureaumanager.bureaumanagerpro.model.stuBasicInfo.service.SisoClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SisoClassInfoServiceImpl extends BaseServiceImpl<SisoClassInfoDto, SisoClassInfoMapper> implements SisoClassInfoService {

    @Autowired
    SisoClassInfoMapper sisoClassInfoMapper;


    @Override
    public int countStudentByClass(String id) {
        return sisoClassInfoMapper.countStudentByClass(id);
    }



    @Override
    public SisoClassInfoDto selectClassInfoByUserId(String userId) {
        return sisoClassInfoMapper.selectClassInfoByUserId(userId);
    }


}
