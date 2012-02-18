package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final int CONVEYORPORT = 6;
    public static final int SHOOTERPORT = 5;
    public static final int LEFTFRONTMOTORPORT = 1;
    public static final int LEFTREARMOTORPORT = 2;
    public static final int RIGHTFRONTMOTORPORT = 3;
    public static final int RIGHTREARMOTORPORT = 4;
    public static final int ARMMOTORPORT = 7;
    
    public static final int LEFTJOYPORT = 1;
    public static final int RIGHTJOYPORT = 2;
    public static final int CONTROLLERPORT = 3;
}

//Numbers for motors were changed: used to be LF:1 LR:3 RF:2 RR:4