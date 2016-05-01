package getstrava.entities.athlete;

import getstrava.entities.club.Club;
import getstrava.entities.gear.Gear;

import java.util.List;

/**
 * Created by roberto on 12/26/13.
 */
public class Athlete {


    private int id;
    private String resource_state;
    private String firstname;
    private String lastname;
    private String profile_medium;
    private String profile;
    private String city;
    private String state;
    private String sex;
    private String friend;
    private String follower;
    private boolean premium;
    private String created_at;
    private String updated_at;
    private boolean approve_followers;
    private int follower_count;
    private int friend_count;
    private int mutual_friend_count;
    private String date_preference;
    private String measurement_preference;
    private String email;
    private int ftp;
    private List<Club> clubs;
    private List<Gear> bikes;
    private List<Gear> shoes;

    @Override
    public String toString() {
        return firstname +" "+ lastname;
    }

    /**
     *
     * @param id
     */
    public Athlete(int id) {
        this.id = id;
    }

    /**
     *
     */
    public Athlete() {
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getResource_state() {
        return resource_state;
    }

    /**
     *
     * @param resource_state
     */
    public void setResource_state(String resource_state) {
        this.resource_state = resource_state;
    }

    /**
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     */
    public String getProfile_medium() {
        return profile_medium;
    }

    /**
     *
     * @param profile_medium
     */
    public void setProfile_medium(String profile_medium) {
        this.profile_medium = profile_medium;
    }

    /**
     *
     * @return
     */
    public String getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *
     * @return
     */
    public String getFriend() {
        return friend;
    }

    /**
     *
     * @param friend
     */
    public void setFriend(String friend) {
        this.friend = friend;
    }

    /**
     *
     * @return
     */
    public String getFollower() {
        return follower;
    }

    /**
     *
     * @param follower
     */
    public void setFollower(String follower) {
        this.follower = follower;
    }

    /**
     *
     * @return
     */
    public boolean getPremium() {
        return premium;
    }

    /**
     *
     * @param premium
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     *
     * @return
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     *
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     *
     * @return
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /**
     *
     * @param updated_at
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    /**
     *
     * @return
     */
    public boolean getApprove_followers() {
        return approve_followers;
    }

    /**
     *
     * @param approve_followers
     */
    public void setApprove_followers(boolean approve_followers) {
        this.approve_followers = approve_followers;
    }

    /**
     *
     * @return
     */
    public int getFollower_count() {
        return follower_count;
    }

    /**
     *
     * @param follower_count
     */
    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    /**
     *
     * @return
     */
    public int getFriend_count() {
        return friend_count;
    }

    /**
     *
     * @param friend_count
     */
    public void setFriend_count(int friend_count) {
        this.friend_count = friend_count;
    }

    /**
     *
     * @return
     */
    public int getMutual_friend_count() {
        return mutual_friend_count;
    }

    /**
     *
     * @param mutual_friend_count
     */
    public void setMutual_friend_count(int mutual_friend_count) {
        this.mutual_friend_count = mutual_friend_count;
    }

    /**
     *
     * @return
     */
    public String getDate_preference() {
        return date_preference;
    }

    /**
     *
     * @param date_preference
     */
    public void setDate_preference(String date_preference) {
        this.date_preference = date_preference;
    }

    /**
     *
     * @return
     */
    public String getMeasurement_preference() {
        return measurement_preference;
    }

    /**
     *
     * @param measurement_preference
     */
    public void setMeasurement_preference(String measurement_preference) {
        this.measurement_preference = measurement_preference;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getFtp() {
        return ftp;
    }

    /**
     *
     * @param ftp
     */
    public void setFtp(int ftp) {
        this.ftp = ftp;
    }

    /**
     *
     * @return
     */
    public List<Club> getClubs() {
        return clubs;
    }

    /**
     *
     * @param clubs
     */
    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    /**
     *
     * @return
     */
    public List<Gear> getBikes() {
        return bikes;
    }

    /**
     *
     * @param bikes
     */
    public void setBikes(List<Gear> bikes) {
        this.bikes = bikes;
    }

    /**
     *
     * @return
     */
    public List<Gear> getShoes() {
        return shoes;
    }

    /**
     *
     * @param shoes
     */
    public void setShoes(List<Gear> shoes) {
        this.shoes = shoes;
    }
}
