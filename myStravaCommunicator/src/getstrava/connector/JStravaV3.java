package getstrava.connector;

import com.google.gson.Gson;
import getstrava.entities.activity.*;
import getstrava.entities.athlete.Athlete;
import getstrava.entities.club.Club;
import getstrava.entities.gear.Gear;
import getstrava.entities.segment.Bound;
import getstrava.entities.segment.Segment;
import getstrava.entities.segment.SegmentEffort;
import getstrava.entities.segment.SegmentLeaderBoard;
import getstrava.entities.stream.Stream;

import java.io.*;
import java.net.*;
import java.net.Proxy;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author vincent
 */
public class JStravaV3 implements JStrava {

    private String accessToken;
    private Athlete currentAthlete;
    Authenticator _authenticator;
    Proxy _proxy;

    /**
     *
     * @param access_token
     */
    public JStravaV3(String access_token) {

        _authenticator = new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication("gollev",
                        "Z@pdos22!".toCharArray()));
            }
        };
        Authenticator.setDefault(_authenticator);
        _proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("coo-surf.cst.cnes.fr", 8050));

        _proxy = null;
        this.accessToken = access_token;
        String URL = "https://www.strava.com/api/v3/athlete";
        String result = getResult(URL);
        Gson gson = new Gson();
        currentAthlete = gson.fromJson(result, Athlete.class);

    }

    /**
     *
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     *
     * @return
     */
    @Override
    public Athlete getCurrentAthlete() {
        return currentAthlete;
    }

    /**
     *
     * @param optionalParameters
     * @return
     */
    @Override
    public Athlete updateAthlete(HashMap optionalParameters) {
        String URL = "https://www.strava.com/api/v3/athlete";
        String result = putResult(URL, optionalParameters);
        Gson gson = new Gson();
        Athlete athlete = gson.fromJson(result, Athlete.class);

        return athlete;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Athlete findAthlete(int id) {

        String URL = "https://www.strava.com/api/v3/athletes/" + id;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete athlete = gson.fromJson(result, Athlete.class);

        return athlete;

    }

    /**
     *
     * @param athleteId
     * @return
     */
    @Override
    public List<SegmentEffort> findAthleteKOMs(int athleteId) {
        String URL = "https://www.strava.com/api/v3/athletes/" + athleteId + "/koms";
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentEffort[] segmentEffortArray = gson.fromJson(result, SegmentEffort[].class);
        List<SegmentEffort> segmentEfforts = Arrays.asList(segmentEffortArray);
        return segmentEfforts;
    }

    /**
     *
     * @param athleteId
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<SegmentEffort> findAthleteKOMs(int athleteId, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athletes/" + athleteId + "/koms?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentEffort[] segmentEffortArray = gson.fromJson(result, SegmentEffort[].class);
        List<SegmentEffort> segmentEfforts = Arrays.asList(segmentEffortArray);
        return segmentEfforts;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Athlete> getCurrentAthleteFriends() {
        String URL = "https://www.strava.com/api/v3/athlete/friends";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> getCurrentAthleteFriends(int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athlete/friends?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Athlete> findAthleteFriends(int id) {
        String URL = "https://www.strava.com/api/v3/athletes/" + id + "/friends";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param id
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> findAthleteFriends(int id, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athletes/" + id + "/friends?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Athlete> getCurrentAthleteFollowers() {
        String URL = "https://www.strava.com/api/v3/athlete/followers";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> getCurrentAthleteFollowers(int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athlete/followers?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Athlete> findAthleteFollowers(int id) {
        String URL = "https://www.strava.com/api/v3/athletes/" + id + "/followers";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param id
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> findAthleteFollowers(int id, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athletes/" + id + "/followers?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Athlete> findAthleteBothFollowing(int id) {
        String URL = "https://www.strava.com/api/v3/athletes/" + id + "/both-following";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param id
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> findAthleteBothFollowing(int id, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athletes/" + id + "/both-following?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param name
     * @param type
     * @param start_date_local
     * @param elapsed_time
     * @return
     */
    @Override
    public Activity createActivity(String name, String type, String start_date_local, int elapsed_time) {
        String URL = "https://www.strava.com/api/v3/activities?name=" + name + "&type=" + type + "&start_date_local=" + start_date_local + "&elapsed_time=" + elapsed_time;
        String result = postResult(URL);
        Gson gson = new Gson();
        System.out.println("RESULTADO" + result);
        Activity activity = gson.fromJson(result, Activity.class);
        return activity;
    }

    /**
     *
     * @param name
     * @param type
     * @param start_date_local
     * @param elapsed_time
     * @param description
     * @param distance
     * @return
     */
    @Override
    public Activity createActivity(String name, String type, String start_date_local, int elapsed_time, String description, float distance) {
        String URL = "https://www.strava.com/api/v3/activities?name=" + name + "&type=" + type + "&start_date_local=" + start_date_local + "&elapsed_time=" + elapsed_time + "&description=" + description + "&distance=" + distance;
        String result = postResult(URL);
        Gson gson = new Gson();
        Activity activity = gson.fromJson(result, Activity.class);
        return activity;
    }

    /**
     *
     * @param activityId
     */
    @Override
    public void deleteActivity(int activityId) {
        String URL = "https://www.strava.com/api/v3/activities/" + activityId;
        String result = deleteResult(URL);
        Gson gson = new Gson();
        gson.fromJson(result, String.class);

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Activity findActivity(int id) {
        String URL = "https://www.strava.com/api/v3/activities/" + id;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity activity = gson.fromJson(result, Activity.class);

        return activity;
    }

    /**
     *
     * @param id
     * @param include_all_efforts
     * @return
     */
    @Override
    public Activity findActivity(int id, boolean include_all_efforts) {
        String URL = "https://www.strava.com/api/v3/activities/" + id + "?include_all_efforts=" + include_all_efforts;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity activity = gson.fromJson(result, Activity.class);

        return activity;
    }

    /**
     *
     * @param activityId
     * @param optionalParameters
     * @return
     */
    @Override
    public Activity updateActivity(int activityId, HashMap optionalParameters) {
        String URL = "https://www.strava.com/api/v3/activities/" + activityId;
        String result = putResult(URL, optionalParameters);
        Gson gson = new Gson();
        Activity activity = gson.fromJson(result, Activity.class);

        return activity;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Activity> getCurrentAthleteActivities() {
        String URL = "https://www.strava.com/api/v3/athlete/activities";
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> currentActivities = Arrays.asList(activitiesArray);
        return currentActivities;
    }

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Activity> getCurrentAthleteActivities(int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/athlete/activities?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> currentActivities = Arrays.asList(activitiesArray);
        return currentActivities;
    }

    /**
     *
     * @param before
     * @return
     */
    @Override
    public List<Activity> getCurrentAthleteActivitiesBeforeDate(long before) {
        String URL = "https://www.strava.com/api/v3/athlete/activities?before=" + before;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> currentActivities = Arrays.asList(activitiesArray);
        return currentActivities;
    }

    /**
     *
     * @param after
     * @return
     */
    @Override
    public List<Activity> getCurrentAthleteActivitiesAfterDate(long after) {
        String URL = "https://www.strava.com/api/v3/athlete/activities?after=" + after;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> currentActivities = Arrays.asList(activitiesArray);
        return currentActivities;
    }

    /**
     *
     * @return
     */
    public List<Activity> getCurrentFriendsActivities() {
        String URL = "https://www.strava.com/api/v3/activities/following";
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> currentFriendsActivities = Arrays.asList(activitiesArray);
        return currentFriendsActivities;
    }

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    public List<Activity> getCurrentFriendsActivities(int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/activities/following?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> currentFriendsActivities = Arrays.asList(activitiesArray);
        return currentFriendsActivities;
    }

    /**
     *
     * @param activityId
     * @return
     */
    @Override
    public List<Zone> getActivityZones(int activityId) {
        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/zones";
        String result = getResult(URL);
        Gson gson = new Gson();
        Zone[] zonesArray = gson.fromJson(result, Zone[].class);
        List<Zone> zones = Arrays.asList(zonesArray);
        return zones;
    }

    /**
     *
     * @param activityId
     * @return
     */
    @Override
    public List<LapEffort> findActivityLaps(int activityId) {
        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/laps";
        String result = getResult(URL);
        Gson gson = new Gson();
        LapEffort[] lapEffortsArray = gson.fromJson(result, LapEffort[].class);
        List<LapEffort> lapEfforts = Arrays.asList(lapEffortsArray);
        return lapEfforts;
    }

    /**
     *
     * @param activityId
     * @return
     */
    @Override
    public List<Comment> findActivityComments(int activityId) {

        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/comments";
        String result = getResult(URL);
        Gson gson = new Gson();
        Comment[] commentsArray = gson.fromJson(result, Comment[].class);

        List<Comment> comments = Arrays.asList(commentsArray);

        return comments;
    }

    /**
     *
     * @param activityId
     * @param markdown
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Comment> findActivityComments(int activityId, boolean markdown, int page, int per_page) {

        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/comments?markdown=" + markdown + "&page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Comment[] commentsArray = gson.fromJson(result, Comment[].class);

        List<Comment> comments = Arrays.asList(commentsArray);

        return comments;
    }

    /**
     *
     * @param activityId
     * @return
     */
    @Override
    public List<Athlete> findActivityKudos(int activityId) {
        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/kudos";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param activityId
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> findActivityKudos(int activityId, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/kudos?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param clubId
     * @return
     */
    @Override
    public List<Athlete> findClubMembers(int clubId) {
        String URL = "https://www.strava.com/api/v3/clubs/" + clubId + "/members";
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param clubId
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Athlete> findClubMembers(int clubId, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/clubs/" + clubId + "/members?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Athlete[] athletesArray = gson.fromJson(result, Athlete[].class);

        List<Athlete> athletes = Arrays.asList(athletesArray);

        return athletes;
    }

    /**
     *
     * @param clubId
     * @return
     */
    @Override
    public List<Activity> findClubActivities(int clubId) {
        String URL = "https://www.strava.com/api/v3/clubs/" + clubId + "/activities";
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> clubActivities = Arrays.asList(activitiesArray);
        return clubActivities;
    }

    /**
     *
     * @param clubId
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public List<Activity> findClubActivities(int clubId, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/clubs/" + clubId + "/activities" + "?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        Activity[] activitiesArray = gson.fromJson(result, Activity[].class);
        List<Activity> clubActivities = Arrays.asList(activitiesArray);
        return clubActivities;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Club findClub(int id) {
        String URL = "https://www.strava.com/api/v3/clubs/" + id;
        String result = getResult(URL);
        Gson gson = new Gson();
        Club club = gson.fromJson(result, Club.class);

        return club;
    }

    /**
     *
     * @return
     */
    public List<Club> getCurrentAthleteClubs() {
        String URL = "https://www.strava.com/api/v3/athlete/clubs";
        String result = getResult(URL);
        Gson gson = new Gson();
        Club[] clubsArray = gson.fromJson(result, Club[].class);

        List<Club> clubs = Arrays.asList(clubsArray);

        return clubs;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Gear findGear(String id) {
        String URL = "https://www.strava.com/api/v3/gear/" + id;
        String result = getResult(URL);
        Gson gson = new Gson();
        Gear gear = gson.fromJson(result, Gear.class);

        return gear;
    }

    /**
     *
     * @param segmentId
     * @return
     */
    @Override
    public Segment findSegment(long segmentId) {
        String URL = "https://www.strava.com/api/v3/segments/" + segmentId;
        String result = getResult(URL);
        Gson gson = new Gson();
        Segment segment = gson.fromJson(result, Segment.class);
        return segment;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Segment> getCurrentStarredSegment() {
        String URL = "https://www.strava.com/api/v3/segments/starred";
        String result = getResult(URL);
        Gson gson = new Gson();
        Segment[] segmentsArray = gson.fromJson(result, Segment[].class);

        List<Segment> segments = Arrays.asList(segmentsArray);

        return segments;
    }

    /**
     *
     * @param optionalParameters
     * @return
     */
    public List<Segment> getCurrentStarredSegment(HashMap optionalParameters) {
        String URL = "https://www.strava.com/api/v3/segments/starred";
        String result = getResult(URL, optionalParameters);
        Gson gson = new Gson();
        Segment[] segmentsArray = gson.fromJson(result, Segment[].class);

        List<Segment> segments = Arrays.asList(segmentsArray);

        return segments;
    }

    /**
     *
     * @param activityId
     * @return
     */
    @Override
    public List<Photo> findActivityPhotos(int activityId) {

        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/photos";
        String result = getResult(URL);
        Gson gson = new Gson();
        Photo[] photosArray = gson.fromJson(result, Photo[].class);
        List<Photo> photos = Arrays.asList(photosArray);
        return photos;
    }

    /**
     *
     * @param segmentId
     * @return
     */
    @Override
    public SegmentLeaderBoard findSegmentLeaderBoard(long segmentId) {
        String URL = "https://www.strava.com/api/v3/segments/" + segmentId + "/leaderboard";
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentLeaderBoard segmentLeaderBoard = gson.fromJson(result, SegmentLeaderBoard.class);
        return segmentLeaderBoard;
    }

    /**
     *
     * @param segmentId
     * @param page
     * @param per_page
     * @return
     */
    @Override
    public SegmentLeaderBoard findSegmentLeaderBoard(long segmentId, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/segments/" + segmentId + "/leaderboard?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentLeaderBoard segmentLeaderBoard = gson.fromJson(result, SegmentLeaderBoard.class);
        return segmentLeaderBoard;
    }

    public SegmentLeaderBoard findSegmentLeaderBoard(long segmentId, HashMap optionalParameters, int page, int per_page) {
        String URL = "https://www.strava.com/api/v3/segments/" + segmentId + "/leaderboard?page=" + page + "&per_page=" + per_page;
        String result = getResult(URL, optionalParameters);
        Gson gson = new Gson();
        SegmentLeaderBoard segmentLeaderBoard = gson.fromJson(result, SegmentLeaderBoard.class);
        return segmentLeaderBoard;
    }

    /**
     *
     * @param segmentId
     * @param optionalParameters
     * @return
     */
    @Override
    public SegmentLeaderBoard findSegmentLeaderBoard(long segmentId, HashMap optionalParameters) {
        String URL = "https://www.strava.com/api/v3/segments/" + segmentId + "/leaderboard";
        String result = getResult(URL, optionalParameters);
        Gson gson = new Gson();
        SegmentLeaderBoard segmentLeaderBoard = gson.fromJson(result, SegmentLeaderBoard.class);
        return segmentLeaderBoard;
    }

    /**
     *
     * @param bound
     * @return
     */
    @Override
    public List<Segment> findSegments(Bound bound) {
        String URL = "https://www.strava.com/api/v3/segments/explore?bounds=" + bound.toString();
        String result = getResult(URL);

        //////////UGLY HACK TO ALLOW GSON TO PARSE THE JSON STRING AND RETURN A LIST OF SEGMENTS
        String segmentString = "\\{\"segments\":";

        result = result.replaceFirst(segmentString, "");
        result = result.substring(0, result.lastIndexOf("}"));

        Gson gson = new Gson();
        Segment[] segmentsArray = gson.fromJson(result, Segment[].class);
        List<Segment> segments = Arrays.asList(segmentsArray);
        return segments;
    }

    /**
     *
     * @param bound
     * @param optionalParameters
     * @return
     */
    @Override
    public List<Segment> findSegments(Bound bound, HashMap optionalParameters) {
        String URL = "https://www.strava.com/api/v3/segments/explore?bounds=" + bound.toString();
        String result = getResult(URL, optionalParameters);

        //////////UGLY HACK TO ALLOW GSON TO PARSE THE JSON STRING AND RETURN A LIST OF SEGMENTS
        String segmentString = "\\{\"segments\":";
        if (result.contains(segmentString)) {
            result = result.replaceFirst(segmentString, "");
            result = result.substring(0, result.lastIndexOf("}"));
        }

        Gson gson = new Gson();
        Segment[] segmentsArray = gson.fromJson(result, Segment[].class);
        List<Segment> segments = Arrays.asList(segmentsArray);
        return segments;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public SegmentEffort findSegmentEffort(int id) {
        String URL = "https://www.strava.com/api/v3/segment_efforts/" + id;
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentEffort segmentEffort = gson.fromJson(result, SegmentEffort.class);
        return segmentEffort;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<SegmentEffort> findSegmentEfforts(long id) {

        String URL = "https://www.strava.com/api/v3/segments/" + id + "/all_efforts";
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentEffort[] effortsArray = gson.fromJson(result, SegmentEffort[].class);
        List<SegmentEffort> efforts = Arrays.asList(effortsArray);
        return efforts;

    }

    /**
     *
     * @param id
     * @param filtre
     * @return
     */
    @Override
    public List<SegmentEffort> findSegmentEfforts(long id, String filtre) {

        String URL = "https://www.strava.com/api/v3/segments/" + id + "/all_efforts?" + filtre;
        String result = getResult(URL);
        Gson gson = new Gson();
        SegmentEffort[] effortsArray = gson.fromJson(result, SegmentEffort[].class);
        List<SegmentEffort> efforts = Arrays.asList(effortsArray);
        return efforts;

    }

    /**
     *
     * @param activityId
     * @param types
     * @return
     */
    @Override
    public List<Stream> findActivityStreams(int activityId, String[] types) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(types[i]);
        }

        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/streams/" + builder.toString();
        String result = getResult(URL);
        Gson gson = new Gson();
        Stream[] streamsArray = gson.fromJson(result, Stream[].class);
        List<Stream> streams = Arrays.asList(streamsArray);
        return streams;

    }

    /**
     *
     * @param activityId
     * @param types
     * @param resolution
     * @param series_type
     * @return
     */
    @Override
    public List<Stream> findActivityStreams(int activityId, String[] types, String resolution, String series_type) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(types[i]);
        }

        String URL = "https://www.strava.com/api/v3/activities/" + activityId + "/streams/" + builder.toString() + "?resolution=" + resolution;

        if (series_type != null && !series_type.isEmpty()) {
            URL += "&series_type=" + series_type;
        }

        String result = getResult(URL);
        Gson gson = new Gson();
        Stream[] streamsArray = gson.fromJson(result, Stream[].class);
        List<Stream> streams = Arrays.asList(streamsArray);
        return streams;
    }

    /**
     *
     * @param id
     * @param types
     * @return
     */
    @Override
    public List<Stream> findEffortStreams(long id, String[] types) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(types[i]);
        }

        String URL = "https://www.strava.com/api/v3/segment_efforts/" + id + "/streams/" + builder.toString();
        String result = getResult(URL);
        Gson gson = new Gson();
        Stream[] streamsArray = gson.fromJson(result, Stream[].class);
        List<Stream> streams = Arrays.asList(streamsArray);
        return streams;
    }

    /**
     *
     * @param id
     * @param types
     * @param resolution
     * @param series_type
     * @return
     */
    @Override
    public List<Stream> findEffortStreams(int id, String[] types, String resolution, String series_type) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(types[i]);
        }

        String URL = "https://www.strava.com/api/v3/segment_efforts/" + id + "/streams/" + builder.toString() + "?resolution=" + resolution;

        if (series_type != null && !series_type.isEmpty()) {
            URL += "&series_type=" + series_type;
        }

        String result = getResult(URL);
        Gson gson = new Gson();
        Stream[] streamsArray = gson.fromJson(result, Stream[].class);
        List<Stream> streams = Arrays.asList(streamsArray);
        return streams;
    }

    /**
     *
     * @param id
     * @param types
     * @return
     */
    @Override
    public List<Stream> findSegmentStreams(int id, String[] types) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(types[i]);
        }

        String URL = "https://www.strava.com/api/v3/segments/" + id + "/streams/" + builder.toString();
        String result = getResult(URL);
        Gson gson = new Gson();
        Stream[] streamsArray = gson.fromJson(result, Stream[].class);
        List<Stream> streams = Arrays.asList(streamsArray);
        return streams;
    }

    /**
     *
     * @param id
     * @param types
     * @param resolution
     * @param series_type
     * @return
     */
    @Override
    public List<Stream> findSegmentStreams(int id, String[] types, String resolution, String series_type) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(types[i]);
        }

        String URL = "https://www.strava.com/api/v3/segments/" + id + "/streams/" + builder.toString() + "?resolution=" + resolution;

        if (series_type != null && !series_type.isEmpty()) {
            URL += "&series_type=" + series_type;
        }

        String result = getResult(URL);
        Gson gson = new Gson();
        Stream[] streamsArray = gson.fromJson(result, Stream[].class);
        List<Stream> streams = Arrays.asList(streamsArray);
        return streams;
    }

    /**
     *
     * @param data_type
     * @param file
     * @return
     */
    @Override
    public UploadStatus uploadActivity(String data_type, File file) {

        String content;

        try {
            HashMap optionalParameters = new HashMap<>();
            optionalParameters.put("client_id", "2940");
            optionalParameters.put("response_type", "code");
            optionalParameters.put("scope", "write");
            optionalParameters.put("approval_prompt", "force");
            optionalParameters.put("redirect_uri", "http://127.0.0.1");

            content = getResult("https://www.strava.com/oauth/authorize", optionalParameters);
            System.out.println(content);

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(
                    "https://www.strava.com/api/v3/uploads");
            httpPost.addHeader("Authorization", "Bearer " + getAccessToken());
            httpPost.setHeader("enctype", "multipart/form-data");

            MultipartEntity reqEntity = new MultipartEntity(
                    HttpMultipartMode.BROWSER_COMPATIBLE);

            reqEntity.addPart("activity_type", new StringBody("ride"));
            reqEntity.addPart("data_type", new StringBody(data_type));

            FileBody bin = new FileBody(file);
            reqEntity.addPart("file", bin);

            httpPost.setEntity(reqEntity);

            HttpResponse response;

            response = httpClient.execute(httpPost);

            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                // EntityUtils to get the response content
                content = EntityUtils.toString(respEntity);
                System.out.println(content);
                //JSONParser jsonParser = new JSONParser();
                //jsonObj = (JSONObject) jsonParser.parse(content);
            }

            return null;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JStravaV3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JStravaV3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param activity_type
     * @param name
     * @param description
     * @param is_private
     * @param trainer
     * @param data_type
     * @param external_id
     * @param file
     * @return
     */
    @Override
    public UploadStatus uploadActivity(String activity_type, String name, String description, int is_private, int trainer, String data_type, String external_id, String file) {

        String charset = "UTF-8";
        String param = "value";
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.

        File textFile = new File(file);

        String finalUrl = "https://www.strava.com/api/v3/uploads";

        try {
            //String[] parsedUrl = URL.split("\\?");
            URL url = new URL(finalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            conn.setDoOutput(true);
            OutputStream output = conn.getOutputStream();
            //DataOutputStream wr = new DataOutputStream(output);
            PrintWriter wr = new PrintWriter(new OutputStreamWriter(output, charset), true);

            wr.append("--" + boundary).append(CRLF);
            wr.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
            wr.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            wr.append(CRLF).append(param).append(CRLF).flush();

            wr.append("--" + boundary).append(CRLF);
            wr.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.getName() + "\"").append(CRLF);
            wr.append("Content-Type: text/plain; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
            wr.append(CRLF).flush();
            Files.copy(textFile.toPath(), output);
            output.flush(); // Important before continuing with writer!
            wr.append(CRLF).flush();

            // End of multipart/form-data.
            wr.append("--" + boundary + "--").append(CRLF).flush();

            int responseCode = ((HttpURLConnection) conn).getResponseCode();
            System.out.println("Reponse: " + responseCode); // Should be 200

        } catch (IOException ex) {
            Logger.getLogger(JStravaV3.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     * @param uploadId
     * @return
     */
    @Override
    public UploadStatus checkUploadStatus(int uploadId
    ) {

        String URL = "https://www.strava.com/api/v3/uploads/" + uploadId;
        String result = getResult(URL);
        Gson gson = new Gson();
        UploadStatus status = gson.fromJson(result, UploadStatus.class);

        return status;
    }

    private String getResult(String URL) {
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(URL);

            HttpURLConnection conn;

            if (_proxy != null) {
                conn = (HttpURLConnection) url.openConnection(_proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }

            conn.setAllowUserInteraction(true);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

        return sb.toString();

    }

    /**
     *
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String getResult(String URL, HashMap optionalParameters) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        try {

            Iterator iterator = optionalParameters.keySet().iterator();

            int index = 0;
            while (iterator.hasNext()) {
                if (index == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                String key = (String) iterator.next();
                sb.append(key);
                sb.append("=");
                sb.append(optionalParameters.get(key).toString());
                //sb.append(URLEncoder.encode(optionalParameters.get(key).toString(), "UTF-8"));
                index++;
            }

            System.out.println(sb);
            URI uri = new URI(String.format(sb.toString()));
            URL url = uri.toURL();

            HttpURLConnection conn;
            if (_proxy != null) {
                conn = (HttpURLConnection) url.openConnection(_proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

        } catch (IOException e) {
            System.err.println(e.toString());
            return null;
        } catch (URISyntaxException e) {
            System.err.println(e.toString());
            return null;
        }
        return sb.toString();

    }

    private String postResult(String URL) {
        StringBuffer sb = new StringBuffer();
        try {

            String finalUrl = "";

            String[] parsedUrl = URL.split("\\?");
            String params = URLEncoder.encode(parsedUrl[1], "UTF-8").replace("%3D", "=").replace("%26", "&");

            URL url = new URL(parsedUrl[0] + "?" + params);
            HttpURLConnection conn;
            if (_proxy != null) {
                conn = (HttpURLConnection) url.openConnection(_proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());

            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            boolean redirect = false;
            // normally, 3xx is redirect
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                if (status == HttpURLConnection.HTTP_MOVED_TEMP
                        || status == HttpURLConnection.HTTP_MOVED_PERM
                        || status == HttpURLConnection.HTTP_SEE_OTHER) {
                    redirect = true;
                }
            }

            if (redirect) {

                // get redirect url from "location" header field
                String newUrl = conn.getHeaderField("Location");

                // open the new connnection again
                conn = (HttpURLConnection) new URL(newUrl).openConnection();
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());

            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private String putResult(String URL) {
        StringBuilder sb = new StringBuilder();

        try {
            String finalUrl = "";
            if (URL.contains("?")) {
                String[] parsedUrl = URL.split("\\?");
                String params = URLEncoder.encode(parsedUrl[1], "UTF-8");
                finalUrl = parsedUrl[0] + "?" + params;
            } else {
                finalUrl = URL;
            }

            URL url = new URL(finalUrl);
            HttpURLConnection conn;
            if (_proxy != null) {
                conn = (HttpURLConnection) url.openConnection(_proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }

            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            if (conn.getResponseCode() != 200 | conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

        return sb.toString();

    }

    private String putResult(String URL, HashMap optionalParameters) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        try {

            Iterator iterator = optionalParameters.keySet().iterator();

            int index = 0;
            while (iterator.hasNext()) {
                if (index == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                String key = (String) iterator.next();
                sb.append(key);
                sb.append("=");
                sb.append(URLEncoder.encode(optionalParameters.get(key).toString(), "UTF-8"));
                index++;
            }

            URI uri = new URI(sb.toString());
            URL url = uri.toURL();

            HttpURLConnection conn;
            if (_proxy != null) {
                conn = (HttpURLConnection) url.openConnection(_proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }

            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

        } catch (IOException e) {
            System.err.println(e.toString());
            return null;
        } catch (URISyntaxException e) {
            System.err.println(e.toString());
            return null;
        }
        return sb.toString();

    }

    private String deleteResult(String URL) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        try {

            URI uri = new URI(String.format(sb.toString()));
            URL url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            if (conn.getResponseCode() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

        } catch (IOException e) {
            System.err.println(e.toString());
            return null;
        } catch (URISyntaxException e) {
            System.err.println(e.toString());
            return null;
        }
        return sb.toString();

    }

}
