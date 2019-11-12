package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.AnglePistons;
import edu.wpi.first.wpilibj.command.Command;

public class PistonsUp extends Command {
	public PistonsUp() {
        requires(Robot.anglePistons);
        Robot.anglePistons.setSolenoidState(true);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		
    }

    public String test() {
        return "test";
    }
}
