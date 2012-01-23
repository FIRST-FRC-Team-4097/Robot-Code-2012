/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.camera.AxisCamera;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    Jaguar leftFrontMotor;
    Jaguar rightFrontMotor;
    Jaguar leftRearMotor;
    Jaguar rightRearMotor;
    Joystick leftJoy;
    Joystick rightJoy;
    DriverStationLCD display;
    RobotDrive drive;
    AxisCamera camera;
    JoyManipulator manipulator;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        manipulator = new JoyManipulator();
        
        leftFrontMotor = new Jaguar(1);
        rightFrontMotor = new Jaguar(3);
        leftRearMotor = new Jaguar(2);
        rightRearMotor = new Jaguar(4);
        
        leftJoy = new Joystick(1);
        rightJoy = new Joystick(2);
        
        display = DriverStationLCD.getInstance();
        camera = AxisCamera.getInstance();
        
        drive = new RobotDrive(leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        System.out.println("RobotInit");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {

    }
    public void autonomousContinuous() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() {
        System.out.println("teleopInit");
    }

    public void teleopContinuous(){
        System.out.println("teleopContin");
        //drive.tankDrive(leftJoy, rightJoy);
        leftFrontMotor.set(-manipulator.getPower(leftJoy.getY()));
        leftRearMotor.set(-manipulator.getPower(leftJoy.getY()));
        rightFrontMotor.set(manipulator.getPower(rightJoy.getY()));
        rightRearMotor.set(manipulator.getPower(rightJoy.getY()));
        display.println(DriverStationLCD.Line.kUser2, 1, "Left Joystick Power: "+leftJoy.getY());
        display.println(DriverStationLCD.Line.kUser3, 1, "Right Joystick Power: "+rightJoy.getY());
        display.updateLCD();
    }



    
}
