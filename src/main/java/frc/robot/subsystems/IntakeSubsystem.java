// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.signals.InvertedValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.IntakeState;

public class IntakeSubsystem extends SubsystemBase {
  private TalonFX m_talonFXMotor;
  private SparkMax m_sparkMaxMotor;
  private DutyCycleOut m_dutyCycleRequest;
  private final Constants.IntakeConstants.MotorType m_motorType;
  private IntakeState m_currentState;

  public IntakeSubsystem() {
    m_motorType = Constants.IntakeConstants.kMotorType;
    
    if (m_motorType == Constants.IntakeConstants.MotorType.TALONFX) {
      m_talonFXMotor = new TalonFX(Constants.IntakeConstants.kIntakeMotorID);
      m_dutyCycleRequest = new DutyCycleOut(0);
      
      MotorOutputConfigs motorConfig = new MotorOutputConfigs();
      motorConfig.Inverted = Constants.IntakeConstants.kIntakeInverted 
          ? InvertedValue.Clockwise_Positive 
          : InvertedValue.CounterClockwise_Positive;
      m_talonFXMotor.getConfigurator().apply(motorConfig);
    } else {
      m_sparkMaxMotor = new SparkMax(Constants.IntakeConstants.kIntakeMotorID, SparkLowLevel.MotorType.kBrushless);
      m_dutyCycleRequest = null;
      
      SparkMaxConfig config = new SparkMaxConfig();
      config.inverted(Constants.IntakeConstants.kIntakeInverted);
      m_sparkMaxMotor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }
  }

  public void setState(IntakeState state) {
    m_currentState = state;
    
    if (m_motorType == Constants.IntakeConstants.MotorType.TALONFX) {
      m_talonFXMotor.setControl(m_dutyCycleRequest.withOutput(state.getSpeed()));
    } else {
      m_sparkMaxMotor.set(state.getSpeed());
    }
  }

  public void input() {
    setState(IntakeState.INPUT);
  }

  public void reverse() {
    setState(IntakeState.REVERSE);
  }

  public void stop() {
    setState(IntakeState.STOP);
  }

  public IntakeState getState() {
    return m_currentState;
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}
}

