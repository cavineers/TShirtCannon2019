package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

public class AnglePistons extends Subsystem {
    DoubleSolenoid sol;

	public AnglePistons() {
		sol = new DoubleSolenoid(Constants.kAnglePistonForward, Constants.kAnglePistonReverse);
	}
    public void initDefaultCommand() {
    	
    }
    
    public void setSolenoidState(boolean state){
    	if (state) {
    		sol.set(DoubleSolenoid.Value.kForward);
    	} else {
    		sol.set(DoubleSolenoid.Value.kReverse);
    	}
    	
    }
    
    public boolean getSolenoidState() {
        if (sol.get() == DoubleSolenoid.Value.kForward) {
            return true;
        } else {
            return false;
        }
    }
}