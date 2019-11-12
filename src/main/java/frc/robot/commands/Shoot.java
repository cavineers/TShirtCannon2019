package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
    private Cannon cannon;
    private boolean fired;

	public Shoot(Cannon cannon) {
        this.cannon = cannon;
        this.fired = false;
	}

	protected void initialize() {
        setTimeout(0.3);
	}

	protected void execute() {
        cannon.setOpen(true);
	}

	protected boolean isFinished() {
        return isTimedOut();
	}

	protected void end() {
        cannon.setOpen(false);
    }
}