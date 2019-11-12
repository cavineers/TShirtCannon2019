package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.AnglePistons;

public class TogglePistonsAngle extends Command {
	public TogglePistonsAngle() {
		// requires(Robot.anglePistons);
	}

	protected void initialize() {
        System.out.println("Toggling Pistons");
		Robot.anglePistons.setSolenoidState(!(Robot.anglePistons.getSolenoidState()));
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}
}