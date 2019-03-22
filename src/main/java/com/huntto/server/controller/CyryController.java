package com.huntto.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huntto.server.dao.CyryDao;
import com.huntto.server.service.CyryService;

@RestController
public class CyryController {
	
	@Autowired
	private CyryService cyryService;
	
	@Autowired
	private CyryDao cyryDao;
	
	@RequestMapping("test")
	public String test() {
		return cyryDao.isExist();
	}
	
	@RequestMapping("test1")
	public String test1(String xml) {
		return cyryService.register(xml);
	}
}
