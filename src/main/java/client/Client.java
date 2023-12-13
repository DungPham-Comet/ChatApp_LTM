package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private Socket sock = null;

    private String ipAddr;

    private int port;

    public Client(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
        System.out.println("Client started!");
        try {
            sock = new Socket(ipAddr, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void run(){
//        try {
//            System.out.println("Client started!");
//            sock = new Socket(ipAddr, port);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void sendUsername(String username) {
    	try {
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPassword(String password) {
        try {
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println(password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readMessage() {
    	try {
            BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String message = input.readLine();
            return message;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
