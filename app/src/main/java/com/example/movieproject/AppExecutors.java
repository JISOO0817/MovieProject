package com.example.movieproject;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    // pattern

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }



    // 백그라운드 스레드
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    // 연결, 취소,
    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }


}
