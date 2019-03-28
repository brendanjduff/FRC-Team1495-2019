package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends ADXRS450_Gyro  {

    public double getAngleDegrees() {
        return (int) getAngle() % 360 < 0 ? ((int) getAngle() % 360) + 360 : (int) getAngle() % 360;
    }

    public double getRawAngleDegrees() {
        return getAngle();
    }

    public double getAngleRadians() {
        return (double) ((int)(getAngle() % 360 < 0 ? ((int)getAngle() % 360) + 360 : (int)getAngle() % 360) / 180) * Math.PI;
    }

    public double getRawAngleRadians() {
        return (getAngle() / 180) * Math.PI;
    }
}