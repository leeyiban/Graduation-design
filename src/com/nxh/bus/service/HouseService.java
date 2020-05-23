package com.nxh.bus.service;

import com.nxh.bus.domain.House;
import com.nxh.bus.vo.HouseVo;
import com.nxh.sys.utils.DataGridView;

/**
 * 房屋管理的服务接口
 *
 *
 */
public interface HouseService {
	/**
	 * 查询所有房屋
	 * @param houseVo
	 * @return
	 */
	public DataGridView queryAllHouse(HouseVo houseVo);
	/**
	 * 添加房屋
	 * @param houseVo
	 */
	public void addHouse(HouseVo houseVo);
	/**
	 * 修改房屋
	 * @param houseVo
	 */
	public void updateHouse(HouseVo houseVo);
	/**
	 * 根据id删除房屋
	 * @param houseid
	 */
	public void deleteHouse(String housenumber);
	/**
	 * 批量删除房屋
	 * @param houseVo
	 */
	public void deleteBatchHouse(String[] housenumbers);

	/**
	 * 根据房屋编号查询
	 * @param housenumber
	 * @return
	 */
	public House queryHouseByHouseNumber(String housenumber);

}
