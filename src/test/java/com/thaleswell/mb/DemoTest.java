package com.thaleswell.mb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoTest {

    @Test
    void test() {
        Demo demo = new Demo();
        
        System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertTrue(demo.getBool());
    }
}
