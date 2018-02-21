package com.xjx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("restriction")
public class Common {
	private static final int GB_SP_DIFF = 160;

	private static final int[] secPosvalueList = { 1601, 1637, 1833, 2078,
			2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730,
			3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

	private static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'w', 'x', 'y', 'z' };

	public static String getFirstLetter(String oriStr) {
		if (isEmpty(oriStr))
			return "";

		String str = oriStr.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) { // ���δ���str��ÿ���ַ�
			ch = str.charAt(i);
			temp = new char[] { ch };
			byte[] uniCode = new String(temp).getBytes();
			if (uniCode[0] < 128 && uniCode[0] > 0) { // �Ǻ���
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString();
	}

	private static char convert(byte[] bytes) {
		char result = '-';
		int secPosvalue = 0;
		int i;
		for (i = 0; i < bytes.length; i++) {
			bytes[i] -= GB_SP_DIFF;
		}
		secPosvalue = bytes[0] * 100 + bytes[1];
		for (i = 0; i < 23; i++) {
			if (secPosvalue >= secPosvalueList[i]
					&& secPosvalue < secPosvalueList[i + 1]) {
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}

	public static String getPropertyByKey(String key) {
		try {
			Properties p = new Properties();
			InputStream in = Common.class
					.getResourceAsStream("../../../prop.properties");
			p.load(in);
			return p.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * �������з���,���紲�Ŵ�С���󣬼Ӵ����������
	 * 
	 * @param strA
	 * @param strB
	 * @return
	 */
	public static int compareNum(String strA, String strB) {
		int numA, numB;
		try {
			strA = strA.trim();
			numA = Integer.parseInt(strA);
		} catch (Exception e) {
			int specialNum = getNonNumStr(strA).hashCode() / 10000000 * 100000;
			numA = 100000000 + specialNum + getNumberFromString(strA);
		}

		try {
			strB = strB.trim();
			numB = Integer.parseInt(strB);
		} catch (Exception e) {
			int specialNum = getNonNumStr(strA).hashCode() / 10000000 * 100000;
			numB = 100000000 + specialNum + getNumberFromString(strB);
		}
		return numA - numB;
	}

	public static int getNumberFromString(String str) {
		if (str == null)
			return -1;
		String numberList = "0123456789";
		int num = 0;
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				if (numberList.indexOf(str.substring(i, i + 1)) >= 0)
					num = num * 10 + Integer.parseInt(str.substring(i, i + 1));
			}
		}
		return num;
	}

	public static String getNonNumStr(String str) {
		if (str == null)
			return "";
		String numberList = "0123456789";
		StringBuilder builder = new StringBuilder();
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				if (numberList.indexOf(str.substring(i, i + 1)) < 0)
					builder.append(str.substring(i, i + 1));
			}
		}
		return builder.toString();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		if (list != null && !list.isEmpty())
			return false;
		return true;
	}

	public static boolean isEmpty(String str) {
		if (str != null && !"".equals(str) && str.trim().length() > 0)
			return false;
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Set list) {
		if (list != null && !list.isEmpty())
			return false;
		return true;
	}

	public static boolean isNumber(String str) {
		if (!isEmpty(str)) {
			char[] cArr = str.toCharArray();
			String pp = "";
			for (int j = 0; j < cArr.length; j++) {
				if (cArr[j] >= 48 && cArr[j] <= 57)
					pp += cArr[j];
			}
			if (pp.length() > 0 && pp.length() == cArr.length)
				return true;
		}
		return false;
	}

	public static String getEnv(String name) {
		StringBuffer sb = new StringBuffer();
		try {
			Process p = Runtime.getRuntime().exec(
					"cmd /c echo " + "%" + name + "%");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				sb.append(strLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static Map<String, String> getEnv() {
		Map<String, String> map = new HashMap<String, String>();
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("cmd /c set");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				String[] str = line.split("=");
				map.put(str[0], str[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static String checkNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	public static String checkNull(Object obj) {
		String str = "";
		if (obj != null) {
			str = obj.toString();
		}
		return str;
	}

	public static Integer[] convertArray(int[] intArray) {
		if (intArray == null)
			return null;
		Integer[] array = new Integer[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			array[i] = intArray[i];
		}
		return array;
	}

	public static Long[] convertArray(long[] intArray) {
		if (intArray == null)
			return null;
		Long[] array = new Long[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			array[i] = intArray[i];
		}
		return array;

	}



	
	public static Timestamp getTimestamp(Date date) {
		if (date != null) {
			return new Timestamp(date.getTime());
		}
		return null;
	}

	public static <T extends Enum<T>> T enumValue(Class<T> enumClass,
			String value) {
		T returnValue = null;
		if (enumClass != null && value != null) {
			try {
				if (Enum.valueOf(enumClass, value) != null) {
					returnValue = Enum.valueOf(enumClass, value);
				}
			} catch (IllegalArgumentException e) {
				returnValue = null;
			}
		}
		return returnValue;
	}
	
	public static <T extends Enum<T>> String[] enumArrayToStringArray(T[] enums){
		List<String> list = new ArrayList<String>();
		if(enums!=null){
			for(T enumObject:enums){
				list.add(enumObject.toString());
			}
		}
		return list.toArray(new String[list.size()]);
	}

	

	
	public static String arrayJoin(String[] array, String separator,
			String wrapper) {
		String arrayString = "";
		if (null == wrapper) {
			wrapper = "";
		}
		if (array == null) {
			arrayString = "";
		} else if (array.length == 1) {
			arrayString = wrapper + array[0] + wrapper;
		} else if (array.length > 1) {
			for (int i = 0; i < array.length - 1; i++) {
				arrayString += wrapper + array[i] + wrapper + separator;
			}
			arrayString += wrapper + array[array.length - 1] + wrapper;
		}
		return arrayString;
	}

	public static String arrayJoin(int[] array, String separator, String wrapper) {
		String arrayString = "";
		if (null == wrapper) {
			wrapper = "";
		}
		if (array == null) {
			arrayString = "";
		} else if (array.length == 1) {
			arrayString = wrapper + array[0] + wrapper;
		} else if (array.length > 1) {
			for (int i = 0; i < array.length - 1; i++) {
				arrayString += wrapper + array[i] + wrapper + separator;
			}
			arrayString += wrapper + array[array.length - 1] + wrapper;
		}
		return arrayString;
	}

	public static String arrayJoin(long[] array, String separator,
			String wrapper) {
		String arrayString = "";
		if (null == wrapper) {
			wrapper = "";
		}
		if (array == null) {
			arrayString = "";
		} else if (array.length == 1) {
			arrayString = wrapper + array[0] + wrapper;
		} else if (array.length > 1) {
			for (int i = 0; i < array.length - 1; i++) {
				arrayString += wrapper + array[i] + wrapper + separator;
			}
			arrayString += wrapper + array[array.length - 1] + wrapper;
		}
		return arrayString;
	}

	public static String arrayJoin(Object[] array, String separator,
			String wrapper) {
		String arrayString = "";
		if (null == wrapper) {
			wrapper = "";
		}
		if (array == null) {
			arrayString = "";
		} else if (array.length == 1) {
			arrayString = wrapper + array[0] + wrapper;
		} else if (array.length > 1) {
			for (int i = 0; i < array.length - 1; i++) {
				arrayString += wrapper + array[i] + wrapper + separator;
			}
			arrayString += wrapper + array[array.length - 1] + wrapper;
		}
		return arrayString;
	}

	
	
	public static String listJoinInteger(List<Integer> list, String separator,
			String wrapper) {
		Integer[] array = list.toArray(new Integer[list.size()]);
		String arrayString = "";
		if (wrapper == null) {
			wrapper = "";
		}
		if (array == null) {
			arrayString = "";
		} else if (array.length == 1) {
			arrayString = wrapper + array[0].intValue() + wrapper;
		} else if (array.length > 1) {
			for (int i = 0; i < array.length - 1; i++) {
				arrayString += wrapper + array[i].intValue() + wrapper
						+ separator;
			}
			arrayString += wrapper + array[array.length - 1].intValue()
					+ wrapper;
		}
		return arrayString;
	}

	

	public static boolean isInArray(Object element, Object[] array) {
		boolean is = false;
		for (Object value : array) {
			if (element.equals(value)) {
				is = true;
				break;
			}
		}
		return is;
	}

	public static boolean isInArray(int element, int[] array) {
		boolean is = false;
		for (int value : array) {
			if (element == value) {
				is = true;
				break;
			}
		}
		return is;
	}

	public static Map<Integer, Integer> arrayToMap(int[] array) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			map.put(array[i], i);
		}
		return map;
	}


	private static String convertCharSetFromAscToGBK(String virgin,
			boolean isAscii) {
		if (!isAscii)
			return Common.isEmpty(virgin) ? "" : virgin.trim();
		try {
			if (virgin == null)
				return "";
			return new String(virgin.getBytes("ISO8859-1"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * д��ascii������ݿ�ʱת��
	 * 
	 * @param str
	 * @return
	 */
	private static String changeCharset(String str, boolean isAscii) {
		if (!isAscii)
			return Common.isEmpty(str) ? "" : str;
		try {
			if (str != null) {
				// ��Ĭ���ַ���������ַ�������ϵͳ��أ�����windowsĬ��ΪGB2312
				byte[] bs = str.getBytes();
				return new String(bs, "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String GetMD2EncString(String plantText) {
		return GetEncString(plantText, "MD2");
	}

	public static String GetMD5EncString(String plantText) {
		return GetEncString(plantText, "MD5");
	}

	public static String GetSHA1EncString(String plantText) {
		return GetEncString(plantText, "SHA-1");
	}

	
	public static String GetSHA256EncString(String plantText) {
		return GetEncString(plantText, "SHA-256");
	}

	
	public static String GetSHA384EncString(String plantText) {
		return GetEncString(plantText, "SHA-384");
	}

	
	public static String GetSHA512EncString(String plantText) {
		return GetEncString(plantText, "SHA-512");
	}

	
	private static String GetEncString(String plainText, String algorithm) {
		plainText = isEmpty(plainText) ? "" : plainText;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(plainText.getBytes());
			byte[] b = md.digest();
			StringBuilder output = new StringBuilder(32);
			for (int i = 0; i < b.length; i++) {
				String temp = Integer.toHexString(b[i] & 0xff);
				if (temp.length() < 2) {
					output.append("0");
				}
				output.append(temp);
			}
			return output.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	
	
	
	

	public static String[] replaceAll(String[] arr, String regex,
			String replacement) {
		if (arr == null || arr.length == 0)
			return null;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].replaceAll(regex, replacement);
		}
		return arr;
	}

	public static boolean isChineseAll(String str) {
		String regEx = "[^\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return false;
		}
		return true;
	}

	public static boolean isCharacter(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z]*");
		Matcher m = pattern.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isCharacterAndNumber(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher m = pattern.matcher(str);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.find()) {
			return true;
		}
		return false;
	}

	
	public static String removeNumber(String str) {
		String v = "";
		if (str == null)
			return "";
		v = str.replaceAll("[0-9]", "").trim();
		if (v.trim().equals(""))
			v = "";
		return v;
	}

	
	public static boolean isNumericAll(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	
	public static String removeNonNumber(String str) {
		String v = "";
		if (str == null)
			return "";
		v = str.replaceAll("[^0-9]", "").trim();
		if (v.trim().equals(""))
			v = "0";
		return v;
	}

	
	public static String replaceNonNumber(String str, String substitution) {
		String v = "";
		if (str == null)
			return "";
		v = str.replaceAll("[^0-9]", substitution).trim();
		if (v.trim().equals(""))
			v = "0";
		return v;
	}
	
	
	public static String dateFormat(Date date) {
		if (date == null){
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	
	public static String dateFormat(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		try {
			DateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	

	

	
	public static String castNumberToRoman(int n) {
		int[] arabic = new int[13];
		String[] roman = new String[13];
		int i = 0;
		String o = "";
		arabic[0] = 1000;
		arabic[1] = 900;
		arabic[2] = 500;
		arabic[3] = 400;
		arabic[4] = 100;
		arabic[5] = 90;
		arabic[6] = 50;
		arabic[7] = 40;
		arabic[8] = 10;
		arabic[9] = 9;
		arabic[10] = 5;
		arabic[11] = 4;
		arabic[12] = 1;

		roman[0] = "M";
		roman[1] = "CM";
		roman[2] = "D";
		roman[3] = "CD";
		roman[4] = "C";
		roman[5] = "XC";
		roman[6] = "L";
		roman[7] = "XL";
		roman[8] = "X";
		roman[9] = "IX";
		roman[10] = "V";
		roman[11] = "IV";
		roman[12] = "I";

		while (n > 0) {
			while (n >= arabic[i]) {
				n = n - arabic[i];
				o = o + roman[i];
			}
			i++;
		}
		return o;
	}
	
	public static Date removeSecond(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date removeMillisecond(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	
	public static String removeSpecialCodePoint(String s) {
		if(s==null) return "";
		Integer[] _16Arr = new Integer[] { 31 };
		List<Integer> _16List = new ArrayList<Integer>(_16Arr.length);
		for (int i = 0; i < _16Arr.length; i++)
			_16List.add(_16Arr[i]);
		if (isEmpty(s))
			return null;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (!_16List.contains(s.codePointAt(i))) {
				builder.append(s.charAt(i));
			}
		}
		return builder.toString().trim();
	}
	
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static Integer[] convertArrayIntegers(Integer[] integers) {
		if (integers == null)
			return null;
		Integer[] array = new Integer[integers.length];
		for (int i = 0; i < integers.length; i++) {
			array[i] = integers[i];
		}
		return array;
	}
	
	 
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");
            s = s.replaceAll("[.]$", "");
        }  
        return s;  
    } 
    
    public static String ArabToRoman(int Arab){
        String Roman = "";
        String[][] list={
                   {"","I","II","III","IV","V","VI","VII","VIII","IX"},
                   {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
                   {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
   {"","M","MM","MMM","","","","","",""}
       };
       Roman += list[3][Arab/1000%10];
       Roman += list[2][Arab/100%10];
       Roman += list[1][Arab/10%10];
       Roman += list[0][Arab%10];
       return Roman;
   }
    
    public static int RomanToInt(String roman){
    int res=0;
    try {
        while(roman.charAt(0)=='M'){res+=1000;roman = roman.substring(1);}
        if(roman.charAt(0)=='D'){res+=500;roman = roman.substring(1);}
        while(roman.charAt(0)=='C'){res+=100;roman = roman.substring(1);}
        if(roman.charAt(0)=='D') {res+=300;roman = roman.substring(1);}
        else if(roman.charAt(0)=='M'){res+=800; roman = roman.substring(1);}
        if(roman.charAt(0)=='L'){res+=50;roman = roman.substring(1);}
        while(roman.charAt(0)=='X'){res+=10;roman=roman.substring(1);}
        if(roman.charAt(0)=='L'){res+=30;roman=roman.substring(1);}
        else if(roman.charAt(0)=='C'){res+=80;roman=roman.substring(1);}
        if(roman.charAt(0)=='V'){res+=5;roman=roman.substring(1);}
        while(roman.charAt(0)=='I'){res+=1;roman=roman.substring(1);}
        if(roman.charAt(0)=='V'){res+=3;roman=roman.substring(1);}
        else if(roman.charAt(0)=='X'){res+=8;roman= roman.substring(1);}
    } catch (StringIndexOutOfBoundsException e) {
            return res;
    }
    return res;
}
    public static int RomanToInt3(String Roman){
        int res = 0;
        String regex=null;
        String s = "'',0,I,1,II,2,III,3,IV,4,V,5,VI,6,VII,7,VIII,8,IX,9,X,10,XX,20,XXX,30,XL,40,L,50,LX,60,LXX,70,LXXX,80,XC,90,C,100,CC,200,CCC,300,CD,400,D,500,DC,600,DCC,700,DCCC,800,CM,900,M,1000,MM,2000,MMM,3000";
        String c[]=s.split(",");//将字符串打散，存入数组s
        Pattern p;//正则
            //遍历循环，正则匹配
        for(int t=c.length-2;t>0;t-=2){
            String roma = c[t];
            int value = Integer.parseInt(c[t+1]);
            regex="^("+roma+")";      p=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
            Matcher matcher=p.matcher(Roman);
            if(matcher.find()){
                res += value;
               Roman=Roman.substring(((String)roma).length());
//                  System.out.print(Roman + "\t\t");
//                  System.out.print(regex + "\t\t");
//                  System.out.println(res);
            }
        }
        return res;
    }
    
    public static String getSingleRomaNumberFromString(String origin){
    	String returnString = "";
    String[] romanElements = {"I","V","X","L","C","D","M"};
    String[] strings = origin.split("");
    for(int i=0;i<strings.length;i++){
    	for(int j=0;j<romanElements.length;j++){
    		if(strings[i].equals(romanElements[j])){
    			returnString =returnString +strings[i];	
    		}
    	}
    }
    return returnString;
    }
    
    public static void main(String[] args) {
		System.out.println(Common.RomanToInt3("III"));
		System.out.println(Common.getSingleRomaNumberFromString("手术间2-01"));
	}
}
