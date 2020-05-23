package com.nxh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nxh.bus.domain.House;
import com.nxh.bus.domain.Check;
import com.nxh.bus.domain.Customer;
import com.nxh.bus.domain.Rent;
import com.nxh.bus.mapper.HouseMapper;
import com.nxh.bus.mapper.CheckMapper;
import com.nxh.bus.mapper.CustomerMapper;
import com.nxh.bus.mapper.RentMapper;
import com.nxh.bus.service.CheckService;
import com.nxh.bus.vo.CheckVo;
import com.nxh.sys.constast.SysConstast;
import com.nxh.sys.domain.User;
import com.nxh.sys.utils.DataGridView;
import com.nxh.sys.utils.RandomUtils;
import com.nxh.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {

	@Autowired
	private CheckMapper checkMapper;
	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private RentMapper rentMapper;
	@Autowired
	private HouseMapper houseMapper;
	@Override
	public Map<String, Object> initCheckFormData(String rentid) {
		
		//查询出租单
		Rent rent=this.rentMapper.selectByPrimaryKey(rentid);
		//查询客户
		Customer customer=this.customerMapper.selectByPrimaryKey(rent.getIdentity());
		//查询房屋
		House house=this.houseMapper.selectByPrimaryKey(rent.getHousenumber());
		//组装Check
		Check check=new Check();
		check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.HOUSE_ORDER_JC));
		check.setRentid(rentid);
		check.setCheckdate(new Date());
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		check.setOpername(user.getRealname());
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("rent", rent);
		map.put("customer", customer);
		map.put("house", house);
		map.put("check", check);
		
		return map;
	}
	@Override
	public void addCheck(CheckVo checkVo) {
		this.checkMapper.insertSelective(checkVo);
		//更改出租单的状态
		Rent rent=this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
		rent.setRentflag(SysConstast.RENT_BACK_TRUE);
		this.rentMapper.updateByPrimaryKeySelective(rent);
		//更改房屋的状态
		House house=new House();
		house.setHousenumber(rent.getHousenumber());
		house.setIsrenting(SysConstast.RENT_HOUSE_FALSE);
		this.houseMapper.updateByPrimaryKeySelective(house);
	}
	@Override
	public DataGridView queryAllCheck(CheckVo checkVo) {
		Page<Object> page=PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
		List<Check> data = this.checkMapper.queryAllCheck(checkVo);
		return new DataGridView(page.getTotal(), data);
	}
	@Override
	public void updateCheck(CheckVo checkVo) {
		this.checkMapper.updateByPrimaryKeySelective(checkVo);
	}

}
