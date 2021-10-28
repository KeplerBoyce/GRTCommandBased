// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.swerve.DriveDistanceCommand;
import frc.robot.commands.swerve.DriveTimeCommand;
import frc.robot.commands.swerve.TurnCommand;
import frc.robot.subsystems.SwerveSubsystem;

public class TextFileCommands extends SequentialCommandGroup {
    /**
     * Creates a new TextFileCommands.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TextFileCommands(SwerveSubsystem swerveSubsystem) {

        //handle possible file not found and io exceptions
        try {
            //reading lines from text file
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\frc\\robot\\commands\\testcommands.txt"));
            Stream<String> lines = br.lines();

            //go through lines one by one and add commands accordingly
            lines.forEach(line -> {
                //split lines into parts (separated in text file by spaces)
                String[] parts = line.split(" ");
                //for turn commands: parts = ["turn", direction, degrees]
                //for drive commands: parts = ["drive", direction, mode(distance or time), meters/seconds]
                
                //check if this line is a turn or a drive command
                switch (parts[0]) {
                    case "turn":
                        //add turn command (params explained above)
                        addCommands(new TurnCommand(swerveSubsystem, parts[1], Float.parseFloat(parts[2])));
                        break;
                    case "drive":
                        //check if driving by distance or by time
                        if (parts[2].equals("distance")) {
                            //add drive distance command (params explained above)
                            addCommands(new DriveDistanceCommand(swerveSubsystem, parts[1], Float.parseFloat(parts[3])));
                        } else if (parts[2].equals("time")) {
                            //add drive time command (params explained above)
                            addCommands(new DriveTimeCommand(swerveSubsystem, parts[1], Float.parseFloat(parts[3])));
                        }
                        break;
                }
            });
            //close buffered reader at the end
            br.close();
        //exceptions
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch(IOException e) {
            System.out.println("Buffered reader is closed");
        }
    }
}
