package com.ibm.etools.gef.tools;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import com.ibm.etools.draw2d.geometry.*;

import com.ibm.etools.gef.*;
import com.ibm.etools.gef.commands.CompoundCommand;
import com.ibm.etools.gef.editparts.LayerManager;
import com.ibm.etools.gef.requests.ChangeBoundsRequest;

import com.ibm.etools.common.command.Command;
import com.ibm.etools.common.command.UnexecutableCommand;

public class DragEditPartsTracker
	extends SelectEditPartTracker
{

private static final int FLAG_SOURCE_FEEDBACK = SelectEditPartTracker.MAX_FLAG << 1;
protected static final int MAX_FLAG = FLAG_SOURCE_FEEDBACK;

private Request sourceRequest;
private List operationSet, exclusionSet;

public DragEditPartsTracker(EditPart sourceEditPart) {
	super(sourceEditPart);
	setDisabledCursor(SharedCursors.NO);
}

public void commitDrag(){
	eraseSourceFeedback();
	eraseTargetFeedback();
	performDrag();
	setState(STATE_TERMINAL);
}

protected List createOperationSet() {
	List list = ToolUtilities.getSelectionWithoutDependants(
		getCurrentViewer());
	ToolUtilities.filterEditPartsUnderstanding(list, getTargetRequest());
	return list;
}

protected Request createTargetRequest(){
	ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_MOVE);
	return request;
}

public void deactivate() {
	eraseSourceFeedback();
	super.deactivate();
	operationSet = null;
	exclusionSet = null;
}

protected void eraseSourceFeedback(){
	if (!getFlag(FLAG_SOURCE_FEEDBACK))
		return;
	setFlag(FLAG_SOURCE_FEEDBACK, false);
	List editParts = getOperationSet();
	for (int i = 0; i < editParts.size(); i++) {
		EditPart editPart = (EditPart) editParts.get(i);
		editPart.eraseSourceFeedback(getTargetRequest());
	}
}

protected Command getCommand(){
	CompoundCommand command = new CompoundCommand();
	command.setDebugLabel("Drag Object Tracker");//$NON-NLS-1$

	Iterator iter = getOperationSet().iterator();

	Request  request = getTargetRequest();
	request.setType(isMove() ? REQ_MOVE : REQ_ORPHAN);

	while (iter.hasNext()){
		EditPart editPart = (EditPart)iter.next();
		command.add(editPart.getCommand(request));
	}

	//If reparenting, add all editparts to target editpart.
	if (!isMove()){
		request.setType(REQ_ADD);
		if( getTargetEditPart() == null )
			command.add( UnexecutableCommand.INSTANCE );
		else
			command.add(getTargetEditPart().getCommand(getTargetRequest()));
	}
	return command;
}

protected String getCommandName(){
	if (isMove())
		return REQ_MOVE;
	return REQ_ADD;
}

protected String getDebugName(){
	return "DragEditPartsTracker:" + getCommandName();//$NON-NLS-1$
}

protected Collection getExclusionSet() {
	if (exclusionSet == null) {
		List set = getOperationSet();
		exclusionSet = new ArrayList(set.size()+1);
		for (int i=0; i<set.size(); i++) {
			GraphicalEditPart editpart = (GraphicalEditPart)set.get(i);
			exclusionSet.add(editpart.getFigure());
		}
		LayerManager layerManager = (LayerManager)getCurrentViewer().
			getEditPartRegistry().get(LayerManager.ID);
		exclusionSet.add(layerManager.getLayer(LayerConstants.CONNECTION_LAYER));
	}
	return exclusionSet;
}

protected boolean handleButtonUp(int button){
	if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)){
		eraseSourceFeedback();
		eraseTargetFeedback();
		performDrag();
		return true;
	}
	return super.handleButtonUp(button);
}

protected boolean handleDragInProgress(){
	if (isInDragInProgress()){
		updateTargetRequest();
		updateTargetUnderMouse();
		showTargetFeedback();
		showSourceFeedback();
		setCurrentCommand(getCommand());
	}
	return true;
}

protected boolean handleKeyDown(KeyEvent e){
	if (acceptArrowKey(e)) {
		accStepIncrement();
		if (stateTransition(STATE_INITIAL, STATE_ACCESSIBLE_DRAG_IN_PROGRESS))
			setStartLocation(getLocation());
		switch (e.keyCode) {
			case SWT.ARROW_DOWN :
				placeMouseInViewer(getLocation().getTranslated(0, accGetStep()));
				break;
			case SWT.ARROW_UP:
				placeMouseInViewer(getLocation().getTranslated(0, -accGetStep()));
				break;
			case SWT.ARROW_RIGHT:
				placeMouseInViewer(getLocation().getTranslated(accGetStep(), 0));
				break;
			case SWT.ARROW_LEFT:
				placeMouseInViewer(getLocation().getTranslated(-accGetStep(), 0));
				break;
		}
		return true;
	}
	return false;	
}

protected boolean handleKeyUp(KeyEvent e){
	if (acceptArrowKey(e)){
		accStepReset();
		return true;
	}
	return false;
}

protected boolean handleInvalidInput(){
	super.handleInvalidInput();
	eraseSourceFeedback();
	return true;
}

protected boolean isMove(){
	return getSourceEditPart().getParent() == getTargetEditPart();
}

protected void performDrag(){
	executeCurrentCommand();
}

protected void showSourceFeedback(){
	List editParts = getOperationSet();
	for (int i = 0; i < editParts.size(); i++) {
		EditPart editPart = (EditPart) editParts.get(i);
		editPart.showSourceFeedback(getTargetRequest());
	}
	setFlag(FLAG_SOURCE_FEEDBACK, true);
}

protected void updateTargetRequest(){
	ChangeBoundsRequest request = (ChangeBoundsRequest)getTargetRequest();
	request.setEditParts(getOperationSet());
	Dimension d = getDragMoveDelta();
	request.setMoveDelta(new Point(d.width,d.height));
	request.setLocation(getLocation());
	request.setType(getCommandName());
}

}
