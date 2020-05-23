package com.nxh.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nxh.sys.domain.LogInfo;
import com.nxh.sys.mapper.LogInfoMapper;
import com.nxh.sys.service.LogInfoService;
import com.nxh.sys.utils.DataGridView;
import com.nxh.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {
	
	@Autowired
	private LogInfoMapper logInfoMapper;

	@Override
	public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
		Page<Object> page=PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
		List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addLogInfo(LogInfoVo logInfoVo) {
		this.logInfoMapper.insertSelective(logInfoVo);
	}

	@Override
	public void deleteLogInfo(Integer logInfoid) {
		this.logInfoMapper.deleteByPrimaryKey(logInfoid);
	}

	@Override
	public void deleteBatchLogInfo(Integer[] ids) {
		for (Integer integer : ids) {
			this.deleteLogInfo(integer);
		}
	}

}
