/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

/**
 *
 * @author team4097
 */
public class RpmCalc {
    public double getRPM(double distance){
        return 159.42663*distance-10277.6612;
        //fix this, fill in real RPM calc
    }
}
