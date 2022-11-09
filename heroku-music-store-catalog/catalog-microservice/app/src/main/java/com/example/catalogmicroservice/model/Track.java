package com.example.catalogmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"handler, hibernateLazyInitializer"})
@Table(name= "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long trackId;
    @Column(name = "album_id")
    private Long albumId;
    private String title;
    @Column(name = "run_time")
    private Long runTime;

    public Track(Long trackId, Long albumId, String title, Long runTime) {
        this.trackId = trackId;
        this.albumId = albumId;
        this.title = title;
        this.runTime = runTime;
    }

    public Track() {
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(trackId, track.trackId) && Objects.equals(albumId, track.albumId) && Objects.equals(title, track.title) && Objects.equals(runTime, track.runTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, albumId, title, runTime);
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", runTime=" + runTime +
                '}';
    }
}
