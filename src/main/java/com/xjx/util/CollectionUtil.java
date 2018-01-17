package com.xjx.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
	
	
	

}
