package com.frb.mapper;

import com.frb.DAO.TrainSample;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SampleMapper {
    ArrayList<TrainSample> getInfo();

    void addSample(TrainSample t);

    TrainSample getSingleInfo(String id);
}
