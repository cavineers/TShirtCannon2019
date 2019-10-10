package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PistonsUp extends Command {
	public PistonsUp() {
		Robot.anglePistons.setSolenoidState(true);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		
    }
}
