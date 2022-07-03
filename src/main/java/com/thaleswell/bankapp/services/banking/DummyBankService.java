package com.thaleswell.bankapp.services.banking;

import com.thaleswell.tui.io.IIO;

public class DummyBankService implements IBankService{

    private IIO io;
    
    public DummyBankService(IIO io)
    {
        this.io = io;
    }
    
    @Override
    public void dispenseCash(double withdrawal) {
        io.sendLine("Dispensing " + withdrawal + ".");
    }

    @Override
    public boolean acceptDeposit(double deposit) {
        io.sendLine("Receiving " + deposit + ".");
        return true;
    }
}
