package 发送方;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public static void main(String[] args) {
		//1.创建Socket用于发送和接收数据
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		Scanner sc = new Scanner(System.in);
		//2.创建数据报，用来存储
		try {
			socket = new DatagramSocket();
			while(true) {
				System.out.println("我：");
				String cInfo = sc.nextLine();
				byte[] buff = cInfo.getBytes();
				packet = new DatagramPacket(buff, buff.length, InetAddress.getByName("127.0.0.1"), 7777);
				//3.发送数据报
				socket.send(packet);
				if(cInfo.equals("bye")) {
					break;
				}
				//4.接收服务端数据
				byte[] buff2 = new byte[1024];
				DatagramPacket packet2 = new DatagramPacket(buff2, buff2.length);
				socket.receive(packet2);
				byte[] data = packet2.getData();
				System.out.println("在线客服说："+new String(data,0,packet2.getLength()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭流
			if(socket!=null) {
				socket.close();
			}
		}
	}
}


