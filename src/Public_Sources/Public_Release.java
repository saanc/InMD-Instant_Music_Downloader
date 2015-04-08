package Public_Sources;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Public_Release {
        static String songName,movieName,location;
        static int ind,flag,ind1;
        static String[] songList,movieList,songURLList;
        static Scanner in=new Scanner(System.in);
	
        public static void main(String[] args) {
		initialize();
		while(true){
			flag=0;
			menuDisplay();
			int choice=in.nextInt();
			if(choice==1){
                searchBySongAndMovieName();
			}
			else if(choice==2){
                searchBySongName();
			}
			else if(choice==3){
				songsFromMovie();		
			}
			else if(choice==4){
				top10();
			}
			else if(choice==5){
				todo();
			}
			else{
				System.out.println("\n\tWrong input!");
			}
		}
	}
		
	public static void initialize(){
		System.out.println("Enter the location where you want to save all the files:");
		location=in.nextLine();
		System.out.println("");
		String aug="Thank You!...Keep Your terminal in Full Screen Mode for better view\nTo Proceed...Press Enter";
		for(int i=0;i<aug.length();i+=1){
			System.out.print(aug.charAt(i));
			delay(50);
		}
		 aug=in.nextLine();
	}
        
	
	public static void menuDisplay(){
		System.out.println("\n\t__________________________Welcome to InMD__________________________\n\t-------------------------------------------------------------------\n\t  more efficiency  more happiness  ^_^  ......Developed by saanc.\n\t-------------------------------------------------------------------\n\n");
		System.out.println("\n\t===================================================================");
		System.out.println("\n\tMenu:");
		System.out.println("\n\t1) Download the song by song name and movie/album/artist name.\n\t2) Download the song by song name.\n\t3) Get list of songs of the movie.\n\t4) Top 10 Bollywood Releases.\n\t5) Make TODO list to download songs.");
		System.out.println("\n\t===================================================================");
		System.out.print("\n\nEnter your choice number from the Menu: ");
	}
        
	public static void searchBySongAndMovieName(){
		in.nextLine();
		System.out.print("\nEnter the song name: ");
		songName=in.nextLine();
		System.out.print("\nEnter the movie/Album/Artist name: ");
		movieName=in.nextLine();
		System.out.print("\n\n\tProcessing...");
		getSong();
	}
	
	public static void searchBySongName(){
		in.nextLine();
		System.out.print("\nEnter the song name: ");
		songName=in.nextLine();
		movieName=null;
		System.out.print("\n\n\tProcessing...");
		getSong();
	}
	
	public static void songsFromMovie(){
		in.nextLine();
		System.out.print("\nEnter the movie name: ");
		movieName=in.nextLine();
		System.out.print("\n\n\tProcessing...");
		GetListFromWiki();
		if(ind==0){
			AugmentedGetListFromWiki();
		}
		if(ind==0){
			System.out.println("\n\tSorry! didn't find any result :(");
			return;
		}
		System.out.println("\n\tHere are the songs of movie: "+movieName);
		System.out.println("\n\t------------------------------------------------------");
		int i;
		for(i=1;i<=ind;i+=1){
			System.out.println("\t"+i+") "+songList[i-1]);
		}
		System.out.println("\n\t------------------------------------------------------");
		System.out.print("\n\nHow many songs you wanna download from this list?: ");
		int NoOfChoices=in.nextInt();
		if(NoOfChoices>ind){
			System.out.println("\n\tNumber of choices exceeded!");
			return;
		}
		System.out.println("\n\nEnter the song numbers that you wanna download separated by space\n");
		int[] choices=new int[30];
		for(i=0;i<NoOfChoices;i+=1){
			choices[i]=in.nextInt();
		}
		
		for(i=0;i<NoOfChoices;i+=1){
			if(choices[i]>0&&choices[i]<=ind){
				songName=songList[choices[i]-1];
				System.out.print("\n\n\tProcessing...");
			System.out.println("\n==================================================================");
				System.out.println("\n\nDownloading the song \""+songName+"\"...\n(Downloading might be slow due to your internet connection)");
				System.out.print("\n\n\tPlease Wait....");
				SaveUrlToFile(songURLList[choices[i]-1]);
				if(flag==0)
				System.out.println("\n\n\tDownloading Completed!\n\n");
			System.out.println("\n==================================================================\n");
			}
		}
	
	}
	
	public static void top10(){
		System.out.print("\n\n\tProcessing...");
		getTop10();
		if(ind==0){
			System.out.println("Not Available");
			return;
			}
		System.out.println("\n\tHere are the top 10 bollywood songs: ");
		System.out.println("\n\t------------------------------------------------------");
		int i;
		for(i=1;i<=ind;i+=1){
			System.out.println("\t"+i+") "+songList[i-1]+" - "+movieList[i-1]);
		}
		System.out.println("\n\t------------------------------------------------------");
		System.out.print("\n\nHow many songs you wanna download from this list?: ");
		int NoOfChoices=in.nextInt();
		if(NoOfChoices>ind){
			System.out.println("\n\tNumber of choices exceeded!");
			return;
		}
		System.out.println("\n\nEnter the song numbers that you wanna download separated by space\n");
		int[] choices=new int[30];
		for(i=0;i<NoOfChoices;i+=1){
			choices[i]=in.nextInt();
		}
		
		for(i=0;i<NoOfChoices;i+=1){
			if(choices[i]>0&&choices[i]<=ind){
			songName=songList[choices[i]-1];
			movieName=movieList[choices[i]-1];
			System.out.print("\n\n\tProcessing...");
			getSong();
			}
		}
	}
 
	
	public static void todo(){
		System.out.print("\nEnter TODO list size: ");
		int TODOsize=in.nextInt();
		in.nextLine();
		songList=new String[TODOsize];
		movieList=new String[TODOsize];
		for(int i=0;i<TODOsize;i+=1){
			System.out.print("\nEnter the name of song "+(i+1)+": ");
			songList[i]=in.nextLine();
			System.out.print("\nEnter the name of movie "+(i+1)+": ");
			movieList[i]=in.nextLine();
		}
		System.out.println("\n\tHere is your TODO list: ");
		System.out.println("\n\t------------------------------------------------------");
		int i;
		for(i=1;i<=TODOsize;i+=1){
			System.out.println("\t"+i+") "+songList[i-1]+" - "+movieList[i-1]);
		}
		System.out.println("\n\t------------------------------------------------------");
		
		System.out.println("\n\tAll songs will automatically be downloaded soon :)\n");
		for(i=0;i<TODOsize;i+=1){
			movieName=movieList[i];
			songName=songList[i];
			System.out.print("\n\n\tProcessing...");
			getSong();
		}
	}
	
	

	
	public static void getTop10(){
		ind=0;
		ind1=0;
		Document doc;
		songList=new String[30];
		movieList=new String[30];
		try {
			doc = Jsoup.connect("http://hindigeetmala.net/latest_top_10.htm").userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			//System.out.println(doc);
			Elements ele=doc.select("a[href]");
			for(Element li: ele){
				String song=li.toString(),name;
				if(song.contains("<a href=\"/song/")){
					name=song.substring(song.indexOf(">")+2);
					name=name.substring(0,name.indexOf("<")-1);
					songList[ind]=name;
					ind+=1;
				}
				else if(song.contains("<a href=\"/movie/")){
					//System.out.println(song);
					name=song.substring(song.indexOf(">")+1);
					name=name.substring(0,name.indexOf("<"));
					movieList[ind1]=name;
					ind1+=1;
				}
			}
		} catch (IOException e) {
			flag=1;
			System.out.println("\n\tConnection Error!");
		}
	}
	
	
	public static void AugmentedGetListFromWiki(){
		String WikiLink=AugmentedGetWikiLinkFromGoogle();
		//System.out.println(WikiLink);
		songList=new String[30];
		songURLList=new String[30];
		ind=0;
		ind1=0;
		Document doc;
		try {
			doc = Jsoup.connect(WikiLink).userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			Elements ele=doc.select("a[href]");
			for(Element li: ele){
				String song=li.toString(),name;
				if(song.contains(".mp3")){
					//System.out.println(song);
					name=song.substring(song.indexOf("-")+1);
////				System.out.println(name);
					name=name.substring(0,name.indexOf("-"));
					name=name.replace("%20", " ");
					songList[ind]=name;
					ind+=1;
					songURLList[ind1]=song.substring(song.indexOf("\"")+1,song.indexOf(".mp3")+4);
//					//System.out.println(SongLinkList[ind1]);
					ind1+=1;
				}
				
			}
		} catch (IOException e) {
			flag=1;
			System.out.println("\n\tNo such movie exists!");
			
		}
	}
	
	public static String AugmentedGetWikiLinkFromGoogle(){
		Document doc;
		String mod_link = null;
		try {
			doc = Jsoup.connect("http://www.google.co.in/search?q=site:downloadming.nu%20"+movieName+"%20mp3%20"+"songs").userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
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
	
	
	public static void GetListFromWiki(){
		String WikiLink=GetWikiLinkFromGoogle();
		songList=new String[30];
		songURLList=new String[30];
		ind=0;
		ind1=0;
		Document doc;
		try {
			doc = Jsoup.connect(WikiLink).userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
			Elements ele=doc.select("a[href]");
			//System.out.println(doc);
			for(Element li: ele){
				String song=li.toString(),name;
				if(song.contains(".mp3")){
					//System.out.println(song);
					name=song.substring(song.indexOf("<b>")+4);
//				System.out.println(name);
					name=name.substring(0,name.indexOf("<"));
					//name=name.replace("%20", " ");
					songList[ind]=name;
					ind+=1;
					songURLList[ind1]=song.substring(song.indexOf("\"")+1,song.indexOf(".mp3")+4);
//					//System.out.println(SongLinkList[ind1]);
					ind1+=1;
				}
				
			}
		} catch (IOException e) {
			AugmentedGetListFromWiki();
			
		}
	}
	
	public static String GetWikiLinkFromGoogle(){
		Document doc;
		String mod_link = null;
		try {
			doc = Jsoup.connect("http://www.google.co.in/search?q=site:webmusic.in%20"+movieName).userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
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
			AugmentedGetListFromWiki();
		}
		return mod_link;
	}
	
	
	public static void getSong(){
		System.out.println("\n==================================================================");
		String download_link=crawl();
		if(download_link==null||(!download_link.contains(".mp3"))){
			flag=1;
			System.out.println("\n\tSorry! didn't find any result :(");
		}
		else{
			if(movieName!=null)
			System.out.println("\n\nDownloading the song \""+songName+"\"...\n(Downloading might be slow due to your internet connection)");
			else
				System.out.println("\n\nDownloading the song \""+songName+"\"...\n(Downloading might be slow due to your internet connection)");
			System.out.print("\n\n\tPlease Wait....");
			SaveUrlToFile(download_link);
			if(flag==0)
			System.out.println("\n\n\tDownloading Completed!\n\n");
			
		}
		System.out.println("\n==================================================================\n");
		
	}
	
	
	public static String crawl() 
	{
		String mod_link=getlink();
		if(mod_link==null)
			return mod_link;
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
			doc = Jsoup.connect("http://www.google.co.in/search?q="+songName+"+"+movieName+"+"+"mp3skull").userAgent("Chrome").ignoreHttpErrors(true).timeout(0).get();
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
		    OutputStream outstream = new FileOutputStream(new File(location+"/"+songName+".mp3"));
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
	
	public static void delay(int t){
		try {
		    Thread.sleep(t);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
}

