package com.seattlesolvers.solverslib.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandOpModeTest extends CommandOpMode {

    public static int x = 3;

    @Override
    public void initialize() {
        x = 3;
        Robot.enable();
        schedule(new CommandBase() {
            @Override
            public void execute() {
                x = 5;
            }
        });
    }

    @Test
    public void testRunOpMode() {
        initialize();
        run();
        assertEquals(5, x);
        reset();
    }
}
