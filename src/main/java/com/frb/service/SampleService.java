package com.frb.service;

import com.frb.DAO.TrainSample;
import com.frb.mapper.SampleMapper;
import com.frb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SampleService {
    @Autowired
    SampleMapper sampleMapper;
    public ArrayList<TrainSample> getInfo() {
        return sampleMapper.getInfo();
    }

    public void addSample(TrainSample t) {
        sampleMapper.addSample(t);
    }

    public Object getSingleInfo(String id) {
        TrainSample t = sampleMapper.getSingleInfo(id);
        String path = t.gettPath();
        String args = t.gettAttr();
        HashMap<String, String> argsMap = StringUtils.toMap(args);
        argsMap.put("model_path", path);
        return argsMap;
    }
}
