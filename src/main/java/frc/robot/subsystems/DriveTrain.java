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
import frc.robot.OI;
import frc.robot.RobotMap;
import poroslib.commands.CurvatureDrive;
import poroslib.subsystems.DiffDrivetrain;
/**
 * Add your docs here.
 */
public class DriveTrain extends DiffDrivetrain {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX masterLeft;
  private WPI_TalonSRX masterRight;
  private WPI_TalonSRX middleLeft;
  private WPI_TalonSRX middleRight;
  private WPI_VictorSPX rearLeft;
  private WPI_VictorSPX rearRight;

  private NeutralMode neutralMode = NeutralMode.Brake;

  public DriveTrain()
  {
    super(new WPI_TalonSRX(RobotMap.kFrontLeftPort), new WPI_TalonSRX(RobotMap.kFrontRightPort), false);

    masterLeft = (WPI_TalonSRX)leftController;
    masterRight = (WPI_TalonSRX)rightController;
    middleLeft = new WPI_TalonSRX(RobotMap.kMiddleLeftPort);
    middleRight = new WPI_TalonSRX(RobotMap.kMiddleRightPort);
    rearLeft = new WPI_VictorSPX(RobotMap.kRearLeftPort);
    rearRight = new WPI_VictorSPX(RobotMap.kRearRightPort);

    middleLeft.follow(masterLeft);
    rearLeft.follow(masterLeft);
    middleRight.follow(masterRight);
    rearRight.follow(masterRight);

    masterLeft.setInverted(InvertType.None);
    masterRight.setInverted(InvertType.None);
    middleLeft.setInverted(InvertType.FollowMaster);
    middleRight.setInverted(InvertType.FollowMaster);
    rearLeft.setInverted(InvertType.FollowMaster);
    rearRight.setInverted(InvertType.FollowMaster);

    setNeutralMode(neutralMode);

    masterRight.set(ControlMode.PercentOutput, 0);
    masterLeft.set(ControlMode.PercentOutput, 0);

    this.setRotateDeadband(0.2);

    this.masterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.masterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.masterLeft.setSensorPhase(true);
    this.masterRight.setSensorPhase(true);
  }

  public void setNeutralMode(NeutralMode neutralMode)
  {
    this.masterLeft.setNeutralMode(neutralMode);
    this.masterRight.setNeutralMode(neutralMode);
    this.middleLeft.setNeutralMode(neutralMode);
    this.middleRight.setNeutralMode(neutralMode);
    this.rearLeft.setNeutralMode(neutralMode);
    this.rearRight.setNeutralMode(neutralMode);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CurvatureDrive(this, OI.driverJoy, 0.6, 1.0, 0.3));
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
