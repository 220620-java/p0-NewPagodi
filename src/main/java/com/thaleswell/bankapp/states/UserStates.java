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

class RetypePasswordState  extends BankAppState {
    private User user;
    private IState next;
    
    RetypePasswordState(IIO io, User user) {
        super(io);
        this.user = user;
    }
    
    @Override
    public String getPrompt() {
        return "retype password: ";
    }
    
    @Override
    public void processInput(String input) {
        if ( input.equals(user.getPassword()) ) {
            // If the passwords match, we can create a new account with the
            // given username and password.
            IUserService userService = getDataServiceBundle().getUserService();
            
            try {
                user = userService.registerUser(user.getUsername(), input);
                BankAppState.setUser(user);
                next = new AccountsMenuState(getIO());
            }
            catch(UsernameAlreadyExistsException e) {
                getIO().sendLine("Username already exists.");
                next = new StartState(getIO());
            }
        }
        else {
            getIO().sendLine("Passwords do not match. Unable to continue.");
            next = new StartState(getIO());
        }
    }
    
    @Override
    public IState getNext() {
        return next;
    }
}

class GetPasswordState extends BankAppState {
    
    private UserReason reason;
    User user;
    String enteredPassword;
    private IState next;
    
    GetPasswordState(IIO io, UserReason reason, User user) {
        super(io);
        this.reason = reason;
        this.user = user;
    }
    
    @Override
    public String getPrompt() {
        return "password: ";
    }
    
    @Override
    public void processInput(String input) {
        enteredPassword = input;
    }

    @Override
    public void performStateTask() {
        IUserService userService = getDataServiceBundle().getUserService();

        if ( reason == UserReason.LOGIN ) {
            // If we're trying to login, verify that the given password matches
            // the user's password.
            if ( enteredPassword.equals(user.getPassword()) ) {
                getIO().sendLine("Login Successful");
                next = new AccountsMenuState(getIO());
                BankAppState.setUser(user);
            }
            else {
                getIO().sendLine("Password is incorrect.");
                next = new StartState(getIO());
            }
        }
        else {
            // We're trying to create an account. Move to RetypePasswordState
            user.setPassword(enteredPassword);
            next = new RetypePasswordState(getIO(), user);
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
    private String username;
    
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
        username = input;
    }
    
    @Override
    public void performStateTask() {
        IUserService userService = getDataServiceBundle().getUserService();
        User user = userService.findByUsername(username);

        if ( reason == UserReason.CREATE_ACCOUNT ) {
            // If we're creating an account, we want user to be null since
            // that indicates there is no existing user with that name.
            if ( user == null ) {
                user = new User(username, "");
                next = new GetPasswordState(getIO(), reason, user);
            }
            else {
                getIO().sendLine("Sorry, that username is taken.");
                next = new StartState(getIO());
            }
        }
        else {
            // On the other hand, if we're trying to login and user is null,
            // then there is no user with that name.
            if ( user == null ) {
                getIO().sendLine("Invalid username.");
                next = new StartState(getIO());
            }
            else {
                next = new GetPasswordState(getIO(), reason, user);
            }
        }
    }

    @Override
    public IState getNext() {
        return next;
    }
    
}
