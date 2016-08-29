package yang.mqtt;

import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.Calendar;

import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 *
 * @date 2015年10月15日 下午2:18:20
 * @author James Yang
 * @version 1.0
 * @since
 */
public class TimerPublisher extends Publisher {
	private String host;//"tcp://192.168.0.101:61613"
	private String username;//"admin"
	private String password;//"password"
	private String clientname = "Publisher";
	private String publictopic = "Topics/htjs/serverToPhone";
	private int size = 0;
	private int times = 1;
	private int sendnum;
	private Calendar start;
	private Calendar end;
	
	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	/**
	 * 
	 */
	public TimerPublisher() {
		//super();
		try {
			this.setClientname("Publisher");
			this.setPublictopic("Topics/htjs/serverToPhone");
			this.setSize(50);
			this.setTimes(200);
			this.setHost("tcp://10.4.45.133:61613");
			this.setUsername("admin");
			this.setPassword("password");
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.MINUTE, 1);
			this.setStart(ca);
			Calendar ca2 = Calendar.getInstance();
			ca2.set(Calendar.MINUTE, 5);
			this.setEnd(ca2);
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.printConnectInfo(fwr);
			this.timerpublishMessages(start, end, fwr, connection, this.getSize(), this.getTimes());
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
	public TimerPublisher(String host, String username, String password) {
		super();
		try {
			this.setClientname("Publisher");
			this.setPublictopic("Topics/htjs/serverToPhone");
			this.setSize(50);
			this.setTimes(20);
			this.host = host;
			this.username = username;
			this.password = password;
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.MINUTE, 1);
			this.setStart(ca);
			Calendar ca2 = Calendar.getInstance();
			ca2.set(Calendar.MINUTE, 5);
			this.setEnd(ca2);
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.printConnectInfo(fwr);
			this.timerpublishMessages(start, end, fwr, connection, size, times);
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
	public TimerPublisher(String name,String host, String username, String password,String publictopic,int size,int times) throws URISyntaxException {
		super();
		try {
			if(!name.isEmpty()){
				this.setClientname(name);
			}
			this.setHost(host);
			this.setUsername(username);
			this.setPassword(password);
			this.setPublictopic(publictopic);
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.MINUTE, 1);
			this.setStart(ca);
			Calendar ca2 = Calendar.getInstance();
			ca2.add(Calendar.MINUTE, 5);
		//	ca2.set(Calendar.MINUTE, 5);
			this.setEnd(ca2);
			MQTT mqtt = this.newMqtt();
			FutureConnection connection = this.getConnection(mqtt);
			FileWriter fwr = this.openSource();
			this.printConnectInfo(fwr);
			this.timerpublishMessages(start, end, fwr, connection, size, times);
			this.closeSource(fwr);
			this.disconnect(connection);
		} catch (Exception e) {
			System.out.println("Run Exception.");
			e.printStackTrace();
		}
	}
	public void timerpublishMessages(Calendar start,Calendar end,OutputStreamWriter fwr,FutureConnection connection,int size,int times){
		sendnum =1;
		Calendar ca1 = Calendar.getInstance();;
//		while(start.getTimeInMillis()<end.getTimeInMillis()){
		while(Calendar.getInstance().before(end)){	
		try {
			String payload = setBody(size);
			
			//for(sendnum =1;sendnum<times;sendnum++){
				this.publishMessages(connection, publictopic, payload);
				Calendar ca = Calendar.getInstance();
				String datetime = ca.get(Calendar.HOUR_OF_DAY)+":"+ca.get(Calendar.MINUTE)+":"
						+ ""+ca.get(Calendar.SECOND)+"."+ca.get(Calendar.MILLISECOND);
				
				System.out.println("Send "+sendnum+" "+datetime+" "+publictopic+" "+payload);
				log(fwr,"Send", sendnum, datetime, publictopic, payload);
				Thread.sleep((long)(Math.random()*1000));
			//}
			sendnum++;
			
		} catch (Exception e) {
			System.out.println("publish failed!");
			e.printStackTrace();
		}
		
		//start.setTimeInMillis(start.getTimeInMillis()+1000);
	}
		Future<Void> f4 = connection.publish(publictopic, "End message no".getBytes(), QoS.AT_LEAST_ONCE, false);
		Calendar ca2 = Calendar.getInstance();
		String datetime = ca2.get(Calendar.HOUR_OF_DAY)+":"+ca2.get(Calendar.MINUTE)+":"
				+ ""+ca2.get(Calendar.SECOND)+"."+ca2.get(Calendar.MILLISECOND);
		System.out.println("Send "+sendnum+" "+datetime+" "+publictopic+" "+"End message no");
		log(fwr,"Send", sendnum, datetime, publictopic, "End message no");
		
		String res = "Send:"+sendnum+" in "+(ca2.getTimeInMillis()-ca1.getTimeInMillis())+" ms";
		System.out.println(res);
		log(fwr,res);
	}
	public void publishMessages(FutureConnection connection,String publictopic,String message){
		Future<Void> f3 = connection.publish(publictopic,message.getBytes(), QoS.AT_LEAST_ONCE, false);
		//Thread.sleep(10);
	}
}