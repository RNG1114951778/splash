package com.example.splash;


import android.os.CountDownTimer;
import android.os.Handler;

public class CustomeCountDownTimer implements Runnable {

    private  int time;
    private  int countdowntime;
    private IcountDownHandler CountDownHandler = null;
    private final Handler handler;
    private boolean isRun;
    public CustomeCountDownTimer(int time,IcountDownHandler CountDownHandler)
    {

        this.time = time;
        this.countdowntime=time;
        this.CountDownHandler = CountDownHandler;
        handler = new Handler();


    }


    //具体实现
    @Override
    public void run() {
        if (isRun){
            if(CountDownHandler!=null){
                CountDownHandler.onTicker(countdowntime);
            }

            if(countdowntime == 0) {
                cancel();
                if (CountDownHandler != null) {
                    CountDownHandler.onFinish();
                }
            }
                else
                {
                    countdowntime = time--;
                    handler.postDelayed(this,1000);
                }
            }
        }



    //开始倒计时
    public void start(){
        isRun = true;
        handler.post(this);
    }


//跳出循环，终止
    public void cancel(){
        isRun = false;
        handler.removeCallbacks(this);
    }
//观察者回调借口  （IOC 数据回调）
    public interface IcountDownHandler{

        //倒计时回调
        void onTicker(int time);

        //完成时回调
        void onFinish();
    }

}
