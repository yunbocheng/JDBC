package com.cyb.jdbc;

import java.util.ResourceBundle;
import java.lang.*;
public class javaPerson {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        jdbc jdbc1 = new mysql();
        jdbc1.export();
    }
}
