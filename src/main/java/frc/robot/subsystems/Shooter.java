package frc.robot.subsystems;

import javax.print.attribute.SetOfIntegerSyntax;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.enums.*;


public class Shooter extends SubsystemBase {
    private final TalonSRX top;
    private final TalonSRX bottom;
    
    public Shooter(int topID, int bottomID){
        top = new TalonSRX(topID);
        bottom = new TalonSRX(bottomID);
    }

    public double regression(Double distance){
        if(distance == null){
            System.out.println("Turn on aim or goal not visible");
            return 0;
        } else {
            return 0.0; //TODO regressions
        }
    }

    @Override
    public void periodic(){
        if(RobotContainer.DRIVE_MODE == DRIVE_MODE.TELEOP_AIM){
            if (RobotContainer.getInstance().buttonBoard.getRawButton(Constants.revButton)){
                top.set(ControlMode.PercentOutput, regression(RobotContainer.getInstance().LimelightVision.getGoalHorizontalDistance()));
                bottom.set(ControlMode.PercentOutput, 0.75 * -regression(RobotContainer.getInstance().LimelightVision.getGoalHorizontalDistance()));
            }
        } else if(RobotContainer.DRIVE_MODE == DRIVE_MODE.AUTON_AIM){
            if(RobotContainer.getInstance().LimelightVision.autonShouldShoot()){
                top.set(ControlMode.PercentOutput, regression(RobotContainer.getInstance().LimelightVision.getGoalHorizontalDistance()));
                bottom.set(ControlMode.PercentOutput, 0.75 * -regression(RobotContainer.getInstance().LimelightVision.getGoalHorizontalDistance()));
            }
        } else {
            top.set(ControlMode.PercentOutput, 0); bottom.set(ControlMode.PercentOutput, 0);
        }
    }
}