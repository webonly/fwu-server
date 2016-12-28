package com.techsun.famouswine.util;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.xml.DOMConfigurator;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;


public class ImagesProcess {
	
	public boolean checkImageSize(File srcFile, int standardWidth) {
		boolean flag = false;
		try {
			BufferedImage srcImg = ImageIO.read(srcFile);
	    	if (srcImg == null) {
	    		return flag;
	    	}
	    	int imageWidth = srcImg.getWidth();
	    	if (imageWidth > standardWidth) {
	    		flag = true;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
    	
		return flag;
    }

	public static int resizeImage(File srcFile, File tempFile, int standardWidth) throws Exception {
    	String type = getFormatName(srcFile);
    	BufferedImage srcImg = ImageIO.read(srcFile);
    	if (srcImg == null) {
    		return -2;
    	}
    	int imageWidth = srcImg.getWidth();
    	if (imageWidth <= standardWidth) {
    		return -1;
    	}
    	int imageHeight = srcImg.getHeight();
		int newWidth= standardWidth;
		int newHeight= (int) Math.floor((double) (imageHeight*standardWidth/imageWidth));
    	
		if(type.equalsIgnoreCase("gif")) {
			GifDecoder decoder = new GifDecoder(); 
            int status = decoder.read(new FileInputStream(srcFile));
            if (status != GifDecoder.STATUS_OK) { 
                throw new IOException("read image " + srcFile.getPath() + " error!"); 
            }
  
            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            FileOutputStream fos = new FileOutputStream(tempFile);
            encoder.start(fos);
            encoder.setRepeat(decoder.getLoopCount()); 
            for (int i = 0; i < decoder.getFrameCount(); i ++) { 
                encoder.setDelay(decoder.getDelay(i)); 
                BufferedImage image = zoom(decoder.getFrame(i), newWidth, newHeight);
                encoder.addFrame(image); 
            } 
            encoder.finish();
            fos.close();
		} else {
			BufferedImage buffImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
	    	buffImg.getGraphics().drawImage(
	    			srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0,
	    			0, null);

	    	ImageIO.write(buffImg, type, tempFile);
		}
		return 0;
    }
    
	public static BufferedImage zoom(BufferedImage sourceImage, int width, int height){
        BufferedImage zoomImage = new BufferedImage(width, height, sourceImage.getType());
        Image image = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Graphics gc = zoomImage.getGraphics();
        gc.setColor(Color.WHITE);
        gc.drawImage(image, 0, 0, null);
        return zoomImage;
    }
    
    public static String getFormatName(File file) throws IOException{
        String formatName = null;
          
        ImageInputStream iis = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> imageReader =  ImageIO.getImageReaders(iis);
        if(imageReader.hasNext()){
            ImageReader reader = imageReader.next();
            formatName = reader.getFormatName();
        }
        iis.close();
        return formatName;
    }
    
    public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	if (in != null) {
            		in.close();
            	}
            	if (fi != null) {
            		fi.close();
            	}
                if (out != null) {
                	out.close();
                }
                if (fo != null) {
                	fo.close();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getFilePath(String path, String fileName) {
		String pathTemp = "";
		if (path.endsWith("/")) {
			pathTemp = path + fileName;
		} else {
			pathTemp = path + "/" + fileName;
		}
		
		return pathTemp;
    }
    
    public static void readLog4jConfig() {
		String ConfigurationDir = System.getenv("CPSXMLCFG_ROOT");
		if ("".equalsIgnoreCase(ConfigurationDir.trim())){
			System.out.println("CPSXMLCFG_ROOT hasn't been set correct!");
    		return;
    	}
		if (ConfigurationDir.indexOf(ConfigurationDir.length()-1) != '/'){
    		ConfigurationDir = ConfigurationDir + "/";
    	}
		DOMConfigurator.configure(ConfigurationDir + "log4j.xml");
    }
}
