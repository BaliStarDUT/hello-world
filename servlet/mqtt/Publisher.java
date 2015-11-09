package yang.mqtt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.Calendar;

import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 *
 * @date 2015年10月15日 上午10:59:30
 * @author James Yang
 * @version 1.0
 * @since
 */
public class Publisher {
	
	private String host;//"tcp://192.168.0.101:61613"
	private String username;//"admin"
	private String password;//"password"
	private String clientname = "Publisher";
	private String publictopic = "Topics/htjs/serverToPhone";
	private int size = 0;
	private int times = 1;
	private int sendnum;

	/**
	 * 
	 */
	public Publisher() {
		super();
//		try {
//			this.setClientname("Publisher");
//			this.setPublictopic("Topics/htjs/serverToPhone");
//			this.setSize(50);
//			this.setTimes(20);
//			this.setHost("tcp://10.4.45.133:61613");
//			this.setUsername("admin");
//			this.setPassword("password");
//			MQTT mqtt = this.newMqtt();
//			FutureConnection connection = this.getConnection(mqtt);
//			FileWriter fwr = this.openSource();
//			this.printConnectInfo(fwr);
//			this.publishMessages(fwr,connection, size, times);
//			this.closeSource(fwr);
//			this.disconnect(connection);
//		} catch (URISyntaxException e) {
//			System.out.println("Run Exception.");
//			e.printStackTrace();
//		}
	}

	/**
	 * @param host
	 * @param username
	 * @param password
	 * @param publictopic
	 */
	public Publisher(String host, String username, String password) {
		//super();
		try {
			this.setClientname("Publisher");
			this.setPublictopic("Topics/htjs/serverToPhone");
			this.setSize(50);
			this.setTimes(20);
			this.host = host;
			this.username = username;
			this.password = password;
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.printConnectInfo(fwr);
			this.publishMessages(fwr,connection, size, times);
			this.closeSource(fwr);
			this.disconnect(connection);
		} catch (URISyntaxException e) {
			System.out.println("Run Exception.");
			e.printStackTrace();
		}
	}

