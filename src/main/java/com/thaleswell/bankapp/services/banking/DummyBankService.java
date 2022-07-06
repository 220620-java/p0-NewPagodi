package com.thaleswell.bankapp.services.banking;

import java.text.DecimalFormat;

import com.thaleswell.tui.io.IIO;

public class DummyBankService implements IBankService{

    private IIO io;
    
    public DummyBankService(IIO io)
    {
        this.io = io;
    }
    
    @Override
    public void dispenseCash(double withdrawal) {
        DecimalFormat df = new DecimalFormat("#.00");
        String withdrawalStr =  df.format(withdrawal);
        
        io.sendLine("Dispensing $" + withdrawalStr + ".");
    }

    @Override
    public boolean acceptDeposit(double deposit) {
        io.sendLine("Receiving " + deposit + ".");
        return true;
    }
}
