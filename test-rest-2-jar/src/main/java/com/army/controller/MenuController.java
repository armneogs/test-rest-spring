package com.army.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.army.model.Menu;
import com.army.service.MenuService;
import com.army.service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	@Autowired
	private ObjectMapper jacksonObjectMapper;
	@Autowired
	private MenuService menuService;
	@RequestMapping(value = "/getall/", method = RequestMethod.GET)
	public String getTypeAll() {
		StringBuilder jsonStringB = new StringBuilder();
		List<Menu> menus = menuService.getAllMenu();
		try {
			jsonStringB.append(jacksonObjectMapper.writeValueAsString(menus));
		} catch (JsonProcessingException e) {
			jsonStringB.append(e);
			e.printStackTrace();
		}

		String jsonString = jsonStringB.toString();
//		String jsonString = mapper.writeValueAsString(value);
		return jsonString;
	}
}
