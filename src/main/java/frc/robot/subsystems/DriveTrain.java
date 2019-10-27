/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import frc.robot.OI;
import frc.robot.RobotMap;
import poroslib.commands.ArcadeDrive;
import poroslib.commands.CurvatureDrive;
import poroslib.subsystems.DiffDrivetrain;
/**
 * Add your docs here.
 */
public class DriveTrain extends DiffDrivetrain {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  /*private Victor masterLeft;
  private Victor masterRight;
  private Victor rearLeft;
  private Victor rearRight;*/

  private SpeedControllerGroup masterLeft;
  private SpeedControllerGroup masterRight;

  private NeutralMode neutralMode = NeutralMode.Brake;

  public DriveTrain()
  {
    super(new SpeedControllerGroup(new Victor(RobotMap.kFrontLeftPort), new Victor(RobotMap.kRearLeftPort)), new SpeedControllerGroup(new Victor(RobotMap.kFrontRightPort), new Victor(RobotMap.kRearRightPort)));

    masterLeft = (SpeedControllerGroup)leftController;
    masterRight = (SpeedControllerGroup)rightController;
    /*rearLeft = new Victor(RobotMap.kRearLeftPort);
    rearRight = new Victor(RobotMap.kRearRightPort);

    rearLeft.follow(masterLeft);
    rearRight.follow(masterRight);

    
    rearLeft.setInverted(false);
    rearRight.setInverted(false);*/
    
    masterLeft.setInverted(false);
    masterRight.setInverted(false);


    masterRight.set(0);
    masterLeft.set(0);

    this.setRotateDeadband(0.2);

    /*this.masterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.masterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.masterLeft.setSensorPhase(true);
    this.masterRight.setSensorPhase(true);*/
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive(this, OI.driverJoy));
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public int getRawLeftPosition() {
    return 0;
  }

  @Override
  public int getRawRightPosition() {
    return 0;
  }

  @Override
  public double getHeading() {
    return 0;
  }


}
