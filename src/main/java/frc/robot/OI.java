/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.commands.PistonsDown;
import frc.robot.commands.PistonsUp;
// import frc.robot.commands.Rumble;
// import frc.robot.commands.Rumble.ControllerSide;
import frc.robot.subsystems.AnglePistons;
import frc.robot.Robot;
import frc.robot.commands.Shoot;
import frc.robot.commands.TogglePistonsAngle;

public class OI {

    public static Joystick joy = new Joystick(0);
    public static JoystickButton a_button = new JoystickButton(joy, 1);
    public static JoystickButton b_button = new JoystickButton(joy, 2);
    public static JoystickButton x_button = new JoystickButton(joy, 3);
    public static JoystickButton y_button = new JoystickButton(joy, 4);

    public static JoystickButton l_bump = new JoystickButton(joy, 5);
    public static JoystickButton r_bump = new JoystickButton(joy, 6);
    public static JoystickButton left_middle = new JoystickButton(joy, 7);
    public static JoystickButton right_middle = new JoystickButton(joy, 8);
    public static JoystickButton left_stick = new JoystickButton(joy, 9);
    public static JoystickButton right_stick = new JoystickButton(joy, 10);

    public int lastDpad = -1;
    public boolean lastRightTrig = false;
    public boolean lastLeftTrig = false;

    public enum ControllerMapMode {
        FIRE, LOCKED, UNKNOWN
    }

    public ControllerMapMode controllerMode = ControllerMapMode.LOCKED;

    public OI() {
        a_button.whenPressed(new Command(){
            @Override
            protected void initialize() {
                // TODO: If we wan't the ability to toggle while in fire mode
                if (controllerMode == ControllerMapMode.LOCKED) {
                    Robot.anglePistons.setSolenoidState(!(Robot.anglePistons.getSolenoidState()));
                } else {
                    System.out.println("Can't toggle pistons. In fire mode");
                }
            }

            @Override
            protected boolean isFinished() {
                return true;
            }
        });
        // b_button.whenPressed();
        // x_button.whenPressed();
        // y_button.whenPressed();
        // left_middle.whenPressed();
        right_middle.whenPressed(new Command(){
            @Override
            protected void initialize() {
                if (controllerMode == ControllerMapMode.FIRE) {
                    System.out.println("Controller set to 'LOCKED' mode");
                    controllerMode = ControllerMapMode.LOCKED;
                } else {
                    System.out.println("Controller set to 'FIRE' mode");
                    controllerMode = ControllerMapMode.FIRE;
                }
            }

            @Override
            protected boolean isFinished() {
                return true;
            }
        });
    }

    public boolean isRightTriggerPressed() {
        double rightTrig = this.getJoystick().getRawAxis(3);
        return rightTrig > 0.9;
    }

    public boolean isLeftTriggerPressed() {
        double leftTrig = this.getJoystick().getRawAxis(2);
        return leftTrig > 0.9;
    }

    public void updatePeriodicCommands(){
        // if (lastRightTrig != isRightTriggerPressed()) {
        //     // the right trigger changed state
        //     lastRightTrig = isRightTriggerPressed();
        //     if (lastRightTrig) {
        //         // the right trigger is pressed
        //     }
        // }

        // if (lastLeftTrig != isLeftTriggerPressed()) {
        //     // the left trigger changed state
        //     lastLeftTrig = isLeftTriggerPressed();
        //     if (lastLeftTrig) {
        //         // the left trigger is pressed
        //         // new MoveGrabberAndElevator(ElevatorLevel.CARGOSHIP_TOP, GrabberPosition.EXTENDED, Grabber.MotorState.OFF).start();

        //     }
        // }

        if (lastDpad != joy.getPOV()) {
			switch (joy.getPOV()) {
                case 0: { //*TOP
                    break;
                }
                case 90: { //*RIGHT
                    if (controllerMode == ControllerMapMode.FIRE) {
                        Robot.rightCannon.setOpen(true); //? PCM ID MUST BE '0'
                        Timer.delay(0.2); //! WAS 0.3
                        Robot.rightCannon.setOpen(false);
                        controllerMode = ControllerMapMode.LOCKED;
                    } else {
                        System.out.println("Can't fire. Controller locked");
                        // new Rumble(0.5, ControllerSide.BOTH);
                    }
                    break;
                }
                case 180: { //*BOTTOM
                    break;
                }
                case 270: { //*LEFT
                    if (controllerMode == ControllerMapMode.FIRE) {
                        Robot.leftCannon.setOpen(true); //? PCM ID MUST BE '0'
                        Timer.delay(0.2); //! WAS 0.3
                        Robot.leftCannon.setOpen(false);
                        controllerMode = ControllerMapMode.LOCKED;
                    } else {
                        System.out.println("Can't fire. Controller locked");
                        // new Rumble(0.5, ControllerSide.BOTH);
                    }
                    break;
                }
			}
		}
		lastDpad = joy.getPOV();
    }

	public Joystick getJoystick() {
        return joy;
    }
    
    public double addDeadZone(double input) {
        if (Math.abs(input) <= .05)
            input = 0;
        else if (input < 0)
            input = -Math.pow(input, 2);
        else
            input = Math.pow(input, 2);
        return input;
    }

}
