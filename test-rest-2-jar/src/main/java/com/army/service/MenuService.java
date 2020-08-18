package com.army.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.army.model.Menu;
import com.army.repo.MenuRepo;

@Service
public class MenuService {
	@Autowired
	private MenuRepo menuRepo;
	public List<Menu> getAllMenu(){
		
		List<Menu> allMenu =  menuRepo.getAllFirstTierMenu();
		for(Menu menu : allMenu) {
			getChildMenus(menu);
		}
		
		return allMenu;
	}
	private Menu getChildMenus(Menu menu) {
		if(menu.getChildMenus().isEmpty()) {
			return menu;
		}
		List<Menu> childMenus = menuRepo.getChildMenuByParentMenuId(menu.getMenuId());

		for(Menu childMenu :childMenus) {
			getChildMenus(childMenu);
			childMenu.setParentMenu(null);
		}
		menu.setChildJSONMenus(childMenus);
		return menu;
	}
}
