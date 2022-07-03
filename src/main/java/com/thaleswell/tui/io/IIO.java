package com.thaleswell.tui.io;

public interface IIO {
    public void sendLine(String msg);
    
    public void send(String msg);
    
    public String read();
}
