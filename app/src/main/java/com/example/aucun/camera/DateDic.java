package com.example.aucun.camera;

import java.util.ArrayList;

public class DateDic {
    private ArrayList<String> sdate;
    private ArrayList<Integer> count;
    private int size = 0;
    private int div;

    public DateDic(int div){
        this.div = div;
    }

    public void add(String date){
        String tmp;
        if (div <= 3) {
            tmp = date.split("-")[div - 1];
        } else {
            tmp = date.split(" ")[1].split(":")[div - 4];
        }

        if (this.sdate.contains(tmp))//리스트에 있는 날짜인가?
        {
            int tmpi = sdate.indexOf(tmp);//날짜 인덱스
            sdate.set(tmpi, sdate.get(tmpi) + 1);//날짜의 값 1증가
        } else {
            sdate.add(tmp);//날짜 추가
            count.add(1);
            size++;
        }

    }

    public String getDate(int index){
        return sdate.get(index);
    }
    public int getCount(int index){
        return count.get(index);
    }

    public int getSize(){
        return this.size;
    }
}
