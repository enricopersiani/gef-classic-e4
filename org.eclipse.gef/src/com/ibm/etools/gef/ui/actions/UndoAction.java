package com.ibm.etools.gef.ui.actions;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.text.MessageFormat;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.*;

import com.ibm.etools.gef.internal.GEFMessages;

import com.ibm.etools.common.command.*;

/**
 * An action to undo the last command.
 */
public class UndoAction
	extends StackAction
{

/**
 * Creates an <code>UndoAction</code> and associates it with the 
 * given editor.
 * 
 * @param editor The editor this action is associated with.
 */
public UndoAction(IEditorPart editor) {
	super(editor);
}

/**
 * Updates the labels and enabled state when the command stack is altered.
 *
 * @param e The <code>EventObject</code>.
 */
public void commandStackChanged(java.util.EventObject e) {
	refresh();
}

/**
 * Returns <code>true</code> if the {@link CommandStack} can undo
 * the last command.
 */
protected boolean calculateEnabled(){
	return getCommandStack().canUndo();
}

/**
 * Initializes this action's text and images.
 */
protected void init(){
	super.init();
	setToolTipText(MessageFormat.format(
			GEFMessages.UndoAction_ToolTipText,	
			new Object[] {""}).trim());  //$NON-NLS-1$
	setText(MessageFormat.format(
			GEFMessages.UndoAction_ActionLabelText, 
			new Object[] {""}).trim()  //$NON-NLS-1$
			+ GEFMessages.UndoAction_ActionShortcutText);
	setId(org.eclipse.ui.IWorkbenchActionConstants.UNDO);
	setHoverImageDescriptor(
		WorkbenchImages.getImageDescriptor(
			IWorkbenchGraphicConstants.IMG_CTOOL_UNDO_EDIT_HOVER));
	setImageDescriptor(
		WorkbenchImages.getImageDescriptor(
			IWorkbenchGraphicConstants.IMG_CTOOL_UNDO_EDIT));
	setDisabledImageDescriptor(
		WorkbenchImages.getImageDescriptor(
			IWorkbenchGraphicConstants.IMG_CTOOL_UNDO_EDIT_DISABLED));	
}

/**
 * Refreshes this action's text to use the last executed command's label.
 */
protected void refresh(){
	Command undoCmd = getCommandStack().getUndoCommand();
	setToolTipText(MessageFormat.format(
			GEFMessages.UndoAction_ToolTipText,
			new Object []{getLabelForCommand(undoCmd)}).trim());
	setText(MessageFormat.format(
			GEFMessages.UndoAction_ActionLabelText,
			new Object []{getLabelForCommand(undoCmd)}).trim()
			+ GEFMessages.UndoAction_ActionShortcutText);
	super.refresh();
}

/**
 * Undoes the last command.
 */
public void run() {
	getCommandStack().undo();
}

}
