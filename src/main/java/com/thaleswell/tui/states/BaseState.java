package com.thaleswell.tui.states;

import com.thaleswell.tui.io.IIO;

abstract public class BaseState implements IState{
    private IIO io;
    
    public BaseState(IIO io) {
        this.io = io;
    }
    
    @Override
    public boolean isExitState() {
        return false;
    }

    @Override
    public String getMenu() {
        return "";
    }
    
    @Override
    public String getPrompt() {
        return "Enter a selection from the options above: ";
    }

    @Override
    public void processInput(String input) {
    }

    @Override
    public void performStateTask() {
    }

    @Override
    abstract public IState getNext();
    
    public IIO getIO() {
        return io;
    }
}
