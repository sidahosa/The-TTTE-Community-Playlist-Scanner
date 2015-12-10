package CommunityRemakesUtility;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *  <p> <b> The TTTE Community's YouTube Playlist Scanner </b> </p>
 * 
 * The fastest way of retrieving and organizing usernames that have participated in 
 * past/current Thomas and friends community remakes.
 * This program scans through playlists located on The TTTE Community's YouTube Channel, it 
 * the should store each username into a treemap using the clip number as the key.
 * 
 * This program only works for The Thomas and Friends Community Channel's playlist.
 * 
 * @author Shawn Idahosa
 *
 */
public class YouTubeUserNameSorter {
	
	/* Used for organizing users participating in current community remake.
	 * This TreeMap will be in natural number order and use the clip number as key
	 * while the YouTuber's username will be the value.*/
	TreeMap <Integer, String> YouTubeUserName = new TreeMap <Integer, String>();
	String mainUrl;
	StringBuffer returnedWords = new StringBuffer();
	
	/*
	 * Constructor that takes in the YouTube URL of a playlist.
	 */
	public YouTubeUserNameSorter(String url){
		mainUrl = url;
	}
	
	/**
	 * Stores the usernames and corresponding clip into a organized treemap that will be
	 * printed in a numeric order list.
	 * @return Stringbuffer (That will be converted into a string)
	 * @throws IOException
	 */	
	private StringBuffer runScan() throws IOException{
		int clipindex = 0;
		// Retrieves YouTube playlist URL and puts into a Document node. 
		Document doc = Jsoup.connect(mainUrl).get(); 
		// Default code YouTube uses for displaying titles in playlist
		Elements users = doc.select("li a.pl-video-title-link.yt-uix-tile-link.yt-"
				+ "uix-sessionlink.spf-link");
		
		for (Element an : users){
			
			String temp = an.text().trim();
			
			//Counter for number of dashes detected
			int counter = temp.split("-").length - 1;
			
			// Disects the username which will be already seperated by dashes.
			if(counter == 3){ 
				String[] str = temp.split("-");
				YouTubeUserName.put(clipindex, str[2]);
				clipindex++;
			} else if (counter == 1) {
				String[] str = temp.split("-");
				YouTubeUserName.put(clipindex, str[1]);
				clipindex++;
			} else if (counter == 2){
				String[] str = temp.split("-");
				//Cases for spelling of clip
				String clip = "Clip"; 
				String clips = "clip";
				if(str[1].contains(clips) || str[1].contains(clip))
				{
					YouTubeUserName.put(clipindex, str[2]);
					clipindex++;
				} else {
					YouTubeUserName.put(clipindex, str[1]);
					clipindex++;
				}
			}
		}
		
		for (Entry<Integer, String> tem : YouTubeUserName.entrySet()){
			if( ( (tem.getKey()) + 1 ) < 10){
				//Case for keys less than 10
				returnedWords.append("Clip #0" + ( (tem.getKey()) + 1) + " - " 
						+ tem.getValue().toString() + "\n");
			} else {
			returnedWords.append("Clip #" + ( (tem.getKey()) + 1) + " - " 
		+ tem.getValue().toString() + "\n");
			}
		}
		return returnedWords;
	}
	
	/*
	 * The code below is used to run on a JAVA IDE.
	 */
	
/*	public static void main(String [] agrs) throws IOException{
		YouTubeUserNameSorter temp = new YouTubeUserNameSorter();
		Scanner myscan = new Scanner(System.in);
		
		System.out.println("Enter URL of YouTube Playlist: ");
		String url = myscan.nextLine();
		
		temp.insertUserName(url);
		myscan.close();
	}*/
	
}
