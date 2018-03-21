# app启动短暂白屏或黑屏问题解决

    <style name="Main" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowBackground">@color/trans</item>
        <item name="android:windowDisablePreview">true</item>
    
    </style>

# edittext软键盘弹出时短暂黑屏问题解决
llroot为activity的根布局
llRoot.getRootView().setBackgroundColor(Color.parseColor("#FFFFFF"));

未解决的问题

  scrollview多层嵌套+edittext,edittext无法滚动的问题
  
  com.example.ndebuger.widget.ScrollEditText  直接使用这个类就可以解决上面的问题
  
  https://www.cnblogs.com/holyknight-zld/archive/2012/07/17/socket_chatroom.html
  
  
 # TCP 传输
   两个端点的建立连接后会有一个传输数据的通道，这通道称为流，而且是建立在网络基础上
   的流，称之为socket 流。该流中既有读取，也有写入。
   
   tcp 的两个端点：一个是客户端，一个是服务端。
   
   客户端：对应的对象，Socket
   服务端：对应的对象，ServerSocket
   
   TCP 客户端：
   
   ①：建立tcp 的socket 服务，最好明确具体的地址和端口。这个对象在创建时，就已
   经可以对指定ip 和端口进行连接(三次握手)。
   
   ②：如果连接成功，就意味着通道建立了，socket 流就已经产生了。只要获取到socket
   流中的读取流和写入流即可，只要通过getInputStream 和getOutputStream 就可以获取两
   个流对象。
   
   ③：关闭资源。 
  
 # udp巩固
 
  udp 的发送端：
  
  ①：建立udp 的socket 服务，创建对象时如果没有明确端口，系统会自动分配一个未
  被使用的端口。
 
  ②：明确要发送的具体数据。
  
  ③：将数据封装成了数据包。
  
  ④：用socket 服务的send 方法将数据包发送出去。
  
  ⑤：关闭资源。
  
  udp 的接收端：
  
  ①：创建udp 的socket 服务，必须要明确一个端口，作用在于，只有发送到这个端口
  的数据才是这个接收端可以处理的数据。
  
  
  ②：定义数据包，用于存储接收到数据。
  
  ③：通过socket 服务的接收方法将收到的数据存储到数据包中。
  
  ④：通过数据包的方法获取数据包中的具体数据内容，比如ip、端口、数据等等。
  
  ⑤：关闭资源。
  
  
# UDP案例
https://www.cnblogs.com/gccbuaa/p/6882802.html

1）server端

    /**
     * UDPserver端
     * */
    public class UdpServer {
        // 定义一些常量
        private final intMAX_LENGTH = 1024; // 最大接收字节长度
        private final intPORT_NUM   = 5066;   // port号
        // 用以存放接收数据的字节数组
        private byte[] receMsgs = new byte[MAX_LENGTH];
        // 数据报套接字
        private DatagramSocket datagramSocket;
        // 用以接收数据报
        private DatagramPacket datagramPacket;
       
        public UdpServer(){
            try {
                /******* 接收数据流程**/
                // 创建一个数据报套接字，并将其绑定到指定port上
                datagramSocket = new DatagramSocket(PORT_NUM);
                // DatagramPacket(byte buf[], int length),建立一个字节数组来接收UDP包
                datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
                // receive()来等待接收UDP数据报
                datagramSocket.receive(datagramPacket);
               
                /****** 解析数据报****/
                String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
                System.out.println("Server Rece:" + receStr);
                System.out.println("Server Port:" + datagramPacket.getPort());
               
                /***** 返回ACK消息数据报*/
                // 组装数据报
                byte[] buf = "I receive the message".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort());
                // 发送消息
                datagramSocket.send(sendPacket);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭socket
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
            }
        }
    }

2）client

    /***
     * UDPclientClient端
     ***/
    public class UdpClient {
       
        private String sendStr = "SendString";
        private String netAddress = "127.0.0.1";
        private final intPORT_NUM = 5066;
       
        private DatagramSocket datagramSocket;
        private DatagramPacket datagramPacket;
       
        public UdpClient(){
            try {
               
                /*** 发送数据***/
                // 初始化datagramSocket,注意与前面Server端实现的差别
                datagramSocket = new DatagramSocket();
                // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
                byte[] buf = sendStr.getBytes();
                InetAddress address = InetAddress.getByName(netAddress);
                datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
                // 发送数据
                datagramSocket.send(datagramPacket);
               
                /*** 接收数据***/
                byte[] receBuf = new byte[1024];
                DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
                datagramSocket.receive(recePacket);
               
                String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
                System.out.println("Client Rece Ack:" + receStr);
                System.out.println(recePacket.getPort());
               
               
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭socket
                if(datagramSocket != null){
                    datagramSocket.close();
                }
            }
        }  
    }