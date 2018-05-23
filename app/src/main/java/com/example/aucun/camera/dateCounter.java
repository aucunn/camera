package com.example.aucun.camera;

public class dateCounter {
    static int numberOfDateVarious = 0;                                                             //날짜가 몇개인가??
    int dateNum = 0;                                                                                //이 날짜가 몇개인가??
    int numberOfsequense = 0;                                                                      //몇번째 날짜인가??
    boolean haveSon= false;                                                                        //다음 날짜가 있는가??
    String ymd_date = "";                                                                                //날짜가 들어가는 스트링
    dateCounter nextDate;                                                                           //다음 날짜 인스턴스, new로 구현은 안됨


    int putDate(String DB_date, dateCounter mycounter){                                            //날짜넣는 메소드

        if(numberOfDateVarious == 0) {
            numberOfDateVarious++;
            this.ymd_date = DB_date;
            return 0;
        }

        if(DB_date.equals(this.ymd_date)){
            this.dateNum++;
            return 0;

        }
        else if (!DB_date.equals(this.ymd_date) && this.haveSon == false){
            dateCounter nextDate = new dateCounter();
            numberOfDateVarious++;
            nextDate.numberOfsequense = numberOfDateVarious;
            nextDate.dateNum++;
            nextDate.ymd_date = DB_date;
            this.haveSon = true;
            return 0;
        }

        else if (haveSon == true){
            putDate(DB_date, nextDate);
            return 0;

        }

    return 0;

    }



    String getYmd_date(int num){                                                                    //날짜 출력 메소드, 이게 쓰일까??
        return this.ymd_date;
    }

    int getDateNum(int num){                                                                        //몇번째 날짜인지로 날짜 횟수 구하기, 이게 쓰일까??
        return this.dateNum;
    }

    int getDateNum(String DB_date, dateCounter mycounter){                                          //날짜 받아서 날짜 횟수 구하기, 이게 쓰이겠지
        if(DB_date == this.ymd_date) return this.dateNum;
        else if (DB_date != this.ymd_date && this.haveSon){
            getDateNum(DB_date, nextDate);
        }

        return -1;
    }

    int getNumberOfDateVarious(){                                                                   //날짜 종류 반환 메소드
        return numberOfDateVarious;
    }
}
