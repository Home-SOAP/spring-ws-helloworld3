package com.codenotfound.thread2;

import java.time.LocalDateTime;

class Db {
    private LocalDateTime date1;
    private LocalDateTime date2;
    private LocalDateTime date3;

    public LocalDateTime getDate1() {
        return date1;
    }

    public void setDate1(LocalDateTime date1) {
        this.date1 = date1;
    }

    public LocalDateTime getDate2() {
        return date2;
    }

    public void setDate2(LocalDateTime date2) {
        this.date2 = date2;
    }

    public LocalDateTime getDate3() {
        return date3;
    }

    public void setDate3(LocalDateTime date3) {
        this.date3 = date3;
    }

    @Override
    public String toString() {
        return "{" +
                "date1=" + date1.format(ThreadExample1.DF) +
                ", date2=" + date2.format(ThreadExample1.DF) +
                ", date3=" + date3.format(ThreadExample1.DF) +
                '}';
    }
}
