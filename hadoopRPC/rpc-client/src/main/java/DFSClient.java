import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author xj
 * @create 2022-09-01 9:53
 **/
public class DFSClient {

    public static void main(String[] args) throws IOException {
        // 通过动态代理去调用
        // import org.apache.hadoop.ipc.RPC
        ClientProtocols proxy = RPC.getProxy(
                ClientProtocols.class, // 客户端协议
                123L, // 协议版本号
                new InetSocketAddress("localhost", 6789),
                new Configuration());

        proxy.mkdir("/opt/workspace");
    }
}
