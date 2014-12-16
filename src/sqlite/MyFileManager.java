package sqlite;



import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class MyFileManager {

	
	static FileChannel inChannel;
	static FileChannel outChannel;

	public MyFileManager(){
		
		
	}
	
	public boolean delete(String fichier){		
		File f = new File(fichier);		
		return 	f.delete();
	}
	
	public  boolean copier(String fichier_source, String fichier_dest)
	{
		boolean isCopy=false;
		FileInputStream src;
		try {
			src = new FileInputStream(fichier_source);
			FileOutputStream dest = new FileOutputStream(fichier_dest);
			inChannel = src.getChannel();
			outChannel = dest.getChannel();
			for (ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
					inChannel.read(buffer) != -1;
					buffer.clear()) {
				buffer.flip();
				while (buffer.hasRemaining()) outChannel.write(buffer);
				isCopy=true;
			}
		} catch (FileNotFoundException e) {
			isCopy=false;
			e.printStackTrace();
		} catch (IOException e) {
			isCopy=false;
			e.printStackTrace();
		}

		finally{
			try {
				inChannel.close();
				outChannel.close();
			} catch (IOException e) {
				isCopy=false;
				e.printStackTrace();
			}	   
		}
		return isCopy;
	}
}



