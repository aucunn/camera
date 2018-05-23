package com.example.aucun.camera;

import java.util.ArrayList;

public class DateDic {
    private ArrayList<String> sdate;
    private ArrayList<Integer> count;
    private int size = 0;
    private int div;

    public DateDic(int div){
        sdate = new ArrayList<String>();
        count = new ArrayList<Integer>();
        this.div = div;
    }

    public void add(String date){
        String tmp = "";
        String dateString[] = {"년 ", "월 ", "일 ", ":", ":", ":"};

        if (div <= 3) {
            String tmp2[] = date.split(" ")[0].split("-");
            for (int i = 0; i < div; i++){
                tmp = tmp + tmp2[i] + dateString[i];
            }
        } else {
            String tmp2[] = date.split(" ");
            for (int i = 0; i < 3; i++){
                tmp = tmp + tmp2[0].split("-")[i] + dateString[i];
            }
            tmp = tmp + " ";
            tmp2 = date.split(" ")[1].split(":");
            for (int i = 3; i < div; i++){
                tmp = tmp + dateString[i + 3] + tmp2[i - 3];
            }
        }

        if (this.sdate.contains(tmp))//리스트에 있는 날짜인가?
        {
            int tmpi = sdate.indexOf(tmp);//날짜 인덱스
            count.set(tmpi, count.get(tmpi) + 1);//날짜의 값 1증가
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
