/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class UserAccount {

    private int id;
    private String username;
    private String email;
    private String phone;
    private String gender;
    private String image;
    private String password;

    public UserAccount() {
    }

    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserAccount(String username, String email, String phone, String gender, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.image = image;
    }

    public UserAccount(String username, String email, String phone, String gender, String image, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.image = image;
        this.password = password;
    }

    public UserAccount(String username, String gender, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "id=" + id + ", username=" + username + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", image=" + image + ", password=" + password + '}';
    }

}
