/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author team4097
 */
public class Shooting {
//    AxisCamera cam;
    Jaguar convey;
//    Jaguar c2;
//    Jaguar conveyToShoot1;
//    Jaguar conveyToShoot2;
//    Joystick joy;
//    double distanceToTop;
//    double verticalDistance;
//    double rpm;
//    public double speed;
    int maxRPM;
    Jaguar shooterMotor;
    Timer timer;
    RpmCalc calc;
    boolean shootingGo;
    double time;
//    boolean readyToShoot;
    public Shooting(Jaguar conveyor, Jaguar shooter){
        shootingGo = false;
        convey = conveyor;
        shooterMotor = shooter;
        calc = new RpmCalc();
        maxRPM = 19500;
        calc = new RpmCalc();
        timer = new Timer();
//        c2 = new Jaguar(2);
//        conveyToShoot1 = new Jaguar(5);
//        conveyToShoot2 = new Jaguar(6);
    }
    public void shootStart(double speed){
        time = Timer.getFPGATimestamp();
        shootingGo = true;
        shooterMotor.set(speed);
    }
    public void shootStop(){
        shootingGo = false;
        loadBallStop();
        shooterMotor.set(0);
    }
    public void loadBallStart(){
        convey.set(-.5);
    }
    public void loadBallStop(){
        convey.set(0);
    }
    public double getSpeed(int distance){
        return (calc.getRPM(distance)/maxRPM);
    }
    
    public void continuous(boolean one, boolean two, int distance, boolean overridden, double orShooterSpeed, double orConveyorSpeed){
        if(!overridden){
            if(one){
                System.out.println("Shooting at speed: "+getSpeed(distance));
                shootStart(-1);
            }
            
            else if(two)
                loadBallStart();
            else loadBallStop();
            if(shootingGo && (Timer.getFPGATimestamp()-time>=.5))
                loadBallStart();
            if(shootingGo && (Timer.getFPGATimestamp()-time)>=3)
                shootStop();
        }

        else if(overridden) {
            shooterMotor.set(orShooterSpeed);
            convey.set(orConveyorSpeed);
        }

            
    }
}
