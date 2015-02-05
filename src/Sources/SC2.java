package Sources;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SC2 {
static String song_name,movie_name;
	public static void main(String[] args) {
		while(true){
		Scanner in=new Scanner(System.in);
		System.out.println("\n\t_________________Welcome to The_Music_Search Zone__________________\n\t-------------------------------------------------------------------\n\t      more efficiency  more happiness  ^_^  ......created by saanc.\n\t-------------------------------------------------------------------");
		System.out.println("\nEnter the song name u wanna download: ");
		song_name=in.nextLine();
		System.out.println("\nEnter the movie/Album/Artist name(If not applicable put dot here): ");
		movie_name=in.nextLine();
		
		get_song();
		}

	}
	
	public static void get_song(){
		String download_link=crawl();
		if(download_link==null||(!download_link.contains(".mp3"))){
			System.out.println("\nSorry! didn't find any result :(");
		}
		else{
			System.out.println("\nDownloading the song \""+song_name+"\" from the movie \""+movie_name+"\"...");
			SaveUrlToFile(download_link);
			System.out.println("\nCongratulations! Song \""+song_name+"\" has been downloaded :)\n");
		}
		
	}
	
	
	public static String crawl() 
	{
		String mod_link=getlink();
		String download_link = null;
		Document doc;
		try {
			doc = Jsoup.connect(mod_link).userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			Elements links = doc.select("a[href]");
			int i=2,flag=0;
			String quality="/320/";
			while(i>0){
			for (Element link : links)
			{
				String linki=link.toString();
				if(linki.contains(quality)&&linki.contains(".mp3")){
					flag=1;
					download_link=linki.substring(linki.indexOf("\"")+1,linki.indexOf(".mp3")+4);
					break;
				}
			}
			
			if(flag==1)
				break;
			quality="/128/";
			i-=1;
			}
			
			if(flag==0){
				for (Element link : links)
				{
					String linki=link.toString();
					if(linki.contains(".mp3")){
						download_link=linki.substring(linki.indexOf("\"")+1,linki.indexOf(".mp3")+4);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(download_link!=null)
		download_link=download_link.replace(" ","%20");
		return download_link;
	}
		
	public static String getlink(){
		Document doc;
		String mod_link = null;
		try {
			doc = Jsoup.connect("http://www.google.co.in/search?q="+song_name+"+"+movie_name+"+"+"mp3skull").userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			Elements links = doc.select("a[href]");
			for (Element link : links)
			{
				mod_link=link.toString();
				
				if(mod_link.contains("/url?q=http://")){
					mod_link=mod_link.substring(16,mod_link.indexOf("&amp"));
					break;
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mod_link;
		
	}
	
	public static void SaveUrlToFile(String download_link){
		try {
			URLConnection conn = new URL(download_link).openConnection();
		    InputStream is = conn.getInputStream();
		    OutputStream outstream = new FileOutputStream(new File("C:\\Users\\saan\\Downloads\\"+song_name+".mp3"));
		    byte[] buffer = new byte[4092];
		    int len;
		    while ((len = is.read(buffer)) > 0) {
		        outstream.write(buffer, 0, len);
		    }
		    outstream.close();
 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}

}
