package com.nxh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nxh.bus.domain.House;
import com.nxh.bus.domain.Sell;
import com.nxh.bus.mapper.HouseMapper;
import com.nxh.bus.mapper.SellMapper;
import com.nxh.bus.service.SellService;
import com.nxh.bus.vo.SellVo;
import com.nxh.sys.constast.SysConstast;
import com.nxh.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellServiceImpl implements SellService {
	
	@Autowired
	private SellMapper sellMapper;
	
	@Autowired
	private HouseMapper houseMapper;

	@Override
	public void addSell(SellVo sellVo) {
		this.sellMapper.insertSelective(sellVo);
		//更改房屋的出租状态
		House house=new House();
		house.setHousenumber(sellVo.getHousenumber());
		house.setIssell(SysConstast.SELL_HOUSE_TRUE);
		houseMapper.updateByPrimaryKeySelective(house);
	}


	@Override
	public DataGridView queryAllSell(SellVo sellVo) {
		Page<Object> page=PageHelper.startPage(sellVo.getPage(), sellVo.getLimit());
		List<Sell> data = this.sellMapper.queryAllSell(sellVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void updateSell(SellVo sellVo) {
		this.sellMapper.updateByPrimaryKeySelective(sellVo);
	}

	@Override
	public void deleteSell(String sellid) {
		//更改房屋的状态
		Sell sell=this.sellMapper.selectByPrimaryKey(sellid);
		House house=new House();
		house.setHousenumber(sell.getHousenumber());
		house.setIssell(SysConstast.SELL_HOUSE_FALSE);
		houseMapper.updateByPrimaryKeySelective(house);
		//删除出租单
		this.sellMapper.deleteByPrimaryKey(sellid);
	}

	@Override
	public Sell querySellBySellId(String sellid) {
		return this.sellMapper.selectByPrimaryKey(sellid);
	}

}
