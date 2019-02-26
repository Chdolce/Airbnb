package com.xykj.demo.Class;

public class CheckDay {
    private int StartDay;
    private int StartMouth;
    private int EndMouth;
    private int EndDay;


    public CheckDay() {
        setEndMouth(12);
        setStartMouth(12);
        setStartDay(22);
        setEndDay(24);
    }

    public int getStartDay() {
        return StartDay;
    }

    public void setStartDay(int startDay) {
        StartDay = startDay;
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

    public int getEndDay() {
        return EndDay;
    }

    public void setEndDay(int endDay) {
        EndDay = endDay;
    }

    public CheckDay ReceveDays(String args) {
        CheckDay checkDay = new CheckDay();
        String N = args;
        String L = "";
        String R = "";
        int day1,mouth1;
        int k = N.length();
        for (int i = 0; i < N.length(); i++) {
            if (N.substring(i, i + 1).equals("-")) {
                L = N.substring(0, i).trim();
                R = N.substring(i + 1, k).trim();

            }

            checkDay.setStartDay(Integer.getInteger(R));
            checkDay.setStartMouth(Integer.getInteger(L));
        }
        return checkDay;
    }
}
