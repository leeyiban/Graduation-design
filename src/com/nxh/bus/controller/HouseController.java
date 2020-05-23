package com.nxh.bus.controller;

import com.nxh.bus.domain.House;
import com.nxh.bus.service.HouseService;
import com.nxh.bus.vo.HouseVo;
import com.nxh.sys.constast.SysConstast;
import com.nxh.sys.utils.AppFileUtils;
import com.nxh.sys.utils.DataGridView;
import com.nxh.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * 房屋管理控制器
 * 
 *
 *
 */
@RestController
@RequestMapping("house")
public class HouseController {

	@Autowired
	private HouseService houseService;

	/**
	 * 加载房屋列表返回DataGridView
	 */
	@RequestMapping("loadAllHouse")
	public DataGridView loadAllHouse(HouseVo houseVo) {
		return this.houseService.queryAllHouse(houseVo);
	}

	/**
	 * 添加房屋
	 */
	@RequestMapping("addHouse")
	public ResultObj addHouse(HouseVo houseVo) {
		try {
			houseVo.setCreatetime(new Date());
			//如果不是默认图片就去掉图片的_temp的后缀
			if(!houseVo.getHouseimg().equals(SysConstast.DEFAULT_HOUSE_IMG)) {
				String filePath=AppFileUtils.updateFileName(houseVo.getHouseimg(),SysConstast.FILE_UPLOAD_TEMP);
				houseVo.setHouseimg(filePath);
			}
			this.houseService.addHouse(houseVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改房屋
	 */
	@RequestMapping("updateHouse")
	public ResultObj updateHouse(HouseVo houseVo) {
		try {
			String houseimg = houseVo.getHouseimg();
			if(houseimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
				String filePath=AppFileUtils.updateFileName(houseVo.getHouseimg(),SysConstast.FILE_UPLOAD_TEMP);
				houseVo.setHouseimg(filePath);
				//把原来的删除
				House house=this.houseService.queryHouseByHouseNumber(houseVo.getHousenumber());
				AppFileUtils.removeFileByPath(house.getHouseimg());
			}

			this.houseService.updateHouse(houseVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除房屋
	 */
	@RequestMapping("deleteHouse")
	public ResultObj deleteHouse(HouseVo houseVo) {
		try {
			this.houseService.deleteHouse(houseVo.getHousenumber());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除房屋
	 */
	@RequestMapping("deleteBatchHouse")
	public ResultObj deleteBatchHouse(HouseVo houseVo) {
		try {
			this.houseService.deleteBatchHouse(houseVo.getIds());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}
