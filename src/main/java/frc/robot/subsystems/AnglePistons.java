package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class AnglePistons extends Subsystem {
    DoubleSolenoid sol;
    
	public AnglePistons() {
		sol = new DoubleSolenoid(20,1,2);
    }
    
    public void initDefaultCommand() {
    	
    }
    
    public void setSolenoidState(boolean state){
    	if (state) {
    		sol.set(DoubleSolenoid.Value.kReverse);
    	} else {
    		sol.set(DoubleSolenoid.Value.kForward);
    	}
    	
    }
    
    public boolean getSolenoidState() {
    	if (sol.get() == DoubleSolenoid.Value.kReverse) return true;
    	return false;
    }
}