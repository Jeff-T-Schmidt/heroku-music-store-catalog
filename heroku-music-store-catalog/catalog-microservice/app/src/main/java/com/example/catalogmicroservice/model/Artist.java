package com.example.catalogmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"handler, hibernateLazyInitializer"})
@Table(name= "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="artist_id")
    private Long artistId;
    private String name;
    private String instagram;
    private String twitter;

    public Artist(Long artistId, String name, String instagram, String twitter) {
        this.artistId = artistId;
        this.name = name;
        this.instagram = instagram;
        this.twitter = twitter;
    }

    public Artist() {
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(artistId, artist.artistId) && Objects.equals(name, artist.name) && Objects.equals(instagram, artist.instagram) && Objects.equals(twitter, artist.twitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, name, instagram, twitter);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", instagram='" + instagram + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
