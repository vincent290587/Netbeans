/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myStravaDownload;

import getstrava.entities.segment.Segment;
import getstrava.entities.segment.SegmentEffort;
import getstrava.entities.segment.SegmentLeaderBoard;
import java.util.List;

/**
 *
 * @author vincent
 */
public class mySegment {

    public List<String> _lignes;
    public Segment _seg;
    public SegmentEffort _effort;
    public SegmentLeaderBoard _leader;
    
    public mySegment (List<String> lignes_, Segment seg_, SegmentLeaderBoard leader_, SegmentEffort effort_) {
        _effort = effort_;
        _leader = leader_;
        _seg = seg_;
        _lignes = lignes_;
    }
    
    public void ajoutLigne(String lig_) {
        _lignes.add(lig_);
    }
    
    public void setEffort(SegmentEffort effort_) {
        _effort = effort_;
    }
    
    public void setLeader(SegmentLeaderBoard leader_) {
        _leader = leader_;
    }
    
    public void setSegment(Segment seg_) {
        _seg = seg_;
    }

}
