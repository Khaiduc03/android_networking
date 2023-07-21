package com.example.android_networking;

public class WorkerThreadPool implements Runnable{
    private String taskName;

    public WorkerThreadPool(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Bắt đầu thực thi: " + taskName);
        try {
            //Giả lập thời gian xử lý công việc
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Hoàn thành: " + taskName);
    }

}
