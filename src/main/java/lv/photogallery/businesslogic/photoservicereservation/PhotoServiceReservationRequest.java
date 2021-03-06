package lv.photogallery.businesslogic.photoservicereservation;

public class PhotoServiceReservationRequest {
    private String service;
    private String dateTime;
    private String email;

    public PhotoServiceReservationRequest(String service, String dateTime, String email) {
        this.service= service;
        this.dateTime= dateTime.trim();
        this.email = email;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
