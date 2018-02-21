package com.xjx.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import java.util.*;
import java.util.Collections;
public class CollectionUtil {
	
	
	public static <T> T[] listToArray(List<T> src, Class<T> type) {
		if (src == null || src.isEmpty()) {
			return null;
		}
		T[] dest = (T[]) Array.newInstance(type, src.size());
		for (int i = 0; i < src.size(); i++) {
			dest[i] = src.get(i);
		}
		return (T[]) dest;
	}
	
	public static <T> T[][] listsToArrays(List<List<T>> src, Class<T> type) {
		if (src == null || src.isEmpty()) {
			return null;
		}

		T[][] dest = dest = (T[][]) Array.newInstance(type, src.size(), src.get(0).size());

		for (int i = 0; i < src.size(); i++) {
			for (int j = 0; j < src.get(i).size(); j++) {
				dest[i][j] = src.get(i).get(j);
			}
		}

		return dest;
	}
	
	/**
	 * 
	 * @param origin   按照树的深度从低到高排好序
	 * @param childString  记录id
	 * @param parentString  父记录Id
	 * @param connectString  存放子记录的key
	 * @param rootString  根节点标志
	 * @return
	 */
	public static List<Map<String, Object>> listToTree(List<Map<String, Object>> origin,String childString,String parentString,String connectString,String rootString) {
		Collections.reverse(origin);
		//key  是 parentld, value  是 menu 数组
		Map<String, List<Map<String, Object>>> parentIdAndListBean = new HashMap<String, List<Map<String, Object>>>();
		// 可以返回 多个根节点，然后 Map<String, Object >是嵌套的结构
		List<Map<String, Object>> rootList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> resmap : origin) {
			if (parentIdAndListBean.containsKey(resmap.get(childString))){// 当前 ID 为原记录父 ID ，则将所有记录放入当前 menus 里面
				resmap.put(connectString, parentIdAndListBean.get(resmap.get(childString).toString()));
			}else
			resmap.put(connectString, null);
			
			//是根节点条件  
			if (resmap.get(parentString) == null || resmap.get(parentString).toString().equals("")||resmap.get(parentString).toString().equals(rootString)){
				parentIdAndListBean.put(resmap.get(childString).toString(),  parentIdAndListBean.get(resmap.get(childString).toString()));
				rootList.add(resmap);
			}else {
			if (parentIdAndListBean.containsKey(resmap.get(parentString).toString())){
				if(parentIdAndListBean.get(resmap.get(parentString))==null){
					List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
					tempList.add(resmap);
					parentIdAndListBean.put(resmap.get(parentString).toString(), tempList);
				}else{
					List<Map<String, Object>> tempList2 =parentIdAndListBean.get(resmap.get(parentString));
					tempList2.add(resmap);
					parentIdAndListBean.put(resmap.get(parentString).toString(), tempList2);
				}
			}
			else {
				if(!parentIdAndListBean.containsKey(resmap.get(parentString).toString())){
					List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
					list2.add(resmap);
					parentIdAndListBean.put(resmap.get(parentString).toString(), list2);
				}
			}
			}
		}
		return rootList;
	}
	

}