	/**
	 * @param host
	 * @param username
	 * @param password
	 * @throws URISyntaxException 
	 */
	public Publisher(String name,String host, String username, String password,String publictopic,int size,int times) throws URISyntaxException {
		super();
		try {
			if(!name.isEmpty()){
				this.clientname = name;
			}
			this.host = host;
			this.username = username;
			this.password = password;
			this.publictopic = publictopic;
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.printConnectInfo(fwr);
			this.publishMessages(fwr,connection, size, times);
			this.closeSource(fwr);
			this.disconnect(connection);
		} catch (Exception e) {
			System.out.println("Run Exception.");
			e.printStackTrace();
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	 public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getPublictopic() {
		return publictopic;
	}

	public void setPublictopic(String publictopic) {
		this.publictopic = publictopic;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	/**
     * 设置消息内容
     * @param body
     */
	public static String setBody(int size) {
		Calendar ca = Calendar.getInstance();
		String body =ca.getTimeInMillis()+"";
		//自动构造消息内容
		if(size>0){
			String DATA = "abcdefghijklmnopqrstuvwxyz";
		       for( int i=0; i < size; i ++) {
		            body += DATA.charAt(i%DATA.length());
		        }
		}else{
		    body = "this is a message";
		}
		return body;
	}
	public MQTT newMqtt() throws URISyntaxException{
		MQTT mqtt = new MQTT();
		mqtt.setHost(host);
		mqtt.setUserName(username);
		mqtt.setPassword(password);
		return mqtt;
		
	}
	public FutureConnection getConnection(MQTT mqtt){
		FutureConnection connection = mqtt.futureConnection();
		Future<Void> f1 = connection.connect();
		try {
			f1.await();
		} catch (Exception e) {
			System.out.println("connect failed!");
			e.printStackTrace();
		}
		return connection;
	}
	/**
     * 输出连接配置信息
	 * @param fwr 
     */
    public void printConnectInfo(OutputStreamWriter fwr){
    	Calendar ca = Calendar.getInstance();
		String datetime = ca.get(Calendar.YEAR)+"-"+ca.get(Calendar.MONTH)+"-"+ca.get(Calendar.DAY_OF_MONTH)+" "+ca.get(Calendar.HOUR_OF_DAY)+":"+ca.get(Calendar.MINUTE)+":"
				+ ""+ca.get(Calendar.SECOND)+"."+ca.get(Calendar.MILLISECOND);
    	System.out.println("DateTime:"+datetime);
		System.out.println("User name:"+username);
    	System.out.println("Password:"+password);
    	System.out.println("Host name:"+host);
    	//System.out.println("Host port:"+port);
    	try {
			fwr.write("DateTiem:"+datetime+"\n");
    		fwr.write("User name:"+username+"\n");
			fwr.write("Password:"+password+"\n");
			fwr.write("Host name:"+host+"\n");
			fwr.flush();
		} catch (IOException e) {
			System.out.println("Error write log!");
			e.printStackTrace();
		}
    }
	 public FileWriter openSource(){
		 File file = null;
		 FileWriter fwr=null;
	    	try {
	    		//注意同步问题
	    		file = new File(clientname+"log.txt");
				fwr = new FileWriter(file);
			} catch (IOException e) {
				System.out.println("Openfile failed!");
				e.printStackTrace();
			}
			return fwr;
	 }
	 public void publishMessages(OutputStreamWriter fwr,FutureConnection connection,int size,int times){
			try {
				String payload = setBody(size);
				Calendar ca1 = Calendar.getInstance();
				for(sendnum =1;sendnum<times;sendnum++){
					Future<Void> f3 = connection.publish(publictopic, payload.getBytes(), QoS.AT_LEAST_ONCE, false);
					Calendar ca = Calendar.getInstance();
					String datetime = ca.get(Calendar.HOUR_OF_DAY)+":"+ca.get(Calendar.MINUTE)+":"
							+ ""+ca.get(Calendar.SECOND)+"."+ca.get(Calendar.MILLISECOND);
					
					System.out.println("Send "+sendnum+" "+datetime+" "+publictopic+" "+payload);
					log(fwr,"Send", sendnum, datetime, publictopic, payload);
					Thread.sleep(1000);
				}
				Future<Void> f4 = connection.publish(publictopic, "End message".getBytes(), QoS.AT_LEAST_ONCE, false);
				Calendar ca2 = Calendar.getInstance();
				String datetime = ca2.get(Calendar.HOUR_OF_DAY)+":"+ca2.get(Calendar.MINUTE)+":"
						+ ""+ca2.get(Calendar.SECOND)+"."+ca2.get(Calendar.MILLISECOND);
				System.out.println("Send "+sendnum+" "+datetime+" "+publictopic+" "+"End message");
				log(fwr,"Send", sendnum, datetime, publictopic, "End message");
				
				String res = "Send:"+sendnum+" in "+(ca2.getTimeInMillis()-ca1.getTimeInMillis())+" ms";
				System.out.println(res);
				log(fwr,res);
			} catch (Exception e) {
				System.out.println("publish failed!");
				e.printStackTrace();
			}

		}
	 	/**
	     * 打印日志
	     * @param i
	     * @param topic
	     * @param msg
	 	 * @param fwr 
	     */
	    public static synchronized void log(OutputStreamWriter fwr,String meth,int i,String datetime,String topic,String msg){
	    	try {
	    		fwr.write(meth+" ");
				fwr.write(i+" "+datetime+" ");
				fwr.write(topic+" ");
				fwr.write(msg+"\n");
				fwr.flush();
			} catch (IOException e) {
				System.out.println("Error write log!");
				e.printStackTrace();
			}
	    }
	    public static synchronized void log(OutputStreamWriter fwr,String msg){
	    	try {
	    		fwr.write(msg+"\n");
				fwr.flush();
			} catch (IOException e) {
				System.out.println("Error write log!");
				e.printStackTrace();
			}
	    }
	    public void closeSource(FileWriter fwr){
	    	try {
				//connection.disconnect();//.await();
				fwr.close();
			} catch (Exception e) {
				System.out.println("Source close failed!");
				e.printStackTrace();
			}
	    }
	    public void disconnect(FutureConnection connection){
			try {
				connection.disconnect();
			} catch (Exception e) {
				System.out.println("disconnect failed!");
				e.printStackTrace();
			}
		}

}
