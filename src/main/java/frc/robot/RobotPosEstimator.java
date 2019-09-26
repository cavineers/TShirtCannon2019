package frc.robot;

import java.util.concurrent.locks.ReentrantLock;

import edu.wpi.first.wpilibj.Notifier;
import frc.lib.RobotPos;
import frc.lib.RobotPosMap;
import frc.lib.RobotPosUpdate;
import frc.lib.RobotPosUpdate.UpdateType;
import frc.lib.pathPursuit.Point;
import frc.robot.Robot;

public class RobotPosEstimator {

    ReentrantLock mutex = new ReentrantLock();
    
    RobotPosMap map = new RobotPosMap(Constants.kMaxListSize, new RobotPosUpdate(0,0, 0, 0, UpdateType.BASE));

    public double rWheelTravel = 0;
    public double lWheelTravel = 0;
    public double heading = 0;
    
    Notifier posUpdater = new Notifier(this::recalcPositionEstimate);

    public RobotPosEstimator(double x, double y, double h, double rWT, double lWT) {
        map.clearAndSetPos(new RobotPos(x, y, h, 0, 0));
        rWheelTravel = Robot.drivetrain.getRightPos();
        lWheelTravel = Robot.drivetrain.getLeftPos();
        heading = Robot.getAngleRad();
    }

    public void updatePos(double newRTravel, double newLTravel, double newHeading) {
        double dR = newRTravel - rWheelTravel;
        double dL = newLTravel - lWheelTravel;
        double dH = rotate(getInverseOfAngle(heading), newHeading);
        double epsilon = 0.0000001; // threshold between circular and linear estimations
        double dx;
        double dy;
        if (dH > epsilon) { // estimate change in position using constant radius math
            double radius = (dR + dL) / (2 * dH);
            double mag = 2 * radius * Math.sin(dH / 2);
            double XMag = mag * Math.cos(newHeading);
            double YMag = mag * Math.sin(newHeading);
            dx = XMag;
            dy = YMag;
            
        } else { // estimate change in position using linear distance between points
            double mag = (dR + dL) / 2;
            dy = Math.sin(newHeading) * mag;
            dx = Math.cos(newHeading) * mag;
        }

        map.addWheelUpdate(dx, dy, Robot.getAngleRad(), Robot.getCurrentTime());
        heading = newHeading;
        rWheelTravel = newRTravel;
        lWheelTravel = newLTravel;
    }

    /**
     * Effectively undoes the rotation by an angle; If a line segment is rotated by
     * the angle and then its inverse, it will have the same heading as before being
     * rotated
     */
    public double getInverseOfAngle(double angleRad) {
        return Math.atan2((-1 * Math.sin(angleRad)), Math.cos(angleRad));
    }

    /**
     * Rotates a1 by a2 using a rotation matrix
     */
    public double rotate(double a1, double a2) {
        return Math.atan2(Math.cos(a1) * Math.sin(a2) + Math.sin(a1) * Math.cos(a2),
                Math.cos(a1) * Math.cos(a2) - Math.sin(a1) * Math.sin(a2));
    }

    /**
     * Get the net distance traveled by the right wheels of the robot
     */
    public double getRightWheelTravel() {
        return rWheelTravel; // in inches
    }

    /**
     * Get the net distance traveled by the left wheels of the robot
     */
    public double getLeftWheelTravel() {
        return lWheelTravel; // in inches
    }

    /**
     * Get an estimate of the robot's position at a given time
     */
    public RobotPos getPositionAtTime(double time) {
        mutex.lock();
        try {
            return map.getRobotPositionAtTime(time);
        } finally {
            mutex.unlock();
        }
    }
    
    /**
     * Gets the displacement of the robot since the given time
     * 
     * @param time rio time in seconds
     * @return the change in position since the given time
     */
    public RobotPos getMovementSinceTime(double time) {
        mutex.lock();
        try {
            return map.getRobotMovementSinceTime(time);
        } finally {
            mutex.unlock();
        }
    }

    /**
     * get the latest available position from the position map
     */
    public RobotPos getPos() {
        mutex.lock();
        try {
            return map.getLastestFieldRelativePosition();
        } finally {
            mutex.unlock();
        }
    }

    /**
     * Recalculates the current position based on new info from the wheel encoders
     */
    public void recalcPositionEstimate() {
        mutex.lock();
        // when locked, continously update position
        try {
            this.updatePos(Robot.drivetrain.getRightPos(), Robot.drivetrain.getLeftPos(), Robot.getAngleRad());
        } finally {
            mutex.unlock();
        }
    }

    /**
     * Starts updating the position of the robot in an independent thread
     */
    public void start() {
        posUpdater.startPeriodic(Constants.kOdometryLoopTime);
    }
    
    /**
     * Stop the thread that updates the robot's position
     */
    public void end() {
        posUpdater.close();
    }

    /**
     * clear all updates from the position map and set the base of the map to zero
     */
    public void zero() {
        mutex.lock();
        try {
            map.clearAndSetPos(new RobotPos(0,0, 0, 0,0));
        } finally {
            mutex.unlock();
        }
    }

     /**
     * clear all updates from the position map and set the base of the map to zero
     */
    public void setPos(Point newPos) {
        mutex.lock();
        try {
            map.clearAndSetPos(new RobotPos(newPos.getX(), newPos.getY(), 0, 0,0));
        } finally {
            mutex.unlock();
        }
    }

}
