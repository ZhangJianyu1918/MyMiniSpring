package org.spring.test.service;

public class WorldServiceWithExceptionImpl implements WorldService{
    @Override
    public void explode() {
        System.out.println("This is the world service with an Exception");
        throw new RuntimeException();
    }
}
