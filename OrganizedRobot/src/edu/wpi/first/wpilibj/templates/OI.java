
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.JoyManipulator;

public class OI {
    //AxisCamera camera;
    private Jaguar leftFront;
    private Jaguar leftBack;
    private Jaguar rightFront;
    private Jaguar rightBack;
    private Joystick leftJoy;
    private Joystick rightJoy;
    private JoyManipulator curve;
    private Jaguar armMotor;
    private Joystick controller;
    private Jaguar conveyor;
    private Jaguar shooterMotor;
    private boolean pudding;
    private double oStart;
    public OI(){
        pudding = false;
        oStart = Timer.getFPGATimestamp();
        
        shooterMotor = new Jaguar(RobotMap.SHOOTERPORT);
        conveyor = new Jaguar(RobotMap.CONVEYORPORT);
        leftFront = new Jaguar(RobotMap.LEFTFRONTMOTORPORT);
        leftBack = new Jaguar(RobotMap.LEFTREARMOTORPORT);
        rightFront = new Jaguar(RobotMap.RIGHTFRONTMOTORPORT);
        rightBack = new Jaguar(RobotMap.RIGHTREARMOTORPORT);
        armMotor = new Jaguar(RobotMap.ARMMOTORPORT);
        
        leftJoy = new Joystick(RobotMap.LEFTJOYPORT);
        rightJoy = new Joystick(RobotMap.RIGHTJOYPORT);
        controller = new Joystick(RobotMap.CONTROLLERPORT);
        
        curve = new JoyManipulator();
        //camera = AxisCamera.getInstance();
    }
    
    public Jaguar getLeftFront(){
        return leftFront;
    }
    
    public Jaguar getLeftBack(){
        return leftBack;
    }
    
    public Jaguar getRightFront(){
        return rightFront;
    }
    
    public Jaguar getRightBack(){
        return rightBack;
    }
    
    public Jaguar getShooter(){
        return shooterMotor;
    }
    
    public Jaguar getArmMotor(){
        return armMotor;
    }
    
    public Jaguar getConveyor(){
        return conveyor;
    }
    
    public double getLeftPower(){
        return curve.getPower(leftJoy.getY());
    }
    public double getRightPower(){
        return curve.getPower(rightJoy.getY());
    }
    public double getScale(){
        return ((rightJoy.getZ()-1)/-2);
    }
    
    public boolean armUpButton(){
        if(controller.getZ()>0.1)
            return true;
        return false;
    }
    public boolean armDownButton(){
        if(controller.getZ()<-0.1)
            return true;
        return false;
    }
    public boolean centerButton(){ //button to center the robot
        if(controller.getRawButton(1)){
            System.out.println("Center button pressed.");
            return true;
        }
        return false;
    }
    
    public boolean shootButton(){
        if(controller.getRawButton(6))
            return true;
        return false;
    }
    
    public boolean loadBallButton(){
        if(controller.getRawButton(2))
            return true;
        return false;
    }
    
    public boolean override(){
        if(controller.getRawButton(3) && (Timer.getFPGATimestamp()-oStart>=.25)){
            oStart = Timer.getFPGATimestamp();
            pudding = !pudding;
        }
        return pudding;
    }
    public double shooterSpeed(){
        return controller.getY();
    }
    public double conveyorSpeed(){
        return controller.getThrottle();
    }
    public double getMagnitude(){
        return controller.getMagnitude();
    }
    
    
/*    public double getBalance(){ // Avery wrote this, don't delete it
        return (leftJoy.getZ());
    }*/
    // Process operator interface input here.
}

