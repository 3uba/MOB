package com.example.cw2;

import android.graphics.Bitmap;
import android.net.Uri;

public class Contact {
    public String id;
    public String name;
    public String mobileNumber;
    public String email;

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAll() {
        return String.format("%s %s %s %s", id, name, mobileNumber, email);
    }
}

