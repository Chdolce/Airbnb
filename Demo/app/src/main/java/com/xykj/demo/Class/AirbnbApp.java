package com.xykj.demo.Class;

import android.app.Application;
import android.content.ContentProvider;
import android.os.Build;

public class AirbnbApp extends Application {
    //已登录的用户信息
    private int loginUser_id;
    private String loginUser_img;
    private String loginUser_name;
    private int longinUser_num;
    private int StartDay;
    private int StartMouth;
    private int EndMouth;
    private int EndDay;
    private int loginUser = 0;
    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     *
     * <p>Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.</p>
     *
     * <p>If you override this method, be sure to call {@code super.onCreate()}.</p>
     *
     * <p class="note">Be aware that direct boot may also affect callback order on
     * Android {@link Build.VERSION_CODES#N} and later devices.
     * Until the user unlocks the device, only direct boot aware components are
     * allowed to run. You should consider that all direct boot unaware
     * components, including such {@link ContentProvider}, are
     * disabled until user unlock happens, especially when component callback
     * order matters.</p>
     */
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public AirbnbApp() {
    }

    public int getLoginUser_id() {
        return loginUser_id;
    }

    public void setLoginUser_id(int loginUser_id) {
        this.loginUser_id = loginUser_id;
    }

    public String getLoginUser_img() {
        return loginUser_img;
    }

    public void setLoginUser_img(String loginUser_img) {
        this.loginUser_img = loginUser_img;
    }

    public String getLoginUser_name() {
        return loginUser_name;
    }

    public void setLoginUser_name(String loginUser_name) {
        this.loginUser_name = loginUser_name;
    }

    public int getLonginUser_num() {
        return longinUser_num;
    }

    public void setLonginUser_num(int longinUser_num) {
        this.longinUser_num = longinUser_num;
    }

    public int getStartDay() {
        return StartDay;
    }

    public void setStartDay(int startDay) {
        StartDay = startDay;
    }

    public int getEndDay() {
        return EndDay;
    }

    public void setEndDay(int endDay) {
        EndDay = endDay;
    }

    public int getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(int loginUser) {
        this.loginUser = loginUser;
    }

    public int getStartMouth() {
        return StartMouth;
    }

    public void setStartMouth(int startMouth) {
        StartMouth = startMouth;
    }

    public int getEndMouth() {
        return EndMouth;
    }

    public void setEndMouth(int endMouth) {
        EndMouth = endMouth;
    }

    public AirbnbApp(int loginUser_id, String loginUser_img, String loginUser_name) {
        this.loginUser_id = loginUser_id;
        this.loginUser_img = loginUser_img;
        this.loginUser_name = loginUser_name;
    }


}
