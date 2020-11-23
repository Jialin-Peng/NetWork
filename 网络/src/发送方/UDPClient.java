package ���ͷ�;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public static void main(String[] args) {
		//1.����Socket���ڷ��ͺͽ�������
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		Scanner sc = new Scanner(System.in);
		//2.�������ݱ��������洢
		try {
			socket = new DatagramSocket();
			while(true) {
				System.out.println("�ң�");
				String cInfo = sc.nextLine();
				byte[] buff = cInfo.getBytes();
				packet = new DatagramPacket(buff, buff.length, InetAddress.getByName("127.0.0.1"), 7777);
				//3.�������ݱ�
				socket.send(packet);
				if(cInfo.equals("bye")) {
					break;
				}
				//4.���շ��������
				byte[] buff2 = new byte[1024];
				DatagramPacket packet2 = new DatagramPacket(buff2, buff2.length);
				socket.receive(packet2);
				byte[] data = packet2.getData();
				System.out.println("���߿ͷ�˵��"+new String(data,0,packet2.getLength()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//�ر���
			if(socket!=null) {
				socket.close();
			}
		}
	}
}


