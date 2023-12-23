package client.utils;

public class HandleRecv {
    public static String recvHeader(String msg) {
        String[] msgArr = msg.split(";");
        String flag = msgArr[0];
        String content = msgArr[1];

        return flag;
    }

    public static String recvContent(String msg) {
        String[] msgArr = msg.split(";");
        String flag = msgArr[0];
        String content = msgArr[1];

        return content;
    }
}
