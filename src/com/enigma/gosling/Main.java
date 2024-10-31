package com.enigma.gosling;

import com.enigma.gosling.service.ViewData;
import com.enigma.gosling.service.impl.ViewDataImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Java Database");

        ViewData viewData = new ViewDataImpl();
        viewData.display();
    }
}
