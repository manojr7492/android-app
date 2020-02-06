package co.adminurbanservices.urban;

class ProductOneDetail {

    private int id;
    private String service;
    private String image;

    ProductOneDetail(int id, String service, String image) {
        this.id = id;
        this.service = service;
        this.image = image;
    }

    public int getId() {
        return id;
    }



    String getServicename() {
        return service;
    }


    public String getImage() {
        return image;
    }
}
