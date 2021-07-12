package GameStore.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Client {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer Id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Address;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String Postal;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String City;

    public Client() {
    }

    public Client(Integer id, String name, String address, String postal, String city) {
        Id = id;
        Name = name;
        Address = address;
        Postal = postal;
        City = city;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostal() {
        return Postal;
    }

    public void setPostal(String postal) {
        Postal = postal;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Postal='" + Postal + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}
