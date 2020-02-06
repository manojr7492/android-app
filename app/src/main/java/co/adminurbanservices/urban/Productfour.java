package co.adminurbanservices.urban;

class Productfour {


    private int id;
    private String service;
    private String image;

    Productfour(int id, String service, String image) {
        this.id = id;
        this.service = service;
        this.image = image;
    }

    public int getId() {
        return id;
    }


    String getShortdesc() {
        return service;
    }


    public String getImage() {
        return image;
    }
}
