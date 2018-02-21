package com.xjx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHelper {
	
	/**
	 * 上传
	 * @param request
	 * @param multipartFiles
	 * @param filePath
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request, MultipartFile[] multipartFiles, String filePath) {
		List<String> relativePaths = new ArrayList<String>();
		String DatePath = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String savePath = filePath+ File.separator + DatePath;
		File saveDir = new File(savePath);
		if (!saveDir.exists() || !saveDir.isDirectory()) {
		saveDir.mkdirs();
		}
		if(multipartFiles != null&&multipartFiles.length!=0){
			for(int i=0;i<multipartFiles.length;i++){
				String suffix = multipartFiles[i].getOriginalFilename().substring(multipartFiles[i].getOriginalFilename().lastIndexOf("."));
				String uuidName = Common.getUUID() + suffix;// make new file name
				String fileName = savePath + File.separator + uuidName;
				String relativePath = filePath + File.separator + DatePath + File.separator + uuidName;
				try {
					multipartFiles[i].transferTo(new File(fileName));
					relativePaths.add(relativePath);
					} catch (IOException e) {
					e.printStackTrace();
					}
			}
		}
		return relativePaths;
	}
	
	/**
	 * 下载
	 * @param request
	 * @param response
	 * @param filePath
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String filePath) {
		String realPath = filePath;
		File file = new File(realPath);
		String filenames = file.getName();
		InputStream inputStream;
		try {
		inputStream = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[inputStream.available()];
		inputStream.read(buffer);
		inputStream.close();
		response.reset();
		//先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		response.addHeader("Content-Disposition",  "attachment;filename="  +  new  String(filenames.replaceAll(" ","").getBytes("utf-8"),"iso8859-l"));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		os.write(buffer);// 输出文件
		os.flush();
		os.close();
		} catch (Exception e){
		e.printStackTrace();
		}
	}
	
}
