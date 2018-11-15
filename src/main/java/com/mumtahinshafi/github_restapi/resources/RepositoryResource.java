package com.mumtahinshafi.github_restapi.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mumtahinshafi.github_restapi.model.Repository;

@Path("/repositories")
public class RepositoryResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Repository> getMessage(@QueryParam("s") String s,
									   @QueryParam("start") int start,
									   @QueryParam("size") int size) throws IOException {
		
		List<Repository> repoList = new ArrayList<>();
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date sevenDaysAgo = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(s != null) {
			String url = "https://api.github.com/search/repositories?q="+s+"+created:%3E"+sdf.format(sevenDaysAgo)+"&sort=stars&order=desc";
			creatingResponse(repoList, url, 1, 0);
		}else if(start > 0 && size > 0) {
			String url = "https://api.github.com/search/repositories?q=created:%3E"+sdf.format(sevenDaysAgo)+"&sort=stars&order=desc";
			creatingResponse(repoList, url, start, size);
		}else {
			String url = "https://api.github.com/search/repositories?q=created:%3E"+sdf.format(sevenDaysAgo)+"&sort=stars&order=desc";
			creatingResponse(repoList, url, 1, 0);
		}
	    
		return repoList;
	}
	
	public void creatingResponse(List<Repository> repo, String url, int start, int size) throws IOException {
		StringBuffer response = httpConnect(url);
		JSONObject myResponse = new JSONObject(response.toString());
	    JSONArray items = myResponse.getJSONArray("items");
	    if(size<=0) {
	    	size=items.length();
	    }
	    if(start<0) {
	    	start=1;
	    }
	    
	    for(int i=start-1; i<size+start-1; i++) {
	    	String urls = myResponse.getJSONArray("items").getJSONObject(i).getString("html_url");
		    String subscribers_url = myResponse.getJSONArray("items").getJSONObject(i).getString("subscribers_url");
		    int stars = myResponse.getJSONArray("items").getJSONObject(i).getInt("watchers");
		    
		    StringBuffer response1 = httpConnect(subscribers_url);
		    JSONArray myResponse1 = new JSONArray(response1.toString());
		    int watchers = myResponse1.length();
		    
		    Repository repository = new Repository(urls, stars, watchers);
		    repo.add(repository); 
	    }
	}
	
	public StringBuffer httpConnect(String url) throws IOException{
		URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("User-Agent", "Mozilla/5.0");
	    BufferedReader in = new BufferedReader(
	            new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	    	response.append(inputLine);
	    }
	    in.close();
	    return response;
	}
	
}
