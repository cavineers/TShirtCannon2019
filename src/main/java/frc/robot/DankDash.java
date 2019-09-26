package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class DankDash {
    private NetworkTable netTable;
    private String robotName;
    private String profileLocation;

    public DankDash() {
        this.netTable = NetworkTableInstance.getDefault().getTable("DankDash");
    }

    public void setProfileName(String robotName) {
        this.robotName = robotName;
    }

    public String getProfileName() {
        return this.robotName;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation;
    }

    public String getProfileLocation() {
        return this.profileLocation;
    }

    public void addListener() {
        netTable.addEntryListener((table, key, entry, value, flags) -> {
            System.out.println("Key: " + key);
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

    public void export() {
        netTable.getEntry("ProfileData").setString(this.profileLocation);
        netTable.getEntry("ProfileName").setString(this.robotName);
    }

    public void sendDash(String id, String data) {
        netTable.getEntry(id).setString(data);
    }
}