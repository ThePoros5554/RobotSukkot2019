package poroslib.commands;

import edu.wpi.first.wpilibj.command.Command;
import poroslib.triggers.SmartJoystick;

/**
 *
 */
public class DisableJoyAxis extends Command {

	private SmartJoystick joy;
	private int axis;
	private boolean isDisabled;
	
    public DisableJoyAxis(SmartJoystick joy, int axis, boolean isDisabled) 
    {    
    	this.joy  = joy;
    	this.axis = axis;
    	this.isDisabled = isDisabled;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	this.joy.disableAxis(this.axis, this.isDisabled);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
