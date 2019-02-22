package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Lift extends Subsystem {
  public WPI_TalonSRX talon;

  public Lift() {
    talon = new WPI_TalonSRX(RobotMap.CAN.kElevator);
    talon.setSafetyEnabled(false);

    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    talon.setSelectedSensorPosition(0);
    talon.setSensorPhase(true);
  }

  public void resetStartValue() {
    talon.setSelectedSensorPosition(0);
  }

  public void posControl(double target) {
    talon.set(ControlMode.Position, (int)(target*4096));
  }

  public void stop() {
    talon.stopMotor();
  }

  public int getCurrentPosition() {
    return talon.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {}
}
