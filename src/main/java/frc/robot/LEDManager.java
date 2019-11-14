package frc.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class LEDManager {
    public enum LEDMode {
        SOLID_WHITE,
        SOLID_RED,
        FIRING_STRIPE,
        NORMAL
    }

    Relay R_underglowColor, R_firing;
    LEDMode underglowColor = LEDMode.SOLID_RED, firingColor = LEDMode.NORMAL;

    public LEDManager () {
        R_underglowColor = new Relay(0);
        R_firing = new Relay(1);
        
        this.setUnderglowColor(LEDMode.SOLID_RED);
        this.setFiringMode(LEDMode.NORMAL);
    }

    public void setUnderglowColor(LEDMode color) {
        switch (color) {
            case SOLID_RED:
                R_underglowColor.set(Value.kForward);
                this.underglowColor = LEDMode.SOLID_RED;
                break;
            case SOLID_WHITE:
                R_underglowColor.set(Value.kForward);
                this.underglowColor = LEDMode.SOLID_WHITE;
                break;
        }
    }

    public void setFiringMode(LEDMode color) {
        switch (color) {
            case FIRING_STRIPE:
                R_firing.set(Value.kForward);
                this.firingColor = LEDMode.FIRING_STRIPE;
                break;
            case NORMAL:
                R_firing.set(Value.kReverse);
                this.firingColor = LEDMode.NORMAL;
                break;
        }
    }

    public LEDMode getUnderglowColor() {
        return this.underglowColor;
    }
}