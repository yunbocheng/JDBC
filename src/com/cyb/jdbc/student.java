package com.cyb.jdbc;

import com.mysql.jdbc.*;
import java.sql.Driver;
public class student {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
