package com.nxh.bus.mapper;

import com.nxh.bus.domain.House;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String housenumber);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(String housenumber);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    
    List<House> queryAllHouse(House house);

}