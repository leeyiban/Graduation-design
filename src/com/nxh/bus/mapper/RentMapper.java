package com.nxh.bus.mapper;

import com.nxh.bus.domain.Rent;

import java.util.List;

public interface RentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);
    
    //查询
    public List<Rent> queryAllRent(Rent rent);
    
}