package com.nxh.bus.mapper;

import com.nxh.bus.domain.Sell;

import java.util.List;

public interface SellMapper {
    int deleteByPrimaryKey(String sellid);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(String sellid);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell record);
    
    //查询
    public List<Sell> queryAllSell(Sell sell);
    
}