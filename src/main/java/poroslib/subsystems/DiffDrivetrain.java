package poroslib.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import poroslib.systems.DifferentialDriver;
import poroslib.util.MathHelper;

/**
 *
 */
public abstract class DiffDrivetrain extends Drivetrain implements PidActionSubsys
{
	private DifferentialDriver driver;
	private boolean isSquared;
	
	private double rotateDeadband = 0;
	private double speedDeadband = 0;
	
	protected SpeedController leftController;
	protected SpeedController rightController;
	
    public DiffDrivetrain(SpeedController leftController, SpeedController rightController)
    {
			this.leftController = leftController;
			this.rightController = rightController;
    	driver = new DifferentialDriver(leftController , rightController);
		}
    
    public DiffDrivetrain(SpeedController leftController, SpeedController rightController, boolean isReversed)
    {
			this.leftController = leftController;
			this.rightController = rightController;
    	driver = new DifferentialDriver(leftController , rightController);
    	this.SetIsReversed(isReversed);
    }   
    
    public void arcadeDrive(double speed, double rotate, double maxOutput)
    {
    	this.driver.setMaxOutput(maxOutput);
    	
    	rotate = MathHelper.handleDeadband(rotate, rotateDeadband);
    	
    	if (this.IsReversed())
    	{
    		driver.arcadeDrive(-speed,-rotate, this.isSquared);
    	}
    	else
    	{
    		driver.arcadeDrive(speed, rotate, this.isSquared);
    	}
		}
		
		public void curvatureDrive(double speed, double rotate, boolean rotateInPlace, double maxOutput)
    {
			this.driver.setMaxOutput(maxOutput);

			this.driver.curvatureDrive(speed, rotate, rotateInPlace);
    }
    
    public void tankDrive(double leftSpeed ,double rightSpeed, double maxOutput)
    {
    	this.driver.setMaxOutput(maxOutput);
    	
    	if (Math.abs(Math.abs(leftSpeed) - Math.abs(rightSpeed)) < rotateDeadband && Math.signum(leftSpeed) + Math.signum(rightSpeed) != 0)
    	{
    		double speed = Math.signum(leftSpeed)*(Math.abs(leftSpeed) + Math.abs(rightSpeed)) / 2;
    		leftSpeed = speed;
    		rightSpeed = speed;
    	}
    	
    	if (this.IsReversed())
    	{
        	driver.tankDrive(-leftSpeed , -rightSpeed, this.isSquared);
    	}
    	else
    	{
        	driver.tankDrive(leftSpeed , rightSpeed, this.isSquared);
    	}
	}
	
	public void GTADrive(double forwardSpeed, double backwardsSpeed, double turn, double pow)
	{    
	  double kPow = pow;	
	  double speed = forwardSpeed - backwardsSpeed;
	  double leftSpeed = Math.signum(speed) * Math.pow(Math.abs(speed), kPow);
	  double rightSpeed = Math.signum(speed) * Math.pow(Math.abs(speed), kPow);
	  turn = MathHelper.handleDeadband(turn, rotateDeadband);
  
	  this.driver.setMaxOutput(1);

	  if (turn < 0)
	  {
		leftSpeed *=  Math.pow(MathHelper.mapRange(0, -1, 1, 0, turn), kPow);
	  }
	  else
	  {
		rightSpeed *= Math.pow(MathHelper.mapRange(0, 1, 1, 0, turn), kPow);
	  }
	  
	  if (this.IsReversed())
	  {
		driver.tankDrive(-leftSpeed, -rightSpeed);
	  }
	  else
	  {
		driver.tankDrive(leftSpeed, rightSpeed);
	  }
	}
    
    public abstract int getRawLeftPosition();
    
    public abstract int getRawRightPosition();
    
    public abstract double getHeading();
    
    public void SetIsSquared(boolean isSquared)
    {
    	this.isSquared = isSquared;
    }
    
    public boolean GetIsSquared()
    {
    	return this.isSquared;
    }
    
    public void PidTurnInPlace(double output)
    {
    	driver.PidTurnInPlace(output);
    }
    
    public void PidDrive(double output)
    {
    	driver.PidDrive(output);
    }
    
    public void setRotateDeadband(double deadband)
    {
    	this.rotateDeadband = deadband;
    }
    
    public double getRotateDeadband()
    {
    	return this.rotateDeadband;
    }
		
		public void setSpeedDeadband(double deadband)
    {
    	this.speedDeadband = deadband;
    }
    
    public double getSpeedDeadband()
    {
    	return this.speedDeadband;
		}
		
	@Override
	public void StopSystem()
	{
		this.driver.stopMotor();
	}

	@Override
	public Subsystem GetSubsystem() 
	{
		return this;
	}
}



