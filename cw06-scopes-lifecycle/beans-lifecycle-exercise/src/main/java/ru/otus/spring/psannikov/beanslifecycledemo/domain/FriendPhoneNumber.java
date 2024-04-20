package ru.otus.spring.psannikov.beanslifecycledemo.domain;

public class FriendPhoneNumber extends PhoneNumber {
    @Override
    public String getOwnerName() {
        return "Друг";
    }
}
