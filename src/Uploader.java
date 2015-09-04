import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

import com.github.sardine.impl.SardineImpl;


public class Uploader implements Runnable{

	Thread uploader;
	private SardineImpl sardineImpl=null;
	private int index=0;
	public Uploader(int i){
		this.index=i;
	}
	
	public void upload(){
		uploader =  new Thread(this);
		uploader.start();
	}
	public void run() {
		try{
			
			HttpClientBuilder htpBuilder=HttpClientBuilder.create();
			HttpRequestInterceptor reqIn=new HttpRequestInterceptor() {
				
				@Override
				public void process(HttpRequest arg0, HttpContext arg1)
						throws HttpException, IOException {
					// TODO Auto-generated method stub
					arg0.setHeader("Connection","close");
					
				}
			};
			htpBuilder.addInterceptorFirst(reqIn);
			
			SardineImpl sardineImpl=new SardineImpl(htpBuilder);
			
			
			sardineImpl.setCredentials("raghu.meeniga@gmail.com","passw0rd");
			htpBuilder.build();
			
		byte[] data = FileUtils.readFileToByteArray(new File("/Users/raghava/Desktop/test.png"));
		sardineImpl.put("https://dav.dropdav.com/test_"+this.index+".png", data);
		}catch(Exception e){
			System.out.println("Ex:"+e);
		}
	}
	
}
