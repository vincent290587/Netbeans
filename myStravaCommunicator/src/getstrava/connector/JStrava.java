package getstrava.connector;

import getstrava.entities.activity.*;
import getstrava.entities.athlete.Athlete;
import getstrava.entities.club.Club;
import getstrava.entities.gear.Gear;
import getstrava.entities.segment.Bound;
import getstrava.entities.segment.Segment;
import getstrava.entities.segment.SegmentEffort;
import getstrava.entities.segment.SegmentLeaderBoard;
import getstrava.entities.stream.Stream;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roberto on 12/26/13.
 */
public interface JStrava {

    /**
     *
     * @return
     */
    public Athlete getCurrentAthlete();

    /**
     *
     * @param optionalParameters
     * @return
     */
    public Athlete updateAthlete(HashMap optionalParameters);

    /**
     *
     * @param id
     * @return
     */
    public Athlete findAthlete(int id);

    /**
     *
     * @param athleteId
     * @return
     */
    public List<SegmentEffort> findAthleteKOMs(int athleteId);

    /**
     *
     * @param athleteId
     * @param page
     * @param per_page
     * @return
     */
    public List<SegmentEffort> findAthleteKOMs(int athleteId,int page, int per_page);

    /**
     *
     * @return
     */
    public List<Athlete> getCurrentAthleteFriends();

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> getCurrentAthleteFriends(int page, int per_page);

    /**
     *
     * @param id
     * @return
     */
    public List<Athlete> findAthleteFriends(int id);

    /**
     *
     * @param id
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> findAthleteFriends(int id, int page, int per_page);

    /**
     *
     * @return
     */
    public List<Athlete> getCurrentAthleteFollowers();

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> getCurrentAthleteFollowers(int page, int per_page);

    /**
     *
     * @param id
     * @return
     */
    public List<Athlete> findAthleteFollowers(int id);

    /**
     *
     * @param id
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> findAthleteFollowers(int id, int page, int per_page);

    /**
     *
     * @param id
     * @return
     */
    public List<Athlete> findAthleteBothFollowing(int id);

    /**
     *
     * @param id
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> findAthleteBothFollowing(int id, int page, int per_page);

    /**
     *
     * @param name
     * @param type
     * @param start_date_local
     * @param elapsed_time
     * @return
     */
    public Activity createActivity(String name, String type, String start_date_local, int elapsed_time);

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
    public Activity createActivity(String name, String type, String start_date_local, int elapsed_time, String description, float distance );

    /**
     *
     * @param activityId
     */
    public void deleteActivity(int activityId);

    /**
     *
     * @param id
     * @return
     */
    public Activity findActivity(int id);

    /**
     *
     * @param id
     * @param include_all_efforts
     * @return
     */
    public Activity findActivity(int id,boolean include_all_efforts);

    /**
     *
     * @param activityId
     * @param optionalParameters
     * @return
     */
    public Activity updateActivity(int activityId, HashMap optionalParameters);

    /**
     *
     * @return
     */
    public List<Activity> getCurrentAthleteActivities();

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    public List<Activity> getCurrentAthleteActivities( int page, int per_page);

    /**
     *
     * @param before
     * @return
     */
    public List<Activity> getCurrentAthleteActivitiesBeforeDate( long before);

    /**
     *
     * @param after
     * @return
     */
    public List<Activity> getCurrentAthleteActivitiesAfterDate( long after);

    /**
     *
     * @return
     */
    public List<Activity> getCurrentFriendsActivities();

    /**
     *
     * @param page
     * @param per_page
     * @return
     */
    public List<Activity> getCurrentFriendsActivities( int page, int per_page);

    /**
     *
     * @param activityId
     * @return
     */
    public List<Zone> getActivityZones (int activityId);

    /**
     *
     * @param activityId
     * @return
     */
    public List<LapEffort> findActivityLaps(int activityId);

    /**
     *
     * @param activityId
     * @return
     */
    public List<Comment> findActivityComments(int activityId);

    /**
     *
     * @param activityId
     * @param markdown
     * @param page
     * @param per_page
     * @return
     */
    public List<Comment> findActivityComments(int activityId,boolean markdown, int page, int per_page);

