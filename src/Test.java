import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;




public class Test {

	public static void main(String[] args)throws Exception {

		/*JSONObject request=new JSONObject();
		request.put("op_id", "1");
		request.put("path", "");
		String requestString=request.toString();
		//requestString=StringEscapeUtils.escapeXml10(request.toString());
		System.out.println("RESPONSE:"+sendGet("http://72.26.124.12:443/websock?cname=Raghava_RaghavaiMac&content="+URLEncoder.encode(requestString)));
		
		*/
		
		for(int i=0;i<40000;i++){
			createTextFile("/Users/raghava/Desktop/FILE/Testing"+i,"ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			System.out.println("File:"+i);
		}
	}
	
	
	public static  void createTextFile(String fileLocalPath, String data)throws Exception
	{
	
		String userInfo1=data;
		String fileName = fileLocalPath.substring((fileLocalPath.lastIndexOf('/')+1));
		FileOutputStream fos = null;
		byte[] source=userInfo1.getBytes();

		   try
		   {
			  byte[] buf = new byte[30*1024]; 
			  File localFile = new File(fileLocalPath); // fileLocalPath includes name of the file also
			  fos = new FileOutputStream(localFile);
			  BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                      (new FileOutputStream(localFile),"UTF-8"));
			  		out.write(userInfo1);
				  // out.write(source,0,source.length);
				   //fos.flush(); 
			  try
			  {
				  fos.close();
				  out.close();
				 //fos = null;
				 
			  }
			  catch(Exception innerExcep)
			  {
			    
			  }

		   }
		   catch(Exception outerExcep)
		   {
			   System.out.println("createTextFile Exception:"+outerExcep);
		   }
		   
		   source=null;
		   fileName=null;
	}
	
	// HTTP GET request
	public static String sendGet(String url) throws Exception {
 
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		
		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		return response.toString();
 
	}
 
	// HTTP POST request
	private void sendPost(String url, String urlParameters) throws Exception {
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
	
	public static String excutePost(String targetURL)
	{

		URL url;
		HttpURLConnection connection = null;
		try {
		//Create connection
		url = new URL(targetURL);
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		//connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
		connection.setRequestProperty("Content-Language", "en-US");
		connection.setUseCaches (false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		//Send request
		/*DataOutputStream wr = new DataOutputStream ( connection.getOutputStream ());
		wr.writeBytes (urlParameters);
		wr.flush ();
		wr.close ();*/
		//Get Response
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer();
		while((line = rd.readLine()) != null)
		{
		response.append(line);
		}
		rd.close();
		return response.toString();
		} catch (Exception e)
		{
		System.out.println("excutePost:Exception:"+e);
		e.printStackTrace();
		return null;
		} finally
		{
		if(connection != null)
		{
		connection.disconnect();
		}
		}
		}
	
	
}
