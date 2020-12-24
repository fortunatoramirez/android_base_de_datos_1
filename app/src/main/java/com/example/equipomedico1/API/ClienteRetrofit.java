package com.example.equipomedico1.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClienteRetrofit {

    private static Retrofit instancia;

    public static Retrofit getInstancia(){
        if(instancia == null)
            /*
            Starting with Android 9 (API level 28), cleartext support is disabled by default.
            Se le agregó android:networkSecurityConfig="@xml/network_security_config" en
            manifests/AndroidManifest.xml:
            --------------------------
                <?xml version="1.0" encoding="utf-8"?>
                <manifest ...>
                    <uses-permission android:name="android.permission.INTERNET" />
                    <application
                        ...
                        android:networkSecurityConfig="@xml/network_security_config"
                        ...>
                        ...
                    </application>
                </manifest>
            --------------------------

             */
            instancia = new Retrofit.Builder().baseUrl("http://201.174.122.201:5001")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        return instancia;
    }
}
