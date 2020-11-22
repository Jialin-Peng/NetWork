package �����;

import java.io.DataInputStream; //���� DataInputStream��
import java.io.DataOutputStream;//����DataOutputStream�� 
import java.io.IOException;//����IOException��
import java.net.ServerSocket;//����ServerSocket��   
import java.net.Socket; //����Socket �� 
import java.util.Scanner; //����Scanner��

/** 
 * ģ��qq���칦�ܣ� ʵ�ֿͻ������������һ��һ�������칦�ܣ��ͻ������ȷ������죬����������ڷ������˺Ϳͻ�����ʾ�� 
 * Ȼ���������Ҳ����������Ϣ��ͬ����ϢҲ�ڿͻ��˺ͷ���������ʾ 
 */  

// ��������  
public class ChatServer {//ChatServer��  
    private int port = 8189;// Ĭ�Ϸ������˿�  

    public ChatServer() {

    }  

    // ����ָ���˿ڵķ�����  
    public ChatServer(int port) {//���췽��  
        this.port = port;//������������ֵ�������  
    }  

    // �ṩ����  
    public void service() {//����service����  
        try {// ��������������  
            ServerSocket server = new ServerSocket(port);//����  ServerSocket��             
            Socket socket = server.accept();// �ȴ��ͻ�����  
            try {                  
                DataInputStream in = new DataInputStream(socket  
                        .getInputStream());// ��ȡ�ͻ��˴�������Ϣ��DataInputStream                   
                DataOutputStream out = new DataOutputStream(socket  
                        .getOutputStream());// ��ͻ��˷�����Ϣ��DataOutputStream                     
                Scanner scanner = new Scanner(System.in);//�Ӽ��̽�������  
                while (true) {                       
                    String accpet = in.readUTF();// ��ȡ���Կͻ��˵���Ϣ   
                    System.out.println(accpet);//������Կͻ��˵���Ϣ  
                    String send = scanner.nextLine();//nextLine��ʽ�����ַ���  
                    System.out.println("��������" + send);//�����ʾ��Ϣ                        
                    out.writeUTF("��������" + send);//�ѷ������˵����뷢���ͻ���   
                }  
            } finally {// ��������ʧ�ܵĻ�����ִ��socket.close();
                socket.close();//�ر����� 
                server.close();//�ر�                
            }  
        } catch (IOException e) {//�����쳣
            e.printStackTrace();  
        }  
    }  

    public static void main(String[] args) {//�����򷽷�  
        new ChatServer().service();//���� service���� 
    }  
}  
