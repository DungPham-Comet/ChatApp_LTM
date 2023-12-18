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

public class Server extends Thread{
    private int port = 8080;

    private ServerSocket ss = null;

    private Socket sock = null;

    public Server(int port) {
        this.port = port;
    }

    public static void handleClient(Socket sock) throws IOException, SQLException {
        System.out.println("Connection from " + sock.getRemoteSocketAddress());
        BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        String username = input.readLine();
        String password = input.readLine();
        System.out.println(sock.getRemoteSocketAddress()+ " Username: " + username);
        System.out.println(sock.getRemoteSocketAddress() + " Password: " + password);
        User user = new User(username, password);

        UserDAO userDAO = new UserDAO();

        User userVerified = userDAO.verifyUser(user);

        if (userVerified != null) {
            System.out.println("Login successfully");
            // Send login successfully message to client
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println("Login Successfully");
        }else{
            System.out.println("Login Fail");
            // Send login successfully message to client
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println("Login Fail");
        }


    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for connection...");
            ss = new ServerSocket(port);

            while(true) {
                sock = ss.accept();
                new Thread(() -> {
                    try {
                        handleClient(sock);
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

//            sock = ss.accept();
//            System.out.println("Connection from " + sock.getRemoteSocketAddress());
//
//            // Add your server logic here
//            // Read username and password from client
//            String username = readUsername();
//            String password = readPassword();
//            System.out.println("Username: " + username);
//            System.out.println("Password: " + password);
//
//            List<User> users = new ArrayList<>();
//
//            ResultSet rs = UserInforService.getAllUser();
//
//            if (rs != null){
//                while (rs.next()) {
//                    users.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password")));
//                }
//            }
//
//            for (User user : users) {
//                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                    System.out.println("Login successfully");
//                    // Send login successfully message to client
//                    PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
//                    out.println("Login successfully");
//
//                    break;
//                }
//            }
//
        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

//    public String readUsername() {
//        try {
//            BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//            String username = input.readLine();
//            return username;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String readPassword() {
//        try {
//            BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//            String password = input.readLine();
//            return password;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    }
}
