package com.example.syl.grmr.Constructor;

/*
매칭 탭에 들어갈 때, 화면 리스트에 띄울 목록
* */

    public class RecyclerItem {

        private String city;
        private String departureDate;
        private String homecomingDate;


        public RecyclerItem (String city, String departureDate, String homecomingDate){
            this.city = city;
            this.departureDate = departureDate;
            this.homecomingDate = homecomingDate;
        }

        public String getCity() {
            return city;
        }
        public String getDepartureDate(){
            return departureDate;
        }
        public String getHomecomingDate(){
            return homecomingDate;
        }

    }


