package com.dgut.modules.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReadUtil {

	  public static String readFile(String path) throws IOException{
		  BufferedReader  is=null;
			        String result="";
			        String line = null;
			         try {
			            @SuppressWarnings("unused")
			             int data=0;
			             byte[] by =new byte[1024];
			             is = new BufferedReader(new InputStreamReader(  
			                     new FileInputStream(new File(path)), "UTF-8")); 
			             while((line=is.readLine())!=null){
			                 //result+=(char)data;
			                //result=new String(data);
		                 result +=line+"\n";
			            }
			         } catch (FileNotFoundException e) {
		             System.out.println("未找到new.template文件！");
			            e.printStackTrace();
			        }
			         finally{
			            System.out.println("创建成功！");
			            is.close();
			        }
		         //return result.toString();
			         return result;
		 }
}
