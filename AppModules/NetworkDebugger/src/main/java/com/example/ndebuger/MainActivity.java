package com.example.ndebuger;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.common.corel.BaseActivity;
import com.example.ndebuger.manager.RemoteConnManager;
import com.example.utils.LoggerUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主界面:
 * tcp->udp ,端口发生改变的时候，以前执行的任务需要取消和清理
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_server_ip_info)
    TextView tvServerIpInfo;
    @BindView(R.id.rg_server_type)
    RadioGroup rgServerType;
    @BindView(R.id.rg_protoct_type)
    RadioGroup rgProtocalType;
    @BindView(R.id.et_server_ip)
    EditText etServerIp;
    @BindView(R.id.et_server_port)
    EditText etServerPort;
    @BindView(R.id.tv_conn_servers)
    TextView tvConnServers;
    @BindView(R.id.sv_parent)
    ScrollView svParent;
    @BindView(R.id.sv_rec_msgs)
    ScrollView svRec;
    @BindView(R.id.sv_send_msgs)
    ScrollView svSend;
    @BindView(R.id.tv_rec_msgs)
    TextView tvRec;
    @BindView(R.id.tv_send_msgs)
    TextView tvSend;
    @BindView(R.id.btn_connect_server)
    Button btnConnectServer;
    @BindView(R.id.btn_disc_all)
    Button btnDiscAll;
    @BindView(R.id.btn_disc_current)
    Button btnDisCurrent;
    @BindView(R.id.btn_test)
    Button btnTest;

    private boolean hasServer = true;
    private boolean hasTcp = true;
    private long lastSendTime;
    private int serverIpStrCount = 0;
    private int ipStrCont;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case GlobalConst.UPDATE_RECE_MSG:
                    if (msg.obj == null)
                        break;
                    String msgStr = (String) msg.obj;
                    tvRec.append(msgStr + "\r\n");
                    break;
                case GlobalConst.UPDATE_SEND_MSG:
                    if (msg.obj == null)
                        break;
                    String msgSendStr = (String) msg.obj;
                    tvSend.append(msgSendStr + "\r\n");
                    break;
                case GlobalConst.ENABLE_BTNS:
                    btnDiscAll.setEnabled(true);
                    btnDisCurrent.setEnabled(true);
                    btnTest.setEnabled(true);
                    break;
            }
            return false;
        }

    });
    private RemoteConnManager connManager;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        setSupportActionBar(toolbar);

        tvServerIpInfo.setText(NetworkUtils.getIPAddress(true));

        rgServerType.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    if (group.getCheckedRadioButtonId() == R.id.rb_client) {
                        hasServer = false;
                        etServerIp.setVisibility(View.VISIBLE);
                        btnConnectServer.setVisibility(View.VISIBLE);
                        btnDiscAll.setVisibility(View.VISIBLE);
                        btnDisCurrent.setVisibility(View.VISIBLE);
                        connManager.chageStateOnServiceTypeChange(RoleType.TCP_SERVER);
                    } else {
                        hasServer = true;
                        etServerIp.setVisibility(View.GONE);
                        btnConnectServer.setVisibility(View.GONE);
                        btnDiscAll.setVisibility(View.GONE);
                        btnDisCurrent.setVisibility(View.GONE);
                        // 状态发生改变，断开连接
                        connManager.chageStateOnServiceTypeChange(RoleType.TCP_CLIENT);
                    }
                    restoreAllComponents();
                }
        );

        rgProtocalType.setOnCheckedChangeListener(
                (group, checkedId) -> {
                    if (group.getCheckedRadioButtonId() == R.id.rb_udp) {
                        hasTcp = false;
                    } else {
                        hasTcp = true;
                    }

                    // TODO: 2018/3/17 端口发生改变，不管是什么连接都要断开
                    connManager.chageStateOnServiceTypeChange(RoleType.TCP_SERVER);
                    connManager.chageStateOnServiceTypeChange(RoleType.TCP_CLIENT);
                    restoreAllComponents();
                }
        );

        /**
         *
         scrollview双层嵌套问题解决
         当父类布局滑动时，父类拦截事件，子类布局.getParent().requestDisallowInterceptTouchEvent(false)

         当子类布局滑动时，父类不拦截事件，子类布局.getParent().requestDisallowInterceptTouchEvent(true)
         */

        svParent.setOnTouchListener((v, event) -> {
            svRec.getParent().requestDisallowInterceptTouchEvent(false); // 允许父类截断
            svSend.getParent().requestDisallowInterceptTouchEvent(false); // 允许父类截断
            LoggerUtils.loge(MainActivity.this, "触摸了外层ScrollView");
            return false;
        });

        svRec.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            LoggerUtils.loge(MainActivity.this, "触摸了接收ScrollView");
            return false;
        });

        svSend.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            LoggerUtils.loge(MainActivity.this, "触摸了发送ScrollView");
            return false;
        });
    }


    @Override
    protected void addListener() {

        // TODO: 2018/3/17 限定最大字符数
        etServerPort.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

        etServerIp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                serverIpStrCount = s.length();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etServerPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ipStrCont = s.length();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ipStrCont < 4) {
                    ToastUtils.showLong("端口号为4位数，请补齐");
                } else {
                    if (serverIpStrCount > 0) {
                        btnConnectServer.setEnabled(true);
                    }
                }
            }
        });

        // TODO: 2018/3/17 监听action.DONE操作
        etServerPort.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // 执行连接服务器操作
                if (hasServer) {
                    openServer();
                } else {
                    doConnectServer();
                }
            }
            return true;
        });
    }

    /**
     * 打开服务器
     */
    private void openServer() {
        if (hasTcp) {
            openTcpServer();
        } else {
            openUdpServer();
        }
    }


    /**
     * 打开udp服务器
     */
    private void openUdpServer() {

    }

    /**
     * 打开tcpserver
     */
    private void openTcpServer() {

    }

    @Override
    protected void initData() {
        connManager = RemoteConnManager.getInstance(mContext, handler);
    }

    @Override
    protected PermissionListener getPermissonCallBack() {
        return null;
    }

    @OnClick({R.id.btn_connect_server, R.id.btn_disc_all, R.id.btn_disc_current, R.id.btn_test})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect_server:
                doConnectServer();
                break;
            case R.id.btn_disc_all:
                break;
            case R.id.btn_disc_current:
                break;
            case R.id.btn_test:
                // TODO: 2018/3/17 限制两次消息发送的时间间隔
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastSendTime < 3000) {
                    // LoggerUtils.loge(this, "两次发送消息的时间间隔不能小于3s");
                    ToastUtils.showShort("两次发送消息的时间间隔不能小于3s");
                    return;
                }

                if (hasTcp) {
                    if (hasServer) {
                        if (connManager.getTcpServerSocket() == null) return;
                        connManager.sendTestMsgToTcpServer(new OnMsgSendComplete() {
                            @Override
                            public void sucess() {
                                lastSendTime = currentTime;
                            }

                            @Override
                            public void error() {

                            }
                        });
                    } else {
                        if (connManager.getTcpClientSocket() == null) return;
                        sendTestToTcpClient(currentTime);
                    }
                } else {
                    if (hasServer) {

                    } else {

                    }
                }

                break;
        }
    }

    /**
     * 执行连接服务器操作
     */
    private void doConnectServer() {
        Editable ipEable = etServerIp.getText();
        if (ipEable == null)
            return;

        String strIp = ipEable.toString();
        int ipLen = strIp.length();
        if (ipLen == 0)
            return;

        Editable portEable = etServerPort.getText();
        if (portEable == null)
            return;

        String strPort = portEable.toString();
        int portLen = strPort.length();
        if (portLen == 0)
            return;

        // 非服务器模式
        if (hasServer)
            return;

        if (hasTcp) {
            connectRemoteTcpServer(strIp, strPort);
        } else {
            connectRemoteUdpServer(strIp, strPort);
        }
    }

    private void sendTestToTcpClient(long currentTime) {
        connManager.sendTestToTcpClient(new OnMsgSendComplete() {
            @Override
            public void sucess() {
                lastSendTime = currentTime;
            }

            @Override
            public void error() {

            }
        });
    }

    /**
     * view状态还原
     */
    public void restoreAllComponents() {
        tvRec.setText("");
        tvSend.setText("");
        btnConnectServer.setEnabled(false);
        btnDiscAll.setEnabled(false);
        btnDisCurrent.setEnabled(false);
        btnTest.setEnabled(false);
    }

    /**
     * 连接udp服务器
     */
    private void connectRemoteUdpServer(String strIp, String strPort) {

    }

    /**
     * 连接tcp服务器
     */
    private void connectRemoteTcpServer(String strIp, String strPort) {
        connManager.connectRemoteTcpServer(strIp, strPort);
    }
}
