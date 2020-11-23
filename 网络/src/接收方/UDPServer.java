package ���շ�;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {
	public static void main(String[] args) {
		System.out.println("���������...");
		//1.����socket���������ͺͽ������ݰ�
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		Scanner sc = new Scanner(System.in);
		//2.�������ݰ����������տͻ��˷��͵����ݺ�ip���˿�
		byte[] buff = new byte[1024];
		try {
			socket = new DatagramSocket(7777);
			packet = new DatagramPacket(buff, buff.length);
			while(true) {
				//3.����socket�����ݰ������տͻ��˵���Ϣ
				socket.receive(packet);
				//4.����ͻ��˷�������Ϣ
				byte[] buff2 = packet.getData();
				String reply = new String(buff2,0,packet.getLength());
				if("bye".equals(reply)) {
					break;
				}
				InetAddress address = packet.getAddress();
				System.out.println("�ͻ���"+reply);
				//5.���ͻ��˷�����Ϣ
				System.out.println("�ң�");
				String info = sc.nextLine();
				byte[] bytes = info.getBytes();
				DatagramPacket packet2 = new DatagramPacket(bytes,bytes.length,address,packet.getPort());
				socket.send(packet2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//6.�ر�socket
			socket.close();
		}
	}
}


