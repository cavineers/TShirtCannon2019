package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
    private Cannon cannon;

	public Shoot(Cannon cannon) {
        this.cannon = cannon;
	}

	protected void initialize() {
        cannon.setOpen(true);
		setTimeout(0.3);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
        cannon.setOpen(false);
    }
}