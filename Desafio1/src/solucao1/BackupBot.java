package solucao1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class BackupBot extends Thread {

	public static File src;
	public static File dest;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner scn = new Scanner(System.in);
		System.out.println("Digite os endereços");
		
		src = new File(scn.nextLine());
		dest = new File(scn.nextLine());
		
		BackupBot bpBot = new BackupBot();
		
		bpBot.start();
		
		scn.close();
	
	}
	
	public static void copyFiles(File source, File dest) throws IOException
	{
	    InputStream is = null;
	    OutputStream os = null;
	    
	    try 
	    {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);

	        int length;
	        byte[] buffer = new byte[1024];
	        
	        while ((length = is.read(buffer)) > 0) 
	        {
	            os.write(buffer, 0, length);
	        }
	    } 
	    finally 
	    {
	        is.close();
	        os.close();
	    }
	}
	
	public void run()
	{   
	    if (src.isDirectory())
	    {
	        if (!dest.exists())
	        {
	        	dest.mkdirs();
	        }

	        String files[] = src.list();

	        for (String file : files)
	        {
	            File srcFile = new File(src, file);
	            File destFile = new File(dest, file);

	            try 
	            {
					copyFiles(srcFile, destFile);
				} 
	            catch (IOException e) 
	            {
					e.printStackTrace();
				}
	        }
	    }
	    else
	    {
	        InputStream in = null;
	        OutputStream out = null;

	        try
	        {
	            in = new FileInputStream(src);
	            out = new FileOutputStream(dest);

	            byte[] buffer = new byte[1024];

	            int length;
	            while ((length = in.read(buffer)) > 0)
	            {
	                out.write(buffer, 0, length);
	            }
	        }
	        catch (Exception e)
	        {
	            try
	            {
	                in.close();
	            }
	            catch (IOException e1)
	            {
	                e1.printStackTrace();
	            }

	            try
	            {
	                out.close();
	            }
	            catch (IOException e1)
	            {
	                e1.printStackTrace();
	            }
	        }
	    }
		
	}

}
