
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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


public class Intake extends SubsystemBase {
    public final Solenoid solenoid;
    public final TalonSRX intakeAxelController;


    public Intake(int axelControllerID, int solenoidID) {
        this.intakeAxelController = new TalonSRX(axelControllerID);
        solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, solenoidID);
    }

  @Override
  public void periodic() {
    if(RobotContainer.DRIVE_MODE == DRIVE_MODE.TELEOP_AIM || RobotContainer.DRIVE_MODE == DRIVE_MODE.TELEOP_DRIVE){
      if(RobotContainer.getInstance().buttonBoard.getRawButtonPressed(Constants.intakeRLButton))
        solenoid.set(!solenoid.get());
    
      intakeAxelController.set(ControlMode.PercentOutput, (RobotContainer.getInstance().buttonBoard.getRawButtonPressed(Constants.intakeButton)  ?  Constants.intakeSpeed : 0) 
                                                        + (RobotContainer.getInstance().buttonBoard.getRawButtonPressed(Constants.reverseButton) ? -Constants.intakeSpeed : 0));
    }
  }
}
