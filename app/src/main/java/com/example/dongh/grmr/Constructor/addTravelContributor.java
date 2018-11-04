package com.example.dongh.grmr.Constructor;

import android.graphics.Bitmap;

public class addTravelContributor {

    public final String title;
    public final Bitmap imagePath;
    public final String destination;
    public final String startDate;
    public final String endDate;

    public addTravelContributor(String title, Bitmap imagePath, String destination, String startDate, String endDate)

    {
        this.title = title;
        this.imagePath = imagePath;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

}
