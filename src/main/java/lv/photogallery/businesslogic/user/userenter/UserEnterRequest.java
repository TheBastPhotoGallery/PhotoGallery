package lv.photogallery.businesslogic.user.userenter;

public class UserEnterRequest {
    private String email;
    private String password;

    public UserEnterRequest (String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
