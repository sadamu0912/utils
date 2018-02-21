package com.xjx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CollectionUtilTest {

	@Test
	public void testListToTree() {
		Map<String,Object> menu1 = new HashMap<String,Object>();
		menu1.put("menuId","1");
		menu1.put("menuParentId", "0");
		
		Map<String,Object> menu2 = new HashMap<String,Object>();
		menu2.put("menuId","11");
		menu2.put("menuParentId", "1");
		
		Map<String,Object> menu3 = new HashMap<String,Object>();
		menu3.put("menuId","111");
		menu3.put("menuParentId", "11");
		
		Map<String,Object> menu4 = new HashMap<String,Object>();
		menu4.put("menuId","2");
		menu4.put("menuParentId", "0");
		
		Map<String,Object> menu5 = new HashMap<String,Object>();
		menu5.put("menuId","21");
		menu5.put("menuParentId", "2");
		Map<String,Object> menu7 = new HashMap<String,Object>();
		menu7.put("menuId","22");
		menu7.put("menuParentId", "2");
		
		Map<String,Object> menu6 = new HashMap<String,Object>();
		menu6.put("menuId","211");
		menu6.put("menuParentId", "21");
		
		Map<String,Object> menu8 = new HashMap<String,Object>();
		menu8.put("menuId","212");
		menu8.put("menuParentId", "21");
		Map<String,Object> menu9 = new HashMap<String,Object>();
		menu9.put("menuId","213");
		menu9.put("menuParentId", "21");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(menu1);
		list.add(menu2);
		list.add(menu3);
		list.add(menu4);
		list.add(menu5);
		list.add(menu6);
		list.add(menu7);
		list.add(menu8);
		list.add(menu9);
		CollectionUtil.listToTree(list, "menuId", "menuParentId", "menus","0");
	}
	
}
