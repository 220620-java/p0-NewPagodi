package com.thaleswell.tui.states;

public interface IState {
    public boolean isExitState();
    
    public void prepare();
    
    public String getMenu();
    
    public String getPrompt();
    
    public void processInput(String input);
    
    public void performStateTask();
    
    public IState getNext();
}
