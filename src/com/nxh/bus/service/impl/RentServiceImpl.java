package com.nxh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nxh.bus.domain.House;
import com.nxh.bus.domain.Rent;
import com.nxh.bus.mapper.HouseMapper;
import com.nxh.bus.mapper.RentMapper;
import com.nxh.bus.service.RentService;
import com.nxh.bus.vo.RentVo;
import com.nxh.sys.constast.SysConstast;
import com.nxh.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {
	
	@Autowired
	private RentMapper rentMapper;
	
	@Autowired
	private HouseMapper houseMapper;

	@Override
	public void addRent(RentVo rentVo) {
		this.rentMapper.insertSelective(rentVo);
		//更改房屋的出租状态
		House house=new House();
		house.setHousenumber(rentVo.getHousenumber());
		house.setIsrenting(SysConstast.RENT_HOUSE_TRUE);
		houseMapper.updateByPrimaryKeySelective(house);
	}

	//更改房屋的买卖状态
	public void addSell(RentVo rentVo) {
		this.rentMapper.insertSelective(rentVo);
		House house=new House();
		house.setHousenumber(rentVo.getHousenumber());
		house.setIsrenting(SysConstast.SELL_HOUSE_FALSE);
		houseMapper.updateByPrimaryKeySelective(house);
	}
	@Override
	public DataGridView queryAllRent(RentVo rentVo) {
		Page<Object> page=PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
		List<Rent> data = this.rentMapper.queryAllRent(rentVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void updateRent(RentVo rentVo) {
		this.rentMapper.updateByPrimaryKeySelective(rentVo);
	}

	@Override
	public void deleteRent(String rentid) {
		//更改房屋的状态
		Rent rent=this.rentMapper.selectByPrimaryKey(rentid);
		House house=new House();
		house.setHousenumber(rent.getHousenumber());
		house.setIsrenting(SysConstast.RENT_HOUSE_FALSE);
		houseMapper.updateByPrimaryKeySelective(house);
		//删除出租单
		this.rentMapper.deleteByPrimaryKey(rentid);
	}

	@Override
	public Rent queryRentByRentId(String rentid) {
		return this.rentMapper.selectByPrimaryKey(rentid);
	}

}
