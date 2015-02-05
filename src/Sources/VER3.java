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

public class VER3 {
static String song_name,movie_name;
static int ind,flag;
static String[] SongList,MovieList;
	public static void main(String[] args) {
		while(true){
			flag=0;
			Scanner in=new Scanner(System.in);
			System.out.println("\n\t__________________________Welcome to InMD__________________________\n\t-------------------------------------------------------------------\n\t      more efficiency  more happiness  ^_^  ......Developed by saanc.\n\t-------------------------------------------------------------------\n\n");
			System.out.println("\n\t===========================================================");
			System.out.println("\n\t```````````````````````````````````````````````````````````");
			System.out.println("\n\tMenu:");
			System.out.println("\n\t1) Download the song by song name.\n\t2) Download the song by song name and movie/album/artist name.\n\t3) Get list of songs of the movie.\n\t4) Top 20 Bollywood Releases.\n\t5) Make TODO list to download songs.");
			System.out.println("\n\t===========================================================");
			System.out.println("\n\t```````````````````````````````````````````````````````````");
			System.out.print("\n\nEnter your choice number from the Menu: ");
			int choice=in.nextInt();
			if(choice>5||choice<1){
				System.out.println("\n\tWrong choice number!");
				ClearSCR();
				continue;
			}
			if(choice==1){
				String ToAvoidEnter=in.nextLine();
				System.out.print("\nEnter the song name: ");
				song_name=in.nextLine();
				movie_name=null;
				System.out.print("\n\n\tProcessing...");
				get_song();
			}
			else if(choice==2){
				String ToAvoidEnter=in.nextLine();
				System.out.print("\nEnter the song name: ");
				song_name=in.nextLine();
				System.out.print("\nEnter the movie/Album/Artist name: ");
				movie_name=in.nextLine();
				System.out.print("\n\n\tProcessing...");
				get_song();
			}
			else if(choice==3){
				String ToAvoidEnter=in.nextLine();
				System.out.print("\nEnter the movie name: ");
				movie_name=in.nextLine();
				System.out.print("\n\n\tProcessing...");
				GetListFromWiki();
				System.out.println("Completed!");
				System.out.println("\n\tHere are the songs of movie: "+movie_name);
				System.out.println("\n\t------------------------------------------------------");
				int i;
				for(i=1;i<=ind;i+=1){
					System.out.println("\t"+i+") "+SongList[i-1]);
				}
				System.out.println("\n\t------------------------------------------------------");
				System.out.print("\n\nHow many songs you wanna download from this list?: ");
				int NoOfChoices=in.nextInt();
				if(NoOfChoices>ind){
					System.out.println("\n\tNumber of choices exceed!");
					continue;
				}
				System.out.println("\n\nEnter the song numbers that you wanna download separated by space\n");
				int[] choices=new int[30];
				for(i=0;i<NoOfChoices;i+=1){
					choices[i]=in.nextInt();
				}
				
				for(i=0;i<NoOfChoices;i+=1){
					if(choices[i]>0&&choices[i]<=ind){
					song_name=SongList[choices[i]-1];
					if(song_name.contains("(")){
						song_name=song_name.substring(0,song_name.indexOf("("));
					}
					else if(song_name.contains("-")){
						song_name=song_name.substring(0,song_name.indexOf("-"));
					}
					else if(song_name.contains("_")){
						song_name=song_name.substring(0,song_name.indexOf("_"));
					}
					else if(song_name.contains("[")){
						song_name=song_name.substring(0,song_name.indexOf("["));
					}
					else if(song_name.contains(".")){
						song_name=song_name.substring(0,song_name.indexOf("."));
					}
					//System.out.println(song_name);
					System.out.print("\n\n\tProcessing...");
					get_song();
					}
				}
				
			}
			else if(choice==4){
				System.out.print("\n\n\tProcessing...");
				GetTop20();
				movie_name=null;
				System.out.println("Completed!");
				System.out.println("\n\tHere are the top 20 songs: ");
				System.out.println("\n\t------------------------------------------------------");
				int i;
				for(i=1;i<=ind;i+=1){
					System.out.println("\t"+i+") "+SongList[i-1]);
				}
				System.out.println("\n\t------------------------------------------------------");
				System.out.print("\n\nHow many songs you wanna download from this list?: ");
				int NoOfChoices=in.nextInt();
				if(NoOfChoices>ind){
					System.out.println("\n\tNumber of choices exceed!");
					continue;
				}
				System.out.println("\n\nEnter the song numbers that you wanna download separated by space\n");
				int[] choices=new int[30];
				for(i=0;i<NoOfChoices;i+=1){
					choices[i]=in.nextInt();
				}
				
				for(i=0;i<NoOfChoices;i+=1){
					if(choices[i]>0&&choices[i]<=ind){
					song_name=SongList[choices[i]-1];
					System.out.print("\n\n\tProcessing...");
					get_song();
					}
			}
			}
			else if(choice==5){
				System.out.print("\nEnter TODO list size: ");
				int TODOsize=in.nextInt();
				String toAvoidEnter=in.nextLine();
				SongList=new String[TODOsize];
				MovieList=new String[TODOsize];
				for(int i=0;i<TODOsize;i+=1){
					System.out.print("\nEnter the name of song "+(i+1)+": ");
					SongList[i]=in.nextLine();
					System.out.print("\nEnter the name of movie "+(i+1)+": ");
					MovieList[i]=in.nextLine();
				}
				System.out.println("\n\tHere is your TODO list: ");
				System.out.println("\n\t------------------------------------------------------");
				int i;
				for(i=1;i<=TODOsize;i+=1){
					System.out.println("\t"+i+") "+SongList[i-1]+" - "+MovieList[i-1]);
				}
				System.out.println("\n\t------------------------------------------------------");
				
				System.out.println("\n\tAll songs will automatically be downloaded soon :)");
				for(i=0;i<TODOsize;i+=1){
					movie_name=MovieList[i];
					song_name=SongList[i];
					System.out.print("\n\n\tProcessing...");
					get_song();
				}
			}
			else{
				System.out.println("\n\tWrong input!");
			}
			ClearSCR();
		}
	}
	
