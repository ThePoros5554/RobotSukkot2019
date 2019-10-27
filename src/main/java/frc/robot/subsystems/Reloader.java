/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.sql.Time;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Reloader extends Subsystem {

  private DoubleSolenoid leftSolenoid;
  private DoubleSolenoid rightSolenoid;

  private DoubleSolenoid liveSolenoid;
  private double lastOpened;
  private double lastClosed;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Reloader(){
    leftSolenoid = new DoubleSolenoid(RobotMap.forwardLeftSolenoid, RobotMap.reverseLeftSolenoid);
    rightSolenoid = new DoubleSolenoid(RobotMap.forwardRightSolenoid, RobotMap.reverseRightSolenoid);

    liveSolenoid = rightSolenoid;
    lastOpened = Timer.getFPGATimestamp();
  }

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void toggleLiveSolenoid(){
    if(liveSolenoid == leftSolenoid){
      liveSolenoid = rightSolenoid;
    }
    else{
      liveSolenoid = leftSolenoid;
    }
  }

  public void openLive()
	{
    liveSolenoid.set(DoubleSolenoid.Value.kForward);
    lastOpened = Timer.getFPGATimestamp();
	}
	
	public void closeLive()
	{
    liveSolenoid.set(DoubleSolenoid.Value.kReverse);
    lastClosed = Timer.getFPGATimestamp();
	}
	
	public void offLive()
	{
		liveSolenoid.set(DoubleSolenoid.Value.kOff);
  }

  public double getLastOpened(){
    return lastOpened;
  }
  
  public double getLastClosed(){
    return lastClosed;
  }

  public DoubleSolenoid.Value getLiveValue(){
    return liveSolenoid.get();
  }

  public void openLeft()
	{
		leftSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closeLeft()
	{
		leftSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void offLeft()
	{
		leftSolenoid.set(DoubleSolenoid.Value.kOff);
  }
  
  public void openRight()
	{
		rightSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closeRight()
	{
		rightSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void offRight()
	{
		rightSolenoid.set(DoubleSolenoid.Value.kOff);
	}


}
