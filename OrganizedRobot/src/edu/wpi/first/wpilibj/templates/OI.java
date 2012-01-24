
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.templates.commands.JoyManipulator;

public class OI {
    Joystick leftJoy;
    Joystick rightJoy;
    JoyManipulator curve;
    public OI(){
        leftJoy = new Joystick(RobotMap.LEFTJOYPORT);
        rightJoy = new Joystick(RobotMap.RIGHTJOYPORT);
        curve = new JoyManipulator();
    }
    public double getLeftPower(){
        return curve.getPower(leftJoy.getY());
    }
    public double getRightPower(){
        return curve.getPower(rightJoy.getY());
    }
    // Process operator interface input here.
}

