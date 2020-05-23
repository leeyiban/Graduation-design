package com.nxh.bus.controller;

import com.nxh.bus.domain.Customer;
import com.nxh.bus.service.CustomerService;
import com.nxh.bus.service.SellService;
import com.nxh.bus.vo.SellVo;
import com.nxh.sys.constast.SysConstast;
import com.nxh.sys.domain.User;
import com.nxh.sys.utils.DataGridView;
import com.nxh.sys.utils.RandomUtils;
import com.nxh.sys.utils.ResultObj;
import com.nxh.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 房屋出租管理的控制器
 *
 *
 */
@RestController
@RequestMapping("sell")
public class SellController {

	@Autowired
	private SellService sellService;
	
	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 检查身份证号是否存在
	 */
	@RequestMapping("checkCustomerExist")
	public ResultObj checkCustomerExist(SellVo sellVo) {
		Customer customer=customerService.queryCustomerByIdentity(sellVo.getIdentity());
		if(null!=customer) {
			return ResultObj.STATUS_TRUE;
		}else {
			return ResultObj.STATUS_FALSE;
		}
	}
	
	/**
	 * 初始化添加出租单的表单数据
	 */
	@RequestMapping("initSellFrom")
	public SellVo initSellFrom(SellVo sellVo) {
		//生成出租单号
		sellVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.HOUSE_ORDER_CZ));
		//设置起租时间
		sellVo.setBegindate(new Date());
		
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		//设置操作员
		sellVo.setOpername(user.getRealname());
		return sellVo;
	}
	
	/**
	 * 保存出租单信息
	 */
	@RequestMapping("saveSell")
	public ResultObj saveSell(SellVo sellVo) {
		try {
			//设置创建时间
			sellVo.setCreatetime(new Date());
			//设置归还状态
			sellVo.setSellflag(SysConstast.RENT_BACK_FALSE);
			
			//保存
			this.sellService.addSell(sellVo);
			
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	
	/***************出租单管理*****************/
	
	/**
	 * 查询
	 */
	@RequestMapping("loadAllSell")
	public DataGridView loadAllSell(SellVo sellVo) {
		return this.sellService.queryAllSell(sellVo);
	}
	
	
	/**
	 * 修改出租单信息
	 */
	@RequestMapping("updateSell")
	public ResultObj updateSell(SellVo sellVo) {
		try {
			//保存
			this.sellService.updateSell(sellVo);
			
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	

	/**
	 * 删除租单信息
	 */
	@RequestMapping("deleteSell")
	public ResultObj deleteSell(SellVo sellVo) {
		try {
			this.sellService.deleteSell(sellVo.getRentid());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	
	
	
}
