/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.*;
/**
 *
 * @author team4097
 */
public class Centering {
    private ColorImage image;
    private int leftRight;
    private ParticleAnalysisReport r;
    private Distance dist;
    private int offset;
    private int distance;
    private Jaguar leftFront;
    private Jaguar leftBack;
    private Jaguar rightFront;
    private Jaguar rightBack;
    private double speed;
    double startTime;
    double startCenterTime;
    boolean turn;
    double turningTime;
    AxisCamera cam;
    boolean centering;
    public Centering(Jaguar leftF, Jaguar leftB, Jaguar rightF, Jaguar rightB, AxisCamera camera){
        speed = .14;
        turn = false;
        centering = false;
        leftFront = leftF;
        leftBack = leftB;
        rightFront = rightF;
        rightBack = rightB;
        cam = camera;
        dist = new Distance();
    }
    
    private void turnStop(){
        leftFront.set(0);
        leftBack.set(0);
        rightFront.set(0);
        rightBack.set(0);
        turn = false;
    }
    
    private void turnStart(double t, int direction){
        turningTime = t;
        startTime = Timer.getFPGATimestamp();
        turn = true;
        leftFront.set(direction*speed);
        leftBack.set(direction*speed);
        rightFront.set(direction*speed);
        rightBack.set(direction*speed);
    }
    private void tryCenter(int offset, int direction){ //-1 for left 1 for right
            if(offset>50)
                turnStart(1, direction);
            else if(offset<40)
                turnStart(.75, direction);
            else if(offset<30)
                turnStart(.5, direction);
            else if(offset<20)
                turnStart(.25, direction);
            else if(offset<15)
                turnStart(.1, direction);
            else if(offset<10)
                turnStart(.075, direction);
            else if(offset<5){
                turn = false;
        }
    }
    public void center(){
        //pre: box is in field of view
        //post: returns distance and puts box in center of view
        try{
            System.out.println("Starting centering...");
            image = cam.getImage();
            System.out.println("Got image.");
            r = dist.getReport(image);
            System.out.println("Got report.");
            offset = dist.getOffset(r);
            System.out.println("offset: "+offset);
            leftRight = dist.leftOrRight(r);//1 = right, -1 = left
            System.out.println("LeftRight: "+leftRight);
            distance = dist.getDistance(r);
            System.out.println("Distance: "+distance);
            centering = true;
//            image.image.clear();
            image.free();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in the center() function.");
        }
    }
    
        private boolean centerLoop()
        {
            try{
    //            startCenterTime = Timer.getFPGATimestamp();
                tryCenter(offset, leftRight);
                image = cam.getImage();
                r = dist.getReport(image);
                offset = dist.getOffset(r);
                leftRight = dist.leftOrRight(r);
                distance = dist.getDistance(r);
    //                image.image.clear();
                image.free();
                if(offset<5){
                    centering = false;
                    return true;
                }
            }
            catch(Exception e){
                
            }
            return false;
        }

    public int continuous(boolean one){
        if(turn && (Timer.getFPGATimestamp()-startTime)>=turningTime){
            turnStop();
        }
            
//        if(one)
//            center();
        
        if(centering && centerLoop())
            return distance;
        
        return 0;
    }
    
    private class Distance {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
        ParticleAnalysisReport rep[];
        int p;
        public ParticleAnalysisReport r;
        Joystick joy;
        ColorImage image;
        Joystick leftJoy;
        BinaryImage thresh;
        ColorImage before;
        CriteriaCollection cc;
        Runtime run;
        int goal;
        int distance;
    
    
        public Distance() {
            run = Runtime.getRuntime();
    //        joy = new Joystick(1);
    //        leftJoy = new Joystick(1);
            cc = new CriteriaCollection();
            cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 170, false);
            cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 40, 260, false);
        }

        /**
        * This function is called periodically during autonomous
        */

        /**
        * This function is called periodically during operator control
        */
        public int getOffset(ParticleAnalysisReport r){
            return Math.abs(1/2*r.imageWidth-r.center_mass_x);
        }
        public int leftOrRight(ParticleAnalysisReport r){
            if(r.center_mass_x>1/2*r.imageWidth)
                return 1;
            return -1;
        }
        public ParticleAnalysisReport getReport(ColorImage image){
            try{
                    goal = 0;
                    before = image;
                    thresh = image.thresholdRGB(0, 130, 120, 255, 160, 255);
                    thresh = thresh.removeSmallObjects(false, 2);
                    thresh = thresh.convexHull(true);
                    thresh = thresh.particleFilter(cc);
                    rep = thresh.getOrderedParticleAnalysisReports();
                    for(int i = 0; i<rep.length; i++){
                        if(rep[i].center_mass_y>goal){
                            p = i;
                            r = rep[i];
                            goal = (rep[i].center_mass_y);

                        }
                    }
                image.image.clear();
                thresh.image.clear();
                before.image.clear();
                image.free();
                thresh.free();
                before.free();
            }
            catch(Exception e){
            }
            return r;
        }
        public int getDistance(ParticleAnalysisReport r) {
            distance = 15000/r.boundingRectHeight;
            return distance;
        }
    }

}
