package frc.robot;

public class Constants {
    // DO NOT EDIT THE FOLLOWING CONSTANTS
    public static final double kDefaultDt = 0.01; // in seconds; the default dt for pathfinding calculations
    public static final double kMaxAccelSpeedUp = 300; //in inches/sec^2; the max acceleration the robot can be commanded to experience when traveling a path
    public static final double kWheelBase = 33; //in inches; the distance between the left and right drive wheels
    public static final double kSensorUnitsPerInch = 72.5572917; //pulses per foot 854.3 math; 876.7 experimentally, 848.18- 4 times 64
    public static final double kMaxTurnToAngleSpeed = 144; // in inches per second; the max speed of the robot used for turn to angle
    public static final double kAngleTolerance = 0.5; // in degrees; the tolerance of turn to angle
    public static final int kTimeoutMs = 10;
    public static final int kPIDLoopIdx = 0;
    public static final double kPVelocity = 5;//1.8; //4
	public static final double kIVelocity = 0.0;//0.0; //0
	public static final double kDVelocity = 23;//0.15; //10
	public static final double kFVelocity = 0.7;//1.5//0.6
    public static final int kVelocityIZone = 0;
    public static final double kAVelocity = 0.001; // TODO: Tune/Test
    public static final double kDriveVoltageRampRate = 0.0;
    public static final int kMaxListSize = (int)(1 / Constants.kOdometryLoopTime) * 3; // max size of position lists is 
    public static final double kOdometryLoopTime = 0.05; // update odometry 20 times per second
    public static final double kCameraToMapToleranceLvl1 = 1.5;
    public static final double kCameraToMapToleranceLvl2 = 3.0;
    public static final double kCameraToMapPercentLvl2 = 0.5;
    public static final double kCameraToMapPercentLvl3 = 0.2;
    public static final double kCameraToMapToleranceLvl3 = 8.0;
    public static final double kPathPursuitTolerance = 1;
    public static final double kMinLookAhead = 12.0;
    public static final double kMaxLookAhead = 24.0;
    public static final double kMinLookAheadSpeed = 9.0;
    public static final double kMaxLookAheadSpeed = 120.0;
    public static final int kFieldWidth = 324;
    public static final double kPathPursuitFinishTolerance = 5;
    public static final double kStopSteeringDistance = 4;
    public static final double kMaxTargetSpeed = 50;
    public static final double kMaxTargetAccel = 120;
    public static final double kMinLookAheadTargeting = 3.0;
    public static final double kMinLookAheadSpeedTargeting = 3.0;
    public static final double kMaxLookAheadTargeting = 12.0;
    public static final double kMaxLookAheadSpeedTargeting = 50.0;
    public static final double kStraightLineDistance = 2;
    public static final double kMinRadiusTargeting = 5;
    
    // BEGIN EDITABLE
    // place all your constants here
    public static final int kLeftCannonModule = 20;
    public static final int kLeftCannonChannel = 7;
    public static final int kRightCannonModule = 20;
    public static final int kRightCannonChannel = 6;
    public static final int kAnglePistonForward = 1;
    public static final int kAnglePistonReverse = 2;
}