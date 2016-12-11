package com.example.lenovo.available.model;

import java.io.Serializable;

public class Segment implements Serializable {

    private String id;

    private String segId;
    private String segmentDate;
    private String artistID;
    private String venueID;
    private String venueName;
    private String segmentTime;
    private String Accompanists;
    private boolean isFavorite;

    public String getAccompanists() {
        return Accompanists;
    }

    public void setAccompanists(String accompanists) {
        Accompanists = accompanists;
    }


    public String getSegmentTime() {
        return segmentTime;
    }

    public void setSegmentTime(String segmentTime) {
        this.segmentTime = segmentTime;
    }

    public String getSegmentDate() {
        return segmentDate;
    }

    public void setSegmentDate(String segmentDate) {
        this.segmentDate = segmentDate;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSegId() {
        return segId;
    }

    public void setSegId(String segId) {
        this.segId = segId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", segId='" + segId + '\'' +
                ", segmentDate='" + segmentDate + '\'' +
                ", artistID='" + artistID + '\'' +
                ", venueID='" + venueID + '\'' +
                ", isFavorite=" + isFavorite +
                ", venueName='" + venueName + '\'' +
                ", segmentTime='" + segmentTime + '\'' +
                ", Accompanists='" + Accompanists + '\'' +
                '}';
    }
}
