package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.exceptions.UsernameAlreadyExistsException;
import com.thaleswell.bankapp.models.User;
import com.thaleswell.bankapp.services.data.IUserService;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

enum UserReason {
    LOGIN,
    CREATE_ACCOUNT;
}

class GetPasswordState extends BankAppState {
    
    private UserReason reason;
    private String username;
    private String password;
    private IState next;
    
    GetPasswordState(IIO io, UserReason reason, String username) {
        super(io);
        this.reason = reason;
        this.username = username;
    }
    
    @Override
    public String getPrompt() {
        return "password: ";
    }
    
    @Override
    public void processInput(String input) {
        password = input;
    }

    @Override
    public void performStateTask() {
        IUserService userService = getDataServiceBundle().getUserService();
        User user;
        if ( reason == UserReason.LOGIN ) {
            user = userService.logIn(username,password);
            
            if ( user == null ) {
                getIO().sendLine("Invalid username or password");
            }
            else {
                getIO().sendLine("Login Successful");
            }
        }
        else {
            try {
                user = userService.registerUser(username, password);
                getIO().sendLine("Account created.");
            }
            catch ( UsernameAlreadyExistsException e ) {
                getIO().sendLine("Username already exists.");
                user = null;
            }
        }
        
        if ( user != null ) {
            BankAppState.setUser(user);
            next = new AccountsMenuState(getIO());
        }
        else {
            next = new StartState(getIO());
        }
    }

    @Override
    public IState getNext() {
        return next;
    }
}

class GetUsernameState extends BankAppState {

    private UserReason reason;
    private IState next;
    
    GetUsernameState(IIO io, UserReason reason) {
        super(io);
        this.reason = reason;
    }
    
    @Override
    public String getPrompt() {
        return "username: ";
    }

    @Override
    public void processInput(String input) {
        next = new GetPasswordState(getIO(), reason, input);
    }

    @Override
    public IState getNext() {
        return next;
    }
    
}
