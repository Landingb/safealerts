package net.safety.safetalerts.url;

import java.util.List;

public class FireStationCoverge {
    List<InfoPersonFull> fireStationPersons;
    int adultCount;
    int childCount;

    public List<InfoPersonFull> getFireStationPersons() {
        return fireStationPersons;
    }

    public void setFireStationPersons(List<InfoPersonFull> fireStationPersons) {
        this.fireStationPersons = fireStationPersons;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
