/**
 * @author xj
 * @create 2022-09-01 9:11
 **/
public interface ClientProtocols {
    /**
     * 客户端协议
     */
    //1.
    long versionID = 123L; // 协议版本号
    //2. hadoop fs mkdir ....
    void mkdir(String path);
}
