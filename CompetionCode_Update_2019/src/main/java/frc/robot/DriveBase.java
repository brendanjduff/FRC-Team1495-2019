package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveBase {
    public static WPI_TalonSRX leftDriveMaster, leftDriveFollower, rightDriveMaster, rightDriveFollower;
    public static DifferentialDrive roboDrive;
    boolean reverseMode = false;
    boolean slowMode = false;
    boolean turboMode = false;

    public DriveBase() {
        leftDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveMaster);
        leftDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kLeftDriveFollower);
        rightDriveMaster = new WPI_TalonSRX(RobotMap.CAN.kRightDriveMaster);
        rightDriveFollower = new WPI_TalonSRX(RobotMap.CAN.kRightDriveFollower);


        //Config Encoders
        rightDriveMaster.configFactoryDefault();
        rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    
        rightDriveMaster.setSensorPhase(true);
        rightDriveMaster.setInverted(false);

        leftDriveMaster.configFactoryDefault();
        leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    
        leftDriveMaster.setSensorPhase(true);
        leftDriveMaster.setInverted(false);
        

        leftDriveMaster.setNeutralMode(NeutralMode.Brake);
        leftDriveFollower.setNeutralMode(NeutralMode.Brake);
        rightDriveMaster.setNeutralMode(NeutralMode.Brake);
        rightDriveFollower.setNeutralMode(NeutralMode.Brake);

        
        leftDriveMaster.configClosedloopRamp(0,0);
        leftDriveFollower.configClosedloopRamp(0,0);
        rightDriveMaster.configClosedloopRamp(0,0);
        rightDriveFollower.configClosedloopRamp(0,0);
        leftDriveMaster.configOpenloopRamp(0,0);
        leftDriveFollower.configOpenloopRamp(0,0);
        rightDriveMaster.configOpenloopRamp(0,0);
        rightDriveFollower.configOpenloopRamp(0,0);

        roboDrive = new DifferentialDrive(new SpeedControllerGroup(leftDriveMaster, leftDriveFollower),
                new SpeedControllerGroup(rightDriveMaster, rightDriveFollower));
    }

    public void drive(OI oi) {
        double triggerSum = oi.driver.getTriggerAxis(Hand.kRight) - oi.driver.getTriggerAxis(Hand.kLeft);
        if (reverseMode) {
            triggerSum = -triggerSum;
        }
        triggerSum -= oi.climber.getY();
        double rotationSum = oi.driver.getX(Hand.kLeft) + oi.climber.getX();

        if (turboMode) {
            roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kTurboSpeedMultiplier,
                    rotationSum * RobotMap.Motors.kRotationMultiplier);
        } else if (slowMode) {
            roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kSlowSpeedMultiplier,
                    rotationSum * RobotMap.Motors.kSlowRotationMultiplier);
        } else {
            roboDrive.arcadeDrive(triggerSum * RobotMap.Motors.kSpeedMultiplier,
                    rotationSum * RobotMap.Motors.kRotationMultiplier);
        }
    }

    public void toggleSlowMode() {
        slowMode = !slowMode;
    }

    public void setSlowMode(boolean v) {
        slowMode = v;
    }

    public boolean getSlowMode() {
        return slowMode;
    }

    public void toggleReverseMode() {
        reverseMode = !reverseMode;
    }

    public void setReverseMode(boolean v) {
        reverseMode = v;
    }

    public boolean getReverseMode() {
        return reverseMode;
    }

    public void toggleTurboMode() {
        turboMode = !turboMode;
    }

    public void setTurboMode(boolean v) {
        turboMode = v;
    }

    public boolean getTurboMode() {
        return turboMode;
    }
}