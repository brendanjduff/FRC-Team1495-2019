    /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;





/**
 * Add your docs here.
 */
public class NavX {


    private AHRS nav;

    public NavX()
    {
        nav = new AHRS(Port.kUSB);
        
    }



    public double getYawDeg()
    {
        return nav.getAngle();
    }
    public double getPitchDeg()
    {
        return nav.getPitch();
    }

    public double getRoll()
    {
        return nav.getRoll();
    }

}
