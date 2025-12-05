// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommands {
  public static class Input extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public Input(IntakeSubsystem intakeSubsystem) {
      m_intakeSubsystem = intakeSubsystem;
      addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
      m_intakeSubsystem.input();
    }

    @Override
    public void execute() {
      m_intakeSubsystem.input();
    }

    @Override
    public void end(boolean interrupted) {
      m_intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
      return false;
    }
  }

  public static class Reverse extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public Reverse(IntakeSubsystem intakeSubsystem) {
      m_intakeSubsystem = intakeSubsystem;
      addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
      m_intakeSubsystem.reverse();
    }

    @Override
    public void execute() {
      m_intakeSubsystem.reverse();
    }

    @Override
    public void end(boolean interrupted) {
      m_intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
      return false;
    }
  }

  public static class Stop extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public Stop(IntakeSubsystem intakeSubsystem) {
      m_intakeSubsystem = intakeSubsystem;
      addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
      m_intakeSubsystem.stop();
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
}

