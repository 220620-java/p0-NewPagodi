package com.thaleswell.bankapp.states;

import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.BaseState;

abstract public class BankAppState  extends BaseState{

    public BankAppState(IIO io) {
        super(io);
    }
}
