/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ExampleCommand;
import edu.wpi.first.wpilibj.templates.commands.JoyDrive;
import edu.wpi.first.wpilibj.templates.subsystems.ArmMotor;
import edu.wpi.first.wpilibj.templates.subsystems.Centering;
import edu.wpi.first.wpilibj.templates.subsystems.Shooting;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    DriveTrain go;
    AxisCamera cam;
    Command autonomousCommand;
    JoyDrive drive;
    DriverStationLCD display;
    OI oi;
    ArmMotor arm;
    int number;
    Centering cent;
    Shooting shoot;
    boolean overridden;
    double aStartTime;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
//        System.out.println("initializing...");
        oi = new OI();
        // instantiate the command used for the autonomous period
        shoot = new Shooting(oi.getConveyor(), oi.getShooter());
        cam = AxisCamera.getInstance("10.40.97.11");
        cent = new Centering(oi.getLeftFront(), oi.getLeftBack(), oi.getRightFront(), oi.getRightBack(), cam);
        autonomousCommand = new ExampleCommand();
        drive = new JoyDrive(oi.getLeftFront(), oi.getLeftBack(), oi.getRightFront(), oi.getRightBack(), oi);
        display = DriverStationLCD.getInstance();
        go = drive.go;
        // Initialize all subsystems
        CommandBase.init();
        arm = new ArmMotor(oi.getArmMotor());
    }

    public void autonomousInit() {
        aStartTime = Timer.getFPGATimestamp();
//        number =cent.continuous(true);
        // schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        if(Timer.getFPGATimestamp()-aStartTime<5)
            go.drive(-.3, .3);
        else go.drive(0, 0);
//        cent.continuous(false);
//        if((Timer.getFPGATimestamp()-aStartTime>5))
//            shoot.continuous(true, false, number, false, 0, 0);
//        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() { 
//        System.out.println("Periodic.");
//        if(oi.override())
//            System.out.println("overridden");
        overridden = oi.override();
//        if(overridden)
//            System.out.println("overridden.");
        if(overridden){
            display.println(DriverStationLCD.Line.kUser4, 1, "Shooter at: "+oi.shooterSpeed());
            display.println(DriverStationLCD.Line.kUser5, 1, "Conveyor at: "+oi.conveyorSpeed());
            display.println(DriverStationLCD.Line.kUser6, 1, "Magnitude: "+oi.getMagnitude());
            display.println(DriverStationLCD.Line.kUser3, 1, "Override Mode ACTIVATED!");
        }
        else{
            display.println(DriverStationLCD.Line.kUser4, 1, "");
            display.println(DriverStationLCD.Line.kUser5, 1, "");
            display.println(DriverStationLCD.Line.kUser6, 1, "");
            display.println(DriverStationLCD.Line.kUser3, 1, "Override Mode Deactivated.");
         
        }
//        System.out.println("checking for centerbutton.");
//        if(oi.centerButton())
//            System.out.println("Got center button.");
        number = cent.continuous(oi.centerButton());
        display.println(DriverStationLCD.Line.kMain6, 1, "Distance: "+number);
        try{
            System.out.println("Trying shooter continuous.");
            shoot.continuous(oi.shootButton(), oi.loadBallButton(), number,overridden, oi.shooterSpeed(), oi.conveyorSpeed());
        }                   
        catch(Exception e){
            System.out.println("Error in shooter continuous: "+e.getMessage());
        }

        display.println(DriverStationLCD.Line.kUser2, 1, "Throttle at: "+oi.getScale());
        Scheduler.getInstance().run();
        try{
            arm.continuous(oi.armUpButton(), oi.armDownButton());
        }

        catch(Exception e){
            System.out.println("Error in arm continuous: "+e.getMessage());
        }
        drive.drive();
        display.updateLCD();
    }
//    public void teleopContinuous(){
////        try{
////            arm.continuous(oi.armUpButton(), oi.armDownButton());
////        }
////
////        catch(Exception e){
////            System.out.println("Error in arm continuous: "+e.getMessage());
////        }
////        drive.drive();
//
//    }
}