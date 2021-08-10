package GameStore.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.image.Image;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Game {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer Id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Name;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT)
    private Float Cost;

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

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer RentedCopies;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String ImageLink;

    public Game() {
    }

    public Game(Integer id, String name, Float cost, String[] genres, String[] modes, Date releaseDate, String[] developers, String[] publishers, Integer availableCopies, Integer rentedCopies, String imageLink) {
        Id = id;
        Name = name;
        Cost = cost;
        Genres = genres;
        Modes = modes;
        ReleaseDate = releaseDate;
        Developers = developers;
        Publishers = publishers;
        AvailableCopies = availableCopies;
        RentedCopies = rentedCopies;
        ImageLink = imageLink;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Float getCost() {
        return Cost;
    }

    public void setCost(Float cost) {
        Cost = cost;
    }

    public Integer getAvailableCopies() {
        return AvailableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        AvailableCopies = availableCopies;
    }

    public Integer getRentedCopies() {
        return RentedCopies;
    }

    public void setRentedCopies(Integer rentedCopies) {
        RentedCopies = rentedCopies;
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

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getImageLink() {
        return ImageLink;
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

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    @Override
    public String toString() {
        return "Game{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Cost=" + Cost +
                ", Genres=" + Arrays.toString(Genres) +
                ", Modes=" + Arrays.toString(Modes) +
                ", ReleaseDate=" + ReleaseDate +
                ", Developers=" + Arrays.toString(Developers) +
                ", Publishers=" + Arrays.toString(Publishers) +
                ", AvailableCopies=" + AvailableCopies +
                ", RentedCopies=" + RentedCopies +
                ", ImageLink='" + ImageLink + '\'' +
                '}';
    }

    public String listViewDisplay_Game()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "Title: " + Name +
                "\nPrice: " + Cost +
                "\nGenres: " + Arrays.toString(Genres) +
                "\nModes: " + Arrays.toString(Modes) +
                "\nRelease Date: " + formatter.format(ReleaseDate) +
                "\nDevelopers: " + Arrays.toString(Developers) +
                "\nPublishers: " + Arrays.toString(Publishers) +
                "\n\nAvailable Copies: " + AvailableCopies +
                "\nRented Copies: " + RentedCopies;
    }
}
