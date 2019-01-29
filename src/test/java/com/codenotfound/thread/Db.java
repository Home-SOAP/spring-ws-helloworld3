package com.codenotfound.thread;

import java.time.LocalDateTime;
import static com.codenotfound.thread.ThreadExample1.DF;

class Db {
    private LocalDateTime date1;
    private LocalDateTime date2;

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

    @Override
    public String toString() {
        return "{" +
                "date1=" + date1.format(DF) +
                ", date2=" + date2.format(DF) +
                '}';
    }
}
