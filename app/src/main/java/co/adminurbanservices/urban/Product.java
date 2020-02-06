package co.adminurbanservices.urban;

class Product {

    private int id;
    private String service;
    private String image;

    Product(int id, String service, String image) {
        this.id = id;
        this.service = service;
        this.image = image;
    }

    public int getId() {
        return id;
    }



    public String getService() {
        return service;
    }


    public String getImage() {
        return image;
    }
}
