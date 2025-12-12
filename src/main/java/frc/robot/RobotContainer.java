// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ShooterCommands;
import frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(0);

  private final ShooterCommands.Fire m_shooterFireCommand;
  private final ShooterCommands.Stop m_shooterStopCommand;
  private final ShooterCommands.IncreaseSpeed m_shooterIncreaseSpeedCommand;
  private final ShooterCommands.DecreaseSpeed m_shooterDecreaseSpeedCommand;
  private final IntakeCommands.Input m_intakeInputCommand;
  private final IntakeCommands.Reverse m_intakeReverseCommand;
  private final IntakeCommands.Stop m_intakeStopCommand;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_shooterFireCommand = new ShooterCommands.Fire(m_shooterSubsystem);
    m_shooterStopCommand = new ShooterCommands.Stop(m_shooterSubsystem);

    m_shooterIncreaseSpeedCommand = new ShooterCommands.IncreaseSpeed(m_shooterSubsystem);
    m_shooterDecreaseSpeedCommand = new ShooterCommands.DecreaseSpeed(m_shooterSubsystem);

    m_intakeInputCommand = new IntakeCommands.Input(m_intakeSubsystem);
    m_intakeReverseCommand = new IntakeCommands.Reverse(m_intakeSubsystem);
    m_intakeStopCommand = new IntakeCommands.Stop(m_intakeSubsystem);

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_driverController.rightTrigger().whileTrue(m_shooterFireCommand);
    m_driverController.rightTrigger().onFalse(m_shooterStopCommand);

    m_driverController.povUp().onTrue(m_shooterIncreaseSpeedCommand);
    m_driverController.povDown().onTrue(m_shooterDecreaseSpeedCommand);

    m_driverController.rightBumper().whileTrue(m_intakeInputCommand);
    m_driverController.rightBumper().onFalse(m_intakeStopCommand);

    m_driverController.leftBumper().whileTrue(m_intakeReverseCommand);
    m_driverController.leftBumper().onFalse(m_intakeStopCommand);

    m_driverController.a().onTrue(m_intakeStopCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // No autonomous command configured yet
    return null;
  }
}
