package �ͻ���;

import java.io.DataInputStream;//����DataInputStream��
import java.io.DataOutputStream;//����DataOutputStream
import java.io.IOException;//����IOException��
import java.net.Socket;//����Socket��
import java.util.Scanner;//����Scanner��

/**
 * ע���õ������������DataInputStream��DataOutputStream���ɶԳ��֣�������ֽ���
 */
// �ͻ�����
public class ChatClient {//����������
    private String host = "localhost";// Ĭ�����ӵ�����
    private int port = 8189;// Ĭ�����ӵ��˿�8189

    public ChatClient() {

    }

    // ���ӵ�ָ���������Ͷ˿�
    public ChatClient(String host, int port) {//���췽��
        this.host = host;//�����췽���Ĳ���host���ݸ������host
        this.port = port;//�����췽���Ĳ���port���ݸ������port
    }

    public void chat() {//chat����
        try {
            // ���ӵ�������
            Socket socket = new Socket(host, port);//����Socket�����

            try {

                DataInputStream in = new DataInputStream(socket
                        .getInputStream());// ��ȡ�������˴�������Ϣ��DataInputStream

                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());// ��������˷�����Ϣ��DataOutputStream


                Scanner scanner = new Scanner(System.in);// װ�α�׼�����������ڴӿ���̨����

                while (true) {
                    String send = scanner.nextLine();//��ȡ����̨���������
                    System.out.println("�ͻ��ˣ�" + send);//����������������ʾ ��Ҳ���ǿͻ�����������˷��͵���Ϣ
                    // �Ѵӿ���̨�õ�����Ϣ���͸�������
                    out.writeUTF("�ͻ��ˣ�" + send);//���ͻ��˵���Ϣ���ݸ�������             
                    String accpet = in.readUTF();// ��ȡ���Է���������Ϣ
                    System.out.println(accpet);//������Է���������Ϣ
                   
                }

            } finally {
                socket.close();//�ر�Socket����
            }
        } catch (IOException e) {//�����쳣
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//�����򷽷�
        new ChatClient().chat();//����chat����
    }
}
