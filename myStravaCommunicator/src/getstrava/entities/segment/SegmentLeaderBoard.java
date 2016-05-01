package getstrava.entities.segment;

import java.util.List;

/**
 * Created by roberto on 1/9/14.
 */
public class SegmentLeaderBoard {

    private int effort_count;
    private int entry_count;
    private List<LeaderBoardEntry> entries;

    /**
     *
     */
    public SegmentLeaderBoard() {
    }

    /**
     *
     * @return
     */
    public int getEffort_count() {
        return effort_count;
    }

    /**
     *
     * @param effort_count
     */
    public void setEffort_count(int effort_count) {
        this.effort_count = effort_count;
    }

    /**
     *
     * @return
     */
    public int getEntry_count() {
        return entry_count;
    }

    /**
     *
     * @param entry_count
     */
    public void setEntry_count(int entry_count) {
        this.entry_count = entry_count;
    }

    /**
     *
     * @return
     */
    public List<LeaderBoardEntry> getEntries() {
        return entries;
    }

    /**
     *
     * @param entries
     */
    public void setEntries(List<LeaderBoardEntry> entries) {
        this.entries = entries;
    }
}
