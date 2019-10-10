package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PistonsDown extends Command {
	public PistonsDown() {
		Robot.anglePistons.setSolenoidState(false);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		
    }
}
