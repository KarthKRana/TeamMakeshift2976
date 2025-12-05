// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//Hehe
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

//Nested Command Class for the Shooter >:)
public class ShooterCommands {

  public static class Fire extends Command {
    private final ShooterSubsystem m_shooterSubsystem;

    public Fire(ShooterSubsystem shooterSubsystem) {
      m_shooterSubsystem = shooterSubsystem;
      addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
      m_shooterSubsystem.fire();
    }

    @Override
    public void execute() {
      m_shooterSubsystem.fire();
    }

    @Override
    public void end(boolean interrupted) {
      m_shooterSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
      return false;
    }
  }

  public static class Stop extends Command {
    private final ShooterSubsystem m_shooterSubsystem;

    public Stop(ShooterSubsystem shooterSubsystem) {
      m_shooterSubsystem = shooterSubsystem;
      addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
      m_shooterSubsystem.stop();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
      return true;
    }
  }

  public static class IncreaseSpeed extends Command {
    private final ShooterSubsystem m_shooterSubsystem;

    public IncreaseSpeed(ShooterSubsystem shooterSubsystem) {
      m_shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
      m_shooterSubsystem.increaseSpeed();
    }

    @Override
    public boolean isFinished() {
      return true;
    }
  }

  public static class DecreaseSpeed extends Command {
    private final ShooterSubsystem m_shooterSubsystem;

    public DecreaseSpeed(ShooterSubsystem shooterSubsystem) {
      m_shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
      m_shooterSubsystem.decreaseSpeed();
    }

    @Override
    public boolean isFinished() {
      return true;
    }
  }
}

