package frc.robot.sensors;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends ADXRS450_Gyro
{
  public double getDegrees() {
    double angle = this.getAngle();
    double boundedAngle = angle % 360;
    if (boundedAngle < 0) {
      boundedAngle += 360;
    }
    return boundedAngle;
  }
}
