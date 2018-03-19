坑:
客户端发送的时候需要有\n"结尾
字符流读取

    this.buRead = new BufferedReader(new InputStreamReader(
                this.s.getInputStream(), "utf-8"));
                    
    while ((connet = readFromClient()) != null) {
        LoggerUtils.loge(this, "客户端说：" + connet);
        Message me = Message.obtain();
        InetAddress inetAddress = s.getInetAddress();
        String extMsg = inetAddress.getHostName() + " , " + inetAddress.getAddress();
        me.obj = extMsg + " , " + connet;
        me.what = 0x122;
        mHandler.sendMessage(me);
    
        for (Socket ss : MainActivity.socketList) {
            OutputStream out = ss.getOutputStream();
            out.write(("服务端说 -> " + connet + "\n").getBytes("utf-8"));
        }
    }

    private String readFromClient() {
        try {
            return buRead.readLine();
        } catch (Exception e) {
            //删除此Socket
            MainActivity.socketList.remove(s);
        }
        return null;
    }

字节流读取:客户端发送的时候不需要"\n"换行

    String connet = null;
    try {
        int len = -1;
        InputStream inputStream = s.getInputStream();
        byte[] buff = new byte[4 * 1024];
        while ((len = inputStream.read(buff)) != -1) {
            connet = new String(buff, 0, len, "utf-8");
            InetAddress inetAddress = s.getInetAddress();
            String extMsg = inetAddress.getHostName() + " , " + inetAddress.getHostAddress();
            LogUtils.loge(this, extMsg + ":" + connet);
    
            // 对客户端的心跳做响应
            if (connet.equalsIgnoreCase("ECHO")) {
                OutputStream out = s.getOutputStream();
                out.write((connet + "\n").getBytes("utf-8"));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
            
1. 消息的读取是阻塞式的，有消息来才会走
2. 在消息发送和接收的结尾要添加\n换行

