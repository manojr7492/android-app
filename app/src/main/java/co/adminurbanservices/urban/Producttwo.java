package co.adminurbanservices.urban;

class Producttwo {

    private int id;
    private String servicename;
    private String start_time;
    private String end_time;
    private String date;



    public Producttwo(int id, String servicename, String start_time, String end_time, String date) {
        this.id = id;
        this.servicename = servicename;
        this.start_time = start_time;
        this.end_time = end_time;
        this.date = date;

    }



    public int getId(){return id;}
    public String getServicename() {
        return servicename;
    }
    public String getStart_time() {
        return start_time;
    }
    public String getEnd_time() {
        return end_time;
    }
    public String getDate() {
        return date;
    }


}


