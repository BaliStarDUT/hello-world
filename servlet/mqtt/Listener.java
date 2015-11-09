package yang.mqtt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

/**
 *
 * @date 2015年10月15日 下午1:43:15
 * @author James Yang
 * @version 1.0
 * @since
 */
public class Listener {
	private String host;//"tcp://192.168.0.101:61613"
	private String username;//"admin"
	private String password;//"password"
	private String clientname = "Publisher";
	private String reseivetopic = "Topics/htjs/serverToPhone";
	private int getnum;
	
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

	public String getReseivetopic() {
		return reseivetopic;
	}

	public void setReseivetopic(String reseivetopic) {
		this.reseivetopic = reseivetopic;
	}

	/**
	 * 
	 */
	public Listener() {
		super();
		try {
			this.setClientname("Listener");
			this.setReseivetopic("Topics/htjs/serverToPhone");
			this.setHost("tcp://10.4.45.133:61613");
			this.setUsername("admin");
			this.setPassword("password");
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.subscribeTopics(connection);
			this.printConnectInfo(fwr);
			this.receiveMessages(fwr, connection);
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
	 * @param publictopic
	 */
	public Listener(String host, String username, String password) {
		super();
		try {
			this.setClientname("Publisher");
			this.setReseivetopic("Topics/htjs/serverToPhone");
			this.host = host;
			this.username = username;
			this.password = password;
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.subscribeTopics(connection);
			this.printConnectInfo(fwr);
			this.receiveMessages(fwr, connection);
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
	public Listener(String name,String host, String username, String password,String reseivetopic) throws URISyntaxException {
		super();
		try {
			if(!name.isEmpty()){
				this.clientname = name;
			}
			this.setHost(host);
			this.setUsername(username);
			this.setPassword(password);
			this.setReseivetopic(reseivetopic);
			MQTT mqtt = this.newMqtt();
//			mqtt.setClientId(name);
//			mqtt.setCleanSession(false);
//			short a = 5;
//			mqtt.setKeepAlive(a);
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.subscribeTopics(connection);
			this.printConnectInfo(fwr);
			this.receiveMessages(fwr, connection);
			this.closeSource(fwr);
			this.disconnect(connection);
		} catch (Exception e) {
			System.out.println("Run Exception.");
			e.printStackTrace();
		}
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
    public void subscribeTopics(FutureConnection connection){
		Topic[] topics = {new Topic(this.getReseivetopic(),QoS.AT_LEAST_ONCE)};
		try {
			Future<byte[]> f2 = connection.subscribe(topics);
			byte[] qoses = f2.await();
		} catch (Exception e) {
			System.out.println("subscribe topics failed!");
			e.printStackTrace();
		}
	}
    public void receiveMessages(FileWriter fwr,FutureConnection connection){
    	try {
			getnum= 1;
			Calendar ca1 = Calendar.getInstance();
			while(true){
				Future<Message> receive = connection.receive();
				Message message = receive.await();
				//System.out.println("Topic:"+message.getTopic());
				byte[] payload = message.getPayload();
				String info = new String(payload,0,payload.length,Charset.forName("UTF-8"));
				//System.out.println("Message:"+info); 
				
				message.ack();
				
				Calendar ca = Calendar.getInstance();
				String datetime = ca.get(Calendar.HOUR_OF_DAY)+":"+ca.get(Calendar.MINUTE)+":"
						+ ""+ca.get(Calendar.SECOND)+"."+ca.get(Calendar.MILLISECOND);
				
				System.out.println("Get "+getnum+" "+datetime+" "+message.getTopic()+" "+info);
				log(fwr,"Get ", getnum, datetime, message.getTopic(), info);
				getnum++;
//				if("End message".equals(info)){
//					break;
//				}
//				if(!connection.isConnected()){
//					break;
//				}
//				if(message.getPayload()==null){
//					break;
//				}
//					
				//receive.await(500, TimeUnit.MILLISECONDS);
			}
//			Calendar ca2 = Calendar.getInstance();
//			String res = "Get:"+(getnum-1)+" in "+(ca2.getTimeInMillis()-ca1.getTimeInMillis())+" ms";
//			System.out.println(res);
//			log(fwr,res);
			//printRes();
		} catch (Exception e) {
			System.out.println("receive messages failed!");
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
