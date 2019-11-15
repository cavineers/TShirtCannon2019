package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cannon extends Subsystem {
	Solenoid sol;
	
	public Cannon(int channel) {
		sol = new Solenoid(0, channel);
    }
    
    public void initDefaultCommand() {
    	
	}

	public void setOpen(boolean state) {
		sol.set(state);
	}

	public boolean isOpen() {
		return sol.get();
	}
}

