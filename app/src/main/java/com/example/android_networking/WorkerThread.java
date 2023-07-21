package com.example.android_networking;

public class WorkerThread extends Thread{
    private String threadName;

    public WorkerThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Bắt đầu thực thi: " + threadName);
        try {
            //Giả lập thời gian xử lý công việc
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Hoàn thành: " + threadName);
    }

}
