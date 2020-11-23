package 接收方;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {
	public static void main(String[] args) {
		System.out.println("服务端启动...");
		//1.创建socket，用来发送和接收数据包
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		Scanner sc = new Scanner(System.in);
		//2.创建数据包，用来接收客户端发送的数据和ip，端口
		byte[] buff = new byte[1024];
		try {
			socket = new DatagramSocket(7777);
			packet = new DatagramPacket(buff, buff.length);
			while(true) {
				//3.接收socket和数据包来接收客户端的信息
				socket.receive(packet);
				//4.输出客户端发来的信息
				byte[] buff2 = packet.getData();
				String reply = new String(buff2,0,packet.getLength());
				if("bye".equals(reply)) {
					break;
				}
				InetAddress address = packet.getAddress();
				System.out.println("客户："+reply);
				//5.给客户端发送消息
				System.out.println("我：");
				String info = sc.nextLine();
				byte[] bytes = info.getBytes();
				DatagramPacket packet2 = new DatagramPacket(bytes,bytes.length,address,packet.getPort());
				socket.send(packet2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//6.关闭socket
			socket.close();
		}
	}
}


