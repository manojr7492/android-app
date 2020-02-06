package co.adminurbanservices.urban;


import com.google.gson.annotations.SerializedName;

/**
 * Created by haerul on 17/03/18.
 */

public class Contact {

    @SerializedName("id") private int Id;
    @SerializedName("servicename") private String Name;
    @SerializedName("image") private String Email;

    public Contact(int id, String name, String email) {
        Id = id;
        Name = name;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    String getServicename() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}
