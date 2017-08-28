// IClientCallback.aidl
package com.davis.aidl;

// Declare any non-default types here with import statements

interface IClientCallback {
    void onLogin(String name);
    void onLogout(String name);
}
