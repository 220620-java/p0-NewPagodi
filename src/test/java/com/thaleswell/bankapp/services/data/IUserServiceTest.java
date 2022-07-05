package com.thaleswell.bankapp.services.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thaleswell.bankapp.data.UserDAO;
import com.thaleswell.bankapp.data.UserPostgres;
import com.thaleswell.bankapp.exceptions.UsernameAlreadyExistsException;
import com.thaleswell.bankapp.models.User;

@ExtendWith(MockitoExtension.class)
class IUserServiceTest {

    @InjectMocks
    private IUserService userServ = new UserServicePostgres();
    
    @Mock
    private UserDAO userDao;
    
    @BeforeAll
    public static void setUp() {
        System.out.println("======BEGIN IUserServiceTests=======");
    }
    
    @Test
    void registerNewUser() {
        User mockUser = new User("username","password");

        // Ensure that the DAO will tell us the username is available
        // and give back a non null object.
        Mockito.when(userDao.findByUsername("username")).thenReturn(null);
        Mockito.when(userDao.create(any(User.class))).thenReturn(mockUser);

        User returnedUser = null;
        try {
            returnedUser = userServ.registerUser("username","password");
        }
        catch (UsernameAlreadyExistsException e) {
        }

        // assertion
        assertNotNull(returnedUser);
    }
    
    @Test
    public void registerUsernameAlreadyExists() {
        User mockUser = new User();
        Mockito.when(userDao.findByUsername("username")).thenReturn(mockUser);

        // when asserting an exception was thrown, the structure is a little
        // different - you set up a lambda function that JUnit will call and
        // check for an exception
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            userServ.registerUser("username","password");
        });
    }
}
