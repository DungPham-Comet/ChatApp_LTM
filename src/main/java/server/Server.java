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

public class Server extends Thread {
    private int port = 8080;

    private ServerSocket ss = null;

    private static Socket sock = null;

    public Server(int port) {
        this.port = port;
    }

    public static void returnAcceptMsg(String msg) throws IOException {
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        out.println( msg + " accepted");
    }

    public static void handleClient(Socket sock) throws IOException, SQLException {
        System.out.println("Connection from " + sock.getRemoteSocketAddress());
        UserDAO userDAO = new UserDAO();

        while(true){
            BufferedReader flag = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String flagMessage = flag.readLine();

            switch (flagMessage){
                case "login":
                    returnAcceptMsg("login");
                    BufferedReader inputSignIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    String username = inputSignIn.readLine();
                    String password = inputSignIn.readLine();
                    System.out.println(sock.getRemoteSocketAddress() + " Username: " + username);
                    System.out.println(sock.getRemoteSocketAddress() + " Password: " + password);
                    User user = new User(username, password);

                    User userVerified = userDAO.verifyUser(user);

                    if (userVerified != null && !userVerified.isOnline()) {
                        System.out.println("Login successfully");
                        // Send login successfully message to client
                        userDAO.updateToOnline(userVerified.getID());
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        out.println("1");

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
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        out.println("2");
                    } else {
                        System.out.println("Login Fail");
                        // Send login successfully message to client
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        out.println("0");
                    }
                    break;
                case "signup":
                    returnAcceptMsg("signup");
                    BufferedReader inputSignUp = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    //while(true){
                    String usernameSignUp = inputSignUp.readLine();
                    String passwordSignUp = inputSignUp.readLine();

                    System.out.println(sock.getRemoteSocketAddress() + " Username: " + usernameSignUp);
                    System.out.println(sock.getRemoteSocketAddress() + " Password: " + passwordSignUp);

                    if (userDAO.checkDuplicated(usernameSignUp)){
                        System.out.println("Username is duplicated");
                        // Send login successfully message to client
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        out.println("0");
                    } else {
                        User userSignUp = new User(usernameSignUp, passwordSignUp);
                        userDAO.addUserWithUsernamePassword(userSignUp);
                        System.out.println("Sign up successfully");
                        // Send login successfully message to client
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        out.println("1");
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
