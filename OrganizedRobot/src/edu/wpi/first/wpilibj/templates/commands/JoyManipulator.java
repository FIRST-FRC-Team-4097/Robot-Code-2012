/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
/**
 *
 * @author team4097
 */
public class JoyManipulator {
    final double boundary = .7071068118655;
    double result;
    public double getPower(double joyPower){
        return joyPower;
    }
    public double getPowerOff(double joyPower){
        if(Math.abs(joyPower)<=.01){
            result = 0;
        }
        else if(joyPower>1){
            result = 1;
        }
        else if(joyPower<-1){
            result = -1;
        }
        else if(joyPower<0 && joyPower>=-boundary){
            result = -(joyPower*joyPower);
        }
        else if(joyPower<=boundary && joyPower>0){
            result = (((1+boundary)*joyPower)+boundary);
        }
        else if(joyPower>0 && joyPower<boundary){
            result = joyPower*joyPower;
        }
        else if(joyPower>=boundary){
            result = ((1+boundary)*joyPower)-boundary;
       }
        else result = joyPower;
//        if (result>1)
//            result = 1;
//        if (result<-1)
//            result = -1;
        return result;
    }
}
