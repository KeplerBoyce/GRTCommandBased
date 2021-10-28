// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swerve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SwerveSubsystem;

/** An example command that uses an example subsystem. */
public class TextFileCommands extends SequentialCommandGroup {
    /**
     * Creates a new TextFileCommands.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TextFileCommands(SwerveSubsystem swerveSubsystem) {

        try {
            //reading lines from text file
            File f = new File("src\\main\\java\\frc\\robot\\commands\\swerve\\testcommands.txt");
            System.out.println(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\frc\\robot\\commands\\swerve\\testcommands.txt"));
            Stream<String> lines = br.lines();

            //go through lines one by one and add corresponding commands
            lines.forEach(line -> {
                //split lines into parts
                String[] parts = line.split(" ");
                //for turns: parts = ["turn", direction, degrees]
                //for drives: parts = ["drive", direction, mode(distance or time), meters/seconds]
                switch (parts[0]) {
                    case "turn":
                        //add command to turn as specified by this line
                        addCommands(new TurnCommand(swerveSubsystem, parts[1], Float.parseFloat(parts[2])));
                        break;
                    case "drive":
                        //add command to drive as specified by this line
                        if (parts[2].equals("distance")) {
                            addCommands(new DriveDistanceCommand(swerveSubsystem, parts[1], Float.parseFloat(parts[3])));
                        } else if (parts[2].equals("time")) {
                            addCommands(new DriveTimeCommand(swerveSubsystem, parts[1], Float.parseFloat(parts[3])));
                        }
                        break;
                }
            });
            br.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        } catch(IOException e) {
            System.out.println("Buffered reader is closed");
        }
    }
}
