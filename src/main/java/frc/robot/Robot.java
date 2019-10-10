/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.lib.MathHelper;
import frc.robot.subsystems.AnglePistons;
import frc.robot.subsystems.Cannon;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static DriveTrain drivetrain;

    public DankDash dankDash;

    public static double lastUpdateTime;
    public static double lastLEDUpdateTime;

    public static NetworkTable netTable;
    public static double heartbeatValue;

    public static AHRS gyro;
    public static OI oi;

    public static boolean isAutoOverridden = false;

    // Digital Inputs for auto selection
    DigitalInput rightStart, leftStart, middleStart;
    AnalogInput favorCargoBayFront, favorCargoBaySide, favorRocket;

    public static Cannon leftCannon;
    public static Cannon rightCannon;

    public static AnglePistons anglePistons;

    double posError;
    double posWant;
    double posGot;
    double velError;
    double velWant;
    double velGot;

    String posData;
    String velData;

    // Camera clock sync checking thread
	public static Object grabber;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        drivetrain = new DriveTrain();

        // initialize operator interface / controls
        oi = new OI();

        // Init and export profile to network tables
        dankDash = new DankDash();
        dankDash.setProfileLocation("TShirtCannon");
        dankDash.setProfileName("T-Shirt Cannon");
        dankDash.export();
        dankDash.addListener();

        LiveWindow.disableAllTelemetry();

        rightStart = new DigitalInput(0);
        middleStart = new DigitalInput(1);
        leftStart = new DigitalInput(2);
        

        favorCargoBayFront = new AnalogInput(0);
        favorCargoBaySide = new AnalogInput(1);
        favorRocket = new AnalogInput(2);

        // Init Cannons
        leftCannon = new Cannon(Constants.kLeftCannonModule, Constants.kLeftCannonChannel);
        rightCannon = new Cannon(Constants.kRightCannonModule, Constants.kRightCannonChannel);

        anglePistons = new AnglePistons();

        // Elevator

        // Velocity loop
        // SmartDashboard.putNumber("f-val", Constants.kFVelocityElevUp);
        // SmartDashboard.putNumber("p-val", Constants.kPVelocityElev);
        // SmartDashboard.putNumber("i-val", Constants.kIVelocityElev);
        // SmartDashboard.putNumber("d-val", Constants.kDVelocityElev);
        // SmartDashboard.putNumber("current-vel",
        // elevator.getElevatorMotor().getEncoder().getVelocity());

        // Position loop
        // SmartDashboard.putNumber("f-val", Constants.kFPosElev);
        // SmartDashboard.putNumber("p-val", Constants.kPPosElev);
        // SmartDashboard.putNumber("i-val", Constants.kIPosElev);
        // SmartDashboard.putNumber("d-val", Constants.kDPosElev);
        // SmartDashboard.putNumber("current-pos",
        // elevator.getElevatorMotor().getEncoder().getPosition());
        // SmartDashboard.putNumber("position output: ", elevator.getPIDPosOutput());

        // //Grabber
        // SmartDashboard.putNumber("f-val", Constants.kGrabberVelF);
        // SmartDashboard.putNumber("p-val", Constants.kGrabberVelP);
        // SmartDashboard.putNumber("i-val", Constants.kGrabberVelI);
        // SmartDashboard.putNumber("d-val", Constants.kGrabberVelD);
        // SmartDashboard.putNumber("grabber-vel", grabber.getArmMotor().getEncoder().getVelocity());

        // SmartDashboard.putNumber("f-val", Constants.kGrabberPosF);
        // SmartDashboard.putNumber("p-val", Constants.kGrabberPosP);
        // SmartDashboard.putNumber("i-val", Constants.kGrabberPosI);
        // SmartDashboard.putNumber("d-val", Constants.kGrabberPosD);
        // SmartDashboard.putNumber("grabber-pos", grabber.getArmMotor().getEncoder().getPosition());

        //Tuning ball intake PID
        // SmartDashboard.putNumber("f-val", Constants.kGrabberBallVelF);
        // SmartDashboard.putNumber("p-val", Constants.kGrabberBallVelP);
        // SmartDashboard.putNumber("i-val", Constants.kGrabberBallVelI);
        // SmartDashboard.putNumber("d-val", Constants.kGrabberBallVelD);
        // SmartDashboard.putNumber("wheel vel", grabber.getBallMotor().getSelectedSensorVelocity());


    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        this.updateDankDash();
    }

    @Override
    public void disabledInit() {
        Scheduler.getInstance().removeAll();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        oi.updatePeriodicCommands();
        Scheduler.getInstance().run();
        
    }

    @Override
    public void teleopInit() {
        
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        oi.updatePeriodicCommands();
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    /**
     * Get the current heading of the robot in radians
     * 
     * @return the current robot heading in radians from -pi to pi
     */
    public static double getAngleRad() {
        return MathHelper.angleToNegPiToPi(Math.toRadians(Robot.gyro.getYaw()));
    }

    /**
     * Gets the current time in seconds
     * 
     * @return the current time in seconds
     */
    public static double getCurrentTime() {
        return Timer.getFPGATimestamp();
    }

    /**
     * Returns true if the robot is currently in the end game period
     */
    public static boolean isEndGame() {
        return DriverStation.getInstance().isOperatorControl() && DriverStation.getInstance().getMatchTime() < 30;
    }

    /**
     * Resyncs clocks if needed
     */

    /**
     * Sends important values like the match time and the heartbeat value to the
     * dashbaord
     */
    public void updateDankDash() {
        if (Robot.getCurrentTime() - lastUpdateTime > 1) { // update the dashboard display once per second
            lastUpdateTime = Robot.getCurrentTime();
            heartbeatValue++;
        }
    }

}