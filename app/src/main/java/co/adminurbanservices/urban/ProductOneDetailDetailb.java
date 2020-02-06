package co.adminurbanservices.urban;

class ProductOneDetailDetailb {

    private int id;
    private String message;
    private String username;
    private String image;
    private String ratea;
    private String datea;


    ProductOneDetailDetailb(int id, String message, String username, String image, String ratea, String datea) {
        this.id = id;
        this.message = message;
        this.username = username;
        this.image = image;
        this.datea = datea;
        this.ratea = ratea;

    }

    public int getId() {
        return id;
    }


    String getServicedetail() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return datea;
    }

    public String getRate() {
        return ratea;
    }


}
