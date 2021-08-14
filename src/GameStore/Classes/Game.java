package GameStore.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Game {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Platform;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    private Float BuyPrice;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    private Float SellPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String[] Genres;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String[] Modes;

    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date ReleaseDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String[] Developers;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String[] Publishers;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer AvailableCopies;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String ImageLink;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Description;

    public Game() {
    }

    public Game(String name, String platform, Float buyPrice, Float sellPrice, String[] genres, String[] modes, Date releaseDate, String[] developers, String[] publishers, Integer availableCopies, String imageLink, String description) {
        Name = name;
        Platform = platform;
        BuyPrice = buyPrice;
        SellPrice = sellPrice;
        Genres = genres;
        Modes = modes;
        ReleaseDate = releaseDate;
        Developers = developers;
        Publishers = publishers;
        AvailableCopies = availableCopies;
        ImageLink = imageLink;
        Description = description;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Float getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        BuyPrice = buyPrice;
    }

    public Float getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        SellPrice = sellPrice;
    }

    public String[] getGenres() {
        return Genres;
    }

    public void setGenres(String[] genres) {
        Genres = genres;
    }

    public String[] getModes() {
        return Modes;
    }

    public void setModes(String[] modes) {
        Modes = modes;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String[] getDevelopers() {
        return Developers;
    }

    public void setDevelopers(String[] developers) {
        Developers = developers;
    }

    public String[] getPublishers() {
        return Publishers;
    }

    public void setPublishers(String[] publishers) {
        Publishers = publishers;
    }

    public Integer getAvailableCopies() {
        return AvailableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        AvailableCopies = availableCopies;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    @Override
    public String toString() {
        return "Game{" +
                "Name='" + Name + '\'' +
                ", Platform='" + Platform + '\'' +
                ", BuyPrice=" + BuyPrice +
                ", SellPrice=" + SellPrice +
                ", Genres=" + Arrays.toString(Genres) +
                ", Modes=" + Arrays.toString(Modes) +
                ", ReleaseDate=" + ReleaseDate +
                ", Developers=" + Arrays.toString(Developers) +
                ", Publishers=" + Arrays.toString(Publishers) +
                ", AvailableCopies=" + AvailableCopies +
                ", ImageLink='" + ImageLink + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public String listViewDisplay_Game()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return   Name + "  (" + Platform + ")" +
                "\nWe sell for: " + SellPrice + " PLN" +
                "\nWe buy for: " + BuyPrice + " PLN" +
                "\nRelease Date: " + formatter.format(ReleaseDate);
    }
}
