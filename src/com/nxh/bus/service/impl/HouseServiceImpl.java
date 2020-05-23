package com.nxh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nxh.bus.domain.House;
import com.nxh.bus.mapper.HouseMapper;
import com.nxh.bus.service.HouseService;
import com.nxh.bus.vo.HouseVo;
import com.nxh.sys.constast.SysConstast;
import com.nxh.sys.utils.AppFileUtils;
import com.nxh.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private HouseMapper houseMapper;

	@Override
	public DataGridView queryAllHouse(HouseVo houseVo) {
		Page<Object> page=PageHelper.startPage(houseVo.getPage(), houseVo.getLimit());
		List<House> data = this.houseMapper.queryAllHouse(houseVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addHouse(HouseVo houseVo) {
		this.houseMapper.insertSelective(houseVo);
	}

	@Override
	public void deleteHouse(String housenumber) {
		//先删除图片
		House house=this.houseMapper.selectByPrimaryKey(housenumber);
		//如果不是默认图片就删除
		if(!house.getHouseimg().equals(SysConstast.DEFAULT_HOUSE_IMG)) {
			AppFileUtils.deleteFileUsePath(house.getHouseimg());
		}
		//删除数据库的数据
		this.houseMapper.deleteByPrimaryKey(housenumber);


	}

	@Override
	public void deleteBatchHouse(String[] housenumbers) {
		for (String housenumber : housenumbers) {
			this.deleteHouse(housenumber);
		}
	}

	@Override
	public void updateHouse(HouseVo houseVo) {
		this.houseMapper.updateByPrimaryKeySelective(houseVo);
	}

	@Override
	public House queryHouseByHouseNumber(String housenumber) {
		return this.houseMapper.selectByPrimaryKey(housenumber);
	}


}
