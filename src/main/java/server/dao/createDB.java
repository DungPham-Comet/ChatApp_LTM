package server.dao;

import models.User;

public class createDB {
    public static void main(String[] args) {
        User u = new User("Hung", "123", "HungND", "url");
        UserDAO userDao = new UserDAO();
        userDao.addUser(u);
    }
}
