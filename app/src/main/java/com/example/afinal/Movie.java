package com.example.afinal;


import java.util.List;

public class Movie {

    private String title , poster , made;
    private Double rating;


    private List<String> cinemas;

    public Movie(String title , String poster , Double rating,String made, List<String> cinemas){
        this.title = title;
        this.poster = poster;
        this.rating = rating;
        this.made = made;
        this.cinemas = cinemas;
    }

    public String getMade(){
        return  made ;
    }

    public List<String> getCinemas(){
        return this.cinemas;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public Double getRating() {
        return rating;
    }
}