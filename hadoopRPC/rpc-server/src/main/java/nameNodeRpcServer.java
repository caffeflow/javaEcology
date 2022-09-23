import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @author xj
 * @create 2022-09-01 9:13
 **/
public class nameNodeRpcServer implements ClientProtocols {
    //client调用 ---> server执行: mkdir方法
    @Override
    public void mkdir(String path) {
        System.out.println("hadoop fs mkdir: " + path);
    }

    // 启动服务
    public static void main(String[] args) throws IOException {
        // import org.apache.hadoop.ipc.RPC
        // import org.apache.hadoop.conf.Configuration
        RPC.Builder builder = new RPC.Builder(new Configuration())
                .setBindAddress("localhost")
                .setPort(6789)
                .setProtocol(ClientProtocols.class) // 定义当前RPC协议
                .setInstance(new nameNodeRpcServer());// 定义当前RPC实例
        // 创建hadoop rpc服务
        RPC.Server server = builder.build();
        server.start();
    }
}
