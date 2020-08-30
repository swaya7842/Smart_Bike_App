package com.example.lironautomotivepvtltd.AdminServiceContacts;

import java.util.List;

public class MyContacts {

    private String ContactName;
    private List<String> ContactNumber;

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public List<String> getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(List<String> contactNumber) {
        ContactNumber = contactNumber;
    }
}
