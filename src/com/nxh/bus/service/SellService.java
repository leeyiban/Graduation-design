package com.nxh.bus.service;

import com.nxh.bus.domain.Sell;
import com.nxh.bus.vo.SellVo;
import com.nxh.sys.utils.DataGridView;

public interface SellService {

	/**
	 * 保存出租单信息
	 * @param sellVo
	 */
	void addSell(SellVo sellVo);

	/**
	 * 查询
	 * @param sellVo
	 */
	DataGridView queryAllSell(SellVo sellVo);

	/**
	 * 修改出租单
	 * @param sellVo
	 */
	void updateSell(SellVo sellVo);

	/**
	 * 删除出租单
	 * @param sellid
	 */
	void deleteSell(String sellid);

	/**
	 *  根据出租单号查询出租单信息
	 * @param sellid
	 * @return
	 */
	Sell querySellBySellId(String sellid);

}
