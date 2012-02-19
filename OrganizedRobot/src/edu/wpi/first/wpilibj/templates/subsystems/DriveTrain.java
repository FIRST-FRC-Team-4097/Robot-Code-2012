/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;
import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author team4097
 */
public class DriveTrain {
    Jaguar leftFront;
    Jaguar rightFront;
    Jaguar leftRear;
    Jaguar rightRear;
    
    public DriveTrain(Jaguar LF, Jaguar LB, Jaguar RF, Jaguar RB)
    {
        leftFront = LF;
        leftRear = LB;
        rightFront = RF;
        rightRear = RB;
    }
    public void drive(double leftPower, double rightPower){
        leftFront.set(leftPower);
        leftRear.set(leftPower);
        rightFront.set(rightPower);
        rightRear.set(rightPower);
    }
//    private void setLeft(double power){
//        leftFront.set(power);
//        leftRear.set(power);
//    }
//    private void setRight(double power){
//        rightFront.set(power);
//        rightRear.set(power);
//    }
}
