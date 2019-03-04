package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Launcher extends Subsystem {
  WPI_VictorSPX motor;
  DoubleSolenoid piston;

  public Launcher() {
    motor = new WPI_VictorSPX(RobotMap.CAN.kLauncher);
    piston = new DoubleSolenoid(RobotMap.PCM.kLauncherForward, RobotMap.PCM.kLauncherReverse);
  }

  
  public void runMotor(boolean direction) {
    if(direction)
      motor.set(RobotMap.Motors.kLauncherSpeedForward);
    else
      motor.set(-RobotMap.Motors.kLauncherSpeedReverse);
  }

  public void stopMotor() {
    motor.stopMotor();
  }

  public void togglePiston(Value v) {
    piston.set(v);
  }


  @Override
  public void initDefaultCommand() {}
}