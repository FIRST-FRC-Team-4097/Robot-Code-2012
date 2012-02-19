/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates.subsystems;
import edu.wpi.first.wpilibj.Jaguar;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class ArmMotor{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    double fSpeed;
    double rSpeed;
    Jaguar jaguar;
    
    
    //DriverStationLCD display;
    public ArmMotor(Jaguar arm) {
        jaguar = arm;
        fSpeed = -1;
        rSpeed = -.4;
       
        //display = DriverStationLCD.getInstance();
    }

    private void armDown() {
        jaguar.set(fSpeed);
    }
    private void armUp(){
        jaguar.set(-rSpeed);
    }
    public void set(double num){
        jaguar.set(num);
    }
    public void continuous(boolean up, boolean down){
        if(down)
            armDown();
        else if(up)
            armUp();
        else 
            set(0);
    }
}

