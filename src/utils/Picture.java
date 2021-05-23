package utils;

import hpmanagement.Activator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public class Picture {
	public static String rename(String fileName){
		String name = "没有名字";
		try{
			if(fileName.equals("") || fileName == null){
				return name;
			}else{
				 String  houzhui = fileName.substring(fileName.lastIndexOf("."));
				 return (int)(Math.random() * 100000) + houzhui;
			}
		}catch(Exception e){
			e.printStackTrace();
			
			return name;
		}
	}
	
	public static void upload(File file, String picture){
		try {
			FileInputStream input = new FileInputStream(file);
			//获取icons路径
			URL url = Activator.getDefault().getBundle().getResource("icons");
			String str = FileLocator.toFileURL(url).toString().substring(6);
			File file1 = new File(str, picture);
			FileOutputStream out = new FileOutputStream(file1);
			
			byte [] bb = new byte[ (int) file.length()];
			int len = 0;
			while((len = input.read(bb)) > 0){
				out.write(bb,0,len);
			}
			
			out.flush();
			out.close();
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
