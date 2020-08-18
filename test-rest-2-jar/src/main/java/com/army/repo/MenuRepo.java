package com.army.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.army.model.Menu;

public interface MenuRepo extends Repository<Menu, Long> {
	@Query("select m_menu from Menu m_menu where m_menu.parentMenu = null order by m_menu.menuSeq")
	List<Menu> getAllFirstTierMenu();
	
	@Query("select c_menu from Menu c_menu where c_menu.parentMenu.menuId = ?1 order by c_menu.menuSeq")
	List<Menu> getChildMenuByParentMenuId(Long pMenuId);
}
