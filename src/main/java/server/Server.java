package server;

import models.User;
import server.dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static client.utils.HandleRecv.recvHeader;

public class Server extends Thread {
    private int port = 8080;

    private Socket sock = null;
    private ServerSocket ss = null;

    public Server(int port) {
        this.port = port;
    }

    public static void returnAcceptMsg(Socket sock, String msg) throws IOException {
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        out.println( msg + " accepted");
    }

    public static void handleClient(Socket sock) throws IOException, SQLException {
        System.out.println("Connection from " + sock.getRemoteSocketAddress());
        UserDAO userDAO = new UserDAO();

        while(true){
            BufferedReader flag = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String combinedMsg = flag.readLine();
            String flagMessage = recvHeader(combinedMsg);

            switch (flagMessage){
                case "login":
                    String username = combinedMsg.split(";")[1];
                    String password = combinedMsg.split(";")[2];

                    System.out.println(sock.getRemoteSocketAddress() + " Username Sign In: " + username);
                    System.out.println(sock.getRemoteSocketAddress() + " Password Sign In: " + password);
                    User user = new User(username, password);

                    User userVerified = userDAO.verifyUser(user);
                    PrintWriter outLogin = new PrintWriter(sock.getOutputStream(), true);

                    if (userVerified != null && !userVerified.isOnline()) {
                        System.out.println("Login successfully");
                        // Send login successfully message to client
                        userDAO.updateToOnline(userVerified.getID());
                        outLogin.println("login:" + ";" +"1");

                        BufferedReader inputMessage = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        String message;
                        message = inputMessage.readLine();

                        switch (message) {
                            case "logout":
                                handleLogOut(sock, userVerified, userDAO);
                                break;
                        }
                    } else if (userVerified != null && userVerified.isOnline()) {
                        System.out.println("Account is being logged in from other device");
                        // Send login successfully message to client
                        outLogin.println("login:" + ";" +"2");
                    } else {
                        System.out.println("Login Fail - Wrong username or password");
                        // Send login successfully message to client
                        outLogin.println("login:" + ";" +"0");
                    }
                    break;
                case "signup":

                    String usernameSignUp = combinedMsg.split(";")[1];
                    String passwordSignUp = combinedMsg.split(";")[2];

                    System.out.println(sock.getRemoteSocketAddress() + " Username Sign Up: " + usernameSignUp);
                    System.out.println(sock.getRemoteSocketAddress() + " Password Sign Up: " + passwordSignUp);
                    PrintWriter outSignup = new PrintWriter(sock.getOutputStream(), true);

                    if (userDAO.checkDuplicated(usernameSignUp)){
                        System.out.println("Username is duplicated");
                        outSignup.println("signup:" + ";" +"0");
                    } else {
                        User userSignUp = new User(usernameSignUp, passwordSignUp);
                        userDAO.addUserWithUsernamePassword(userSignUp);
                        System.out.println("Sign up successfully");
                        outSignup.println("signup:" + ";" +"1");
                    }
                    //}
                    break;
            }
        }
    }

    public static void handleLogOut(Socket sock, User user, UserDAO userDAO) throws IOException, SQLException {
        userDAO.updateToOffline(user.getID());
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for connection...");
            ss = new ServerSocket(port);

            while (true) {
                sock = ss.accept();
                new Thread(() -> {
                    try {
                        handleClient(sock);
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