    /**
     *
     * @param activityId
     * @return
     */
    public List<Athlete> findActivityKudos(int activityId);

    /**
     *
     * @param activityId
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> findActivityKudos(int activityId,int page, int per_page);

    /**
     *
     * @param activityId
     * @return
     */
    public List<Photo>findActivityPhotos(int activityId);

    /**
     *
     * @param clubId
     * @return
     */
    public List<Athlete> findClubMembers(int clubId);

    /**
     *
     * @param clubId
     * @param page
     * @param per_page
     * @return
     */
    public List<Athlete> findClubMembers(int clubId,int page, int per_page);

    /**
     *
     * @param clubId
     * @return
     */
    public List<Activity> findClubActivities(int clubId);

    /**
     *
     * @param clubId
     * @param page
     * @param per_page
     * @return
     */
    public List<Activity> findClubActivities(int clubId, int page, int per_page);

    /**
     *
     * @param id
     * @return
     */
    public Club findClub(int id);

    /**
     *
     * @return
     */
    public List<Club> getCurrentAthleteClubs();

    /**
     *
     * @param id
     * @return
     */
    public Gear findGear(String id);

    /**
     *
     * @param segmentId
     * @return
     */
    public Segment findSegment(long segmentId);

    /**
     *
     * @return
     */
    public List<Segment> getCurrentStarredSegment();

    /**
     *
     * @param segmentId
     * @return
     */
    public SegmentLeaderBoard findSegmentLeaderBoard (long segmentId);

    /**
     *
     * @param segmentId
     * @param page
     * @param per_page
     * @return
     */
    public SegmentLeaderBoard findSegmentLeaderBoard (long segmentId, int page, int per_page);

    /**
     *
     * @param segmentId
     * @param optionalParameters
     * @return
     */
    public SegmentLeaderBoard findSegmentLeaderBoard (long segmentId, HashMap optionalParameters);

    /**
     *
     * @param bound
     * @return
     */
    public List<Segment>findSegments(Bound bound);

    /**
     *
     * @param bound
     * @param optionalParameters
     * @return
     */
    public List<Segment>findSegments(Bound bound,HashMap optionalParameters);

    /**
     *
     * @param id
     * @return
     */
    public SegmentEffort findSegmentEffort(int id);

    /**
     *
     * @param id
     * @return
     */
    public List<SegmentEffort>findSegmentEfforts(long id);
    
   
    /**
     *
     * @param id
     * @param filtre
     * @return
     */
    public List<SegmentEffort>findSegmentEfforts(long id, String filtre);
    
    /**
     *
     * @param activityId
     * @param types
     * @return
     */
    public List<Stream>findActivityStreams(int activityId,String[]types);

    /**
     *
     * @param activityId
     * @param types
     * @param resolution
     * @param series_type
     * @return
     */
    public List<Stream>findActivityStreams(int activityId,String[]types,String resolution, String series_type);

    /**
     *
     * @param id
     * @param types
     * @return
     */
    public List<Stream>findEffortStreams(long id,String[]types);

    /**
     *
     * @param activityId
     * @param types
     * @param resolution
     * @param series_type
     * @return
     */
    public List<Stream>findEffortStreams(int activityId,String[]types,String resolution,String series_type);

    /**
     *
     * @param activityId
     * @param types
     * @return
     */
    public List<Stream>findSegmentStreams(int activityId,String[]types);

    /**
     *
     * @param activityId
     * @param types
     * @param resolution
     * @param series_type
     * @return
     */
    public List<Stream>findSegmentStreams(int activityId,String[]types,String resolution,String series_type);

    /**
     *
     * @param data_type
     * @param file
     * @return
     */
    public UploadStatus uploadActivity(String data_type,File file);

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
    public UploadStatus uploadActivity(String activity_type,String name,String description,int is_private,int trainer,String data_type,String external_id,String file);

    /**
     *
     * @param uploadId
     * @return
     */
    public UploadStatus checkUploadStatus(int uploadId);

}
