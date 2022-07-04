package com.thaleswell.tui;

import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

public class TUI {
    
    protected IIO io;
    protected IState state;
    
    public TUI(IIO io, IState startState) {
        this.io = io;
        this.state = startState;
    }
    
    public void runLoop() {
        String input;

        while ( !state.isExitState() ) {
            state.prepare();
            io.send(state.getMenu());
            io.send(state.getPrompt());
            input = io.read();

            state.processInput(input);
            state.performStateTask();
            state.teardown();
            state = state.getNext();
        }

        // The exit state never actually ran, so call it's teardown method to
        // perform any final cleanup.
        state.teardown();
    }
    
    protected IIO getIO() {
        return io;
    }
}