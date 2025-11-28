// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.ShooterCommands;
import frc.robot.subsystems.ShooterSubsystem;
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

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.kDriverControllerPort);

  // Commands
  private final ShooterCommands.Fire m_shooterFireCommand;
  private final ShooterCommands.Reverse m_shooterReverseCommand;
  private final ShooterCommands.Stop m_shooterStopCommand;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Initialize commands
    m_shooterFireCommand = new ShooterCommands.Fire(m_shooterSubsystem);
    m_shooterReverseCommand = new ShooterCommands.Reverse(m_shooterSubsystem);
    m_shooterStopCommand = new ShooterCommands.Stop(m_shooterSubsystem);

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
    // Right trigger fires the shooter (when pressed beyond threshold)
    Trigger rightTrigger = new Trigger(() -> m_driverController.getRightTriggerAxis() > 0.1);
    rightTrigger.whileTrue(m_shooterFireCommand);
    rightTrigger.onFalse(m_shooterStopCommand);

    // Left trigger reverses the shooter slowly (for removing items)
    Trigger leftTrigger = new Trigger(() -> m_driverController.getLeftTriggerAxis() > 0.1);
    leftTrigger.whileTrue(m_shooterReverseCommand);
    leftTrigger.onFalse(m_shooterStopCommand);
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
