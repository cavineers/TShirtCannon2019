/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // CAN IDs

  //drive motors
//   public static int rightDriveMotor1 = 1; //talon (cim) (40A)
//   public static int rightDriveMotor2 = 2; //talon (cim) (40A)
//   public static int leftDriveMotor1  = 7; //talon (cim) (40A)
//   public static int leftDriveMotor2  = 4; //talon (cim) (40A)

  //   TEST 2
  public static int rightDriveMotor1 = 7; //talon (cim) (40A)
  public static int rightDriveMotor2 = 4; //talon (cim) (40A)
  public static int leftDriveMotor1  = 1; //talon (cim) (40A)
  public static int leftDriveMotor2  = 2; //talon (cim) (40A)

  //elevator motors
  public static int elevatorMotor = 23; //sparkmax (neo) (40A)

  //cargo intake motor
  public static int intakeMoter = 22; //talon (775 pro) (30A)
  
  //climber winch motor
  public static int climberMotor = 21; //sparkmax (neo) (40A)

  // arm movement motor
  public static int armMotor = 20; // sparkmax (neo) (40A)

  // grabber intake motor
  public static int grabberIntake = 24; //talon (bag) (30A)

  //pneumatics:

  //PCM can IDs
  public static int PCM1 = 25;

  public static int PDP = 26;

  //PCM devices:

  //Drive Shifters (switches between high and low drive gears)
  public static int driveShifter1 = 6; //correct
  public static int driveShifter2 = 5;

  //Hatch Scoop (lowers and raises the hatch scooping arms)
  public static int hatchScoop1 = 29;
  public static int hatchScoop2 = 30;

  //Grabber (controls both hatch intake and ball hard stop)
  public static int grabber1 = 31; //correct
  public static int grabber2 = 32;

  //Cargo Intake (controls if the cargo intake arms are down or not)
  public static int cargoIntake1 = 33;
  public static int cargoIntake2 = 34;

  //Digital In/Output mapping

  //auto selection
  public static final int kAutoPos0 = 0;
  public static final int kAutoPos1 = 1;
  public static final int kAutoPos2 = 2;
  public static final int kAutoPos3 = 3;
  public static final int kAutoPos4 = 4;
  public static final int kDIO5 = 5;
  public static final int kGrabberHomeingLimitSwitch = 6;
  public static final int kElevatorLimitSwitch = 7;
  public static final int kGrabberHatchLimitSwitch = 8;
  public static final int kGrabberCargoLimitSwitch = 9;
  
}
