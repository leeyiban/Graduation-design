package com.nxh.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理的路由控制器
 *
 *
 */
@Controller
@RequestMapping("bus")
public class BusController {

	/**
	 * 跳转到客户管理的页面
	 */
	@RequestMapping("toCustomerManager")
	public String toCustomerManager() {
		return "business/customer/customerManager";
	}
	
	/**
	 * 跳转到房屋管理的页面
	 */
	@RequestMapping("toHouseManager")
	public String toHouseManager() {
		return "business/house/houseManager";
	}

	/**
	 * 跳转到房屋出租的页面
	 */
	@RequestMapping("toRentHouseManager")
	public String toRentHouseManager() {
		return "business/rent/rentHouseManager";
	}

	/**
	 * 跳转到出租单管理的页面
	 */
	@RequestMapping("toRentManager")
	public String toRentManager() {
		return "business/rent/rentManager";
	}

	/**
	 * 跳转到房屋入库管理的页面
	 */
	@RequestMapping("toCheckHouseManager")
	public String toCheckHouseManager() {
		return "business/check/checkHouseManager";
	}

	/**
	 * 跳转到检查单管理的页面
	 */
	@RequestMapping("toCheckManager")
	public String toCheckManager() {
		return "business/check/checkManager";
	}

	/**
	 * 跳转到房屋出售的页面
	 */
	@RequestMapping("toSellManager")
	public String toSellManager() {
		return "business/sell/sellManager";
	}

}