	public static void GetTop20(){
		ind=0;
		Document doc;
		SongList=new String[30];
		try {
			doc = Jsoup.connect("http://www.top10songsindia.com/p/top-10-hindi-songs.html").userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			Elements ele=doc.select("a[href]");
			for(Element li: ele){
				String song=li.toString(),name;
				if(song.contains("youtu.be")){
					name=song.substring(song.indexOf(">")+1);
					name=name.substring(0,name.indexOf("<"));
					SongList[ind]=name;
					ind+=1;
				}
				if(ind==20)
					break;
			}
		} catch (IOException e) {
			flag=1;
			System.out.println("\n\tConnection Error!");
		}
	}
	
	
	public static void GetListFromWiki(){
		String WikiLink=GetWikiLinkFromGoogle();
		SongList=new String[30];
		ind=0;
		Document doc;
		try {
			doc = Jsoup.connect(WikiLink).userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			Elements ele=doc.select("a[href]");
			for(Element li: ele){
				String song=li.toString(),name;
				if(song.contains(".mp3")){
					name=song.substring(song.indexOf("div>")+4);
					name=name.substring(name.indexOf("div>")+5);
					name=name.substring(name.indexOf("-")+2);
					name=name.substring(0,name.indexOf(".mp3"));
					SongList[ind]=name;
					ind+=1;
				}
			}
		} catch (IOException e) {
			flag=1;
			System.out.println("\n\tNo such movie or song exists!");
		}
	}
	
	public static String GetWikiLinkFromGoogle(){
		Document doc;
		String mod_link = null;
		try {
			doc = Jsoup.connect("http://www.google.co.in/search?q=allmp3song.com:%20"+movie_name).userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
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
			flag=1;
			System.out.println("\n\tNo such movie exists!");
		}
		return mod_link;
	}
	
	public static void get_song(){
		String download_link=crawl();
		if(download_link==null||(!download_link.contains(".mp3"))){
			System.out.println("\n\tSorry! didn't find any result :(");
		}
		else{
			if(movie_name!=null)
			System.out.println("\n\nDownloading the song \""+song_name+"\" from the movie \""+movie_name+"\"...\n(Downloading might be slow due to your internet connection)");
			else
				System.out.println("\n\nDownloading the song \""+song_name+"\"...\n\n(Downloading might be slow due to your internet connection)");
			System.out.print("\n\n\tDownloading....");
			SaveUrlToFile(download_link);
			if(flag==0)
			System.out.println("\n\n\tDownloading Completed!\n\n");
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
				if(linki.contains(quality)&&linki.contains(".mp3\"")){
					flag=1;
					
					download_link=linki.substring(linki.indexOf("\"")+1,linki.indexOf(".mp3\"")+4);
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
					if(linki.contains(".mp3\"")){
						//System.out.println(linki);
						download_link=linki.substring(linki.indexOf("\"")+1,linki.indexOf(".mp3\"")+4);
						break;
					}
				}
			}
		} catch (IOException e) {
			flag=1;
			System.out.println("\n\tNo such movie or song exists!");
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
			flag=1;
			System.out.println("\n\tNo such movie or song exists!");
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
		    System.out.println("Completed!");
 
		} catch (MalformedURLException e) {
			flag=1;
			System.out.println("\n\tConnection Error!");
		} catch (IOException e) {
			flag=1;
			System.out.println("\n\tConnection Error!");
		}
 
	}
	
	public static void ClearSCR(){
		
	}

}
