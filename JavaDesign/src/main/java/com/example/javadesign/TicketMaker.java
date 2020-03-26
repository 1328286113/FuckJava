package com.example.javadesign;

public class TicketMaker {
    private static int ticket = 1000;
    public int getNextTicketNumber(){
        return ticket++;
    }
    public static int getInstance() {
        return ticket;
    }
}
