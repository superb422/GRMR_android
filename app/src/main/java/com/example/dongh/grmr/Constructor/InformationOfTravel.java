package com.example.dongh.grmr.Constructor;

/**
 * 여행등록 메인화면에서 이전에 등록했던 여행 정보들 가져와서 카드에 넣기 위함
 */
public class InformationOfTravel {

    private String city;
    private String departureDate;
    private String homeComingDate;
    private String peopleNum;
    private String country;

    public void setCity(String city){
        this.city = city;
    }

    public void setDepartureDate(String departureDate){
        this.departureDate = departureDate;
    }

    public void setHomeComingDate(String homeComingDate){
        this.homeComingDate = homeComingDate;
    }

    public void setPeopleNum(String peopleNum){
        this.peopleNum = peopleNum;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getHomeComingDate() {
        return homeComingDate;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public String getCountry() {
        return country;
    }

}

