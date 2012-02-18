/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.OI;
/**
 *
 * @author team4097
 */
public class JoyDrive extends CommandBase {
    OI oi;
    public DriveTrain go;
    JoyManipulator curve;
    
    public JoyDrive(Jaguar LF, Jaguar LB, Jaguar RF, Jaguar RB, OI op) {
        go = new DriveTrain(LF, LB, RF, RB);
        curve = new JoyManipulator();
        oi = op;
        //Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        go.drive(oi.getLeftPower(), oi.getRightPower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    public void drive(){
        go.drive(oi.getScale()*(oi.getLeftPower()), -oi.getScale()*(oi.getRightPower()));
    }
//    public void slowLeftDrive(){
//        go.setLeft(-.2);
//    }
//    public void slowRightDrive(){
//        go.setRight(.2);
//    }
}
