package com.ibm.etools.common.command;

import com.ibm.etools.gef.commands.AbstractCommand;

/**
 * This class provides a singleton {@link UnexecutableCommand#INSTANCE} that cannot execute.
 */
public class UnexecutableCommand
	extends AbstractCommand {

public static final UnexecutableCommand INSTANCE = new UnexecutableCommand();

private UnexecutableCommand() {}

public boolean canExecute() {
	return false;
}

public boolean canUndo() {
	return false;
}

public void execute() {}

public void redo() {}

}
