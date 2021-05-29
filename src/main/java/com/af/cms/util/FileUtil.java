package com.af.cms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	
	public static void fileupload(byte[] file,String filePath,String fileName) throws IOException {
		 // Target directory		
		File targetfile = new File(filePath);
		if(targetfile.exists()) {
			targetfile.mkdirs();
		}
		
		 // Binary stream write		
		FileOutputStream out = new FileOutputStream(filePath+fileName);
	    out.write(file);
	    out.flush();
	    out.close();
	}

}
