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
import frc.robot.ShooterState;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private TalonFX m_talonFXMotor;
  private SparkMax m_sparkMaxMotor;
  private DutyCycleOut m_dutyCycleRequest;
  private final Constants.ShooterConstants.MotorType m_motorType;
  private boolean m_isFiring;
  private ShooterState m_currentShooterSpeed = ShooterState.SPEED_05; //Initial speed should be low

  public ShooterSubsystem() {
    m_motorType = Constants.ShooterConstants.kMotorType;
    
    if (m_motorType == Constants.ShooterConstants.MotorType.TALONFX) {
      m_talonFXMotor = new TalonFX(Constants.kMotorPort);
      m_dutyCycleRequest = new DutyCycleOut(0);
      
      MotorOutputConfigs motorConfig = new MotorOutputConfigs();
      motorConfig.Inverted = Constants.kShooterInverted 
          ? InvertedValue.Clockwise_Positive 
          : InvertedValue.CounterClockwise_Positive;
      m_talonFXMotor.getConfigurator().apply(motorConfig);

    } else {
      
      m_sparkMaxMotor = new SparkMax(Constants.kMotorPort, SparkLowLevel.MotorType.kBrushless);
      m_dutyCycleRequest = null;
      
      SparkMaxConfig config = new SparkMaxConfig();
      config.inverted(Constants.kShooterInverted);
      m_sparkMaxMotor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }
  }

  public void fire() {
    setSpeed(m_currentShooterSpeed.getSpeed());
    m_isFiring = true;
  }

  public void stop() {
    setSpeed(0.0);
    m_isFiring = false;
  }

  public void increaseSpeed() {
    m_currentShooterSpeed = m_currentShooterSpeed.increase();
    if (m_isFiring) {
      fire();
    }
  }

  public void decreaseSpeed() {
    m_currentShooterSpeed = m_currentShooterSpeed.decrease();
    if (m_isFiring) {
      fire();
    }
  }

  public ShooterState getCurrentShooterSpeed() {
    return m_currentShooterSpeed;
  }

  public boolean isFiring() {
    return m_isFiring;
  }

  private void setSpeed(double speed) {
    if (m_motorType == Constants.ShooterConstants.MotorType.TALONFX) {
      m_talonFXMotor.setControl(m_dutyCycleRequest.withOutput(speed));
    } else {
      m_sparkMaxMotor.set(speed);
    }
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {}
}

