package com.ibm.etools.gef.requests;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.ibm.etools.draw2d.geometry.*;

/**
 * A Request to change the bounds the EditPart(s).
 */
public class ChangeBoundsRequest
	extends GroupRequest
	implements DropRequest
{

private Point moveDelta = new Point();
private Dimension resizeDelta = new Dimension();
private int resizeDirection;
private Point mouseLocation;

/**
 * Default constructor.
 */
public ChangeBoundsRequest(){}

/**
 * Creates a ChangeBoundsRequest with the given type.
 *
 * @param type The type of Request.
 */
public ChangeBoundsRequest(Object type){
	setType(type);
}

/**
 * Returns the location of the mouse pointer.
 *
 * @return The location of the mouse pointer.
 */
public Point getLocation(){
	return mouseLocation;
}

/**@deprecated*/
public Point getMouseLocation(){
	return getLocation();
}

/**
 * Returns a Point representing the distance the EditPart has moved.
 *
 * @return A Point representing the distance the EditPart has moved.
 */
public Point getMoveDelta(){
	return moveDelta;
}

/**
 * Returns the direction the figure is being resized.  Possible values
 * can be found in PositionConstants.
 */
public int getResizeDirection() {
	return resizeDirection;
}

/**
 * Returns a Dimension representing how much the EditPart has been resized.
 *
 * @return A Dimension representing how much the EditPart has been resized.
 */
public Dimension getSizeDelta(){
	return resizeDelta;
}

/**
 * Returns a Rectangle representing the new bounds.
 *
 * @return A Rectangle representing the new bounds.
 */
public Rectangle getTransformedRectangle(Rectangle rect){
	Rectangle changed = new Rectangle(rect);
	changed.translate(getMoveDelta());
	changed.width += resizeDelta.width;
	changed.height+= resizeDelta.height;
	return changed;
}

/**
 * Sets the move delta.
 *
 * @param p The Point representing the move delta.
 */
public void setMoveDelta(Point p){
	moveDelta = p;
}

/**
 * Sets the direction the figure is being resized.  Possible values
 * can be found in PositionConstants.
 */
public void setResizeDirection(int dir) {
	resizeDirection = dir;
}

/** @deprecated */
public void setMouseLocation(Point p){
	setLocation(p);
}

/**
 * Sets the location of the mouse pointer.
 *
 * @param p The location of the mouse pointer.
 */
public void setLocation(Point p){
	mouseLocation = p;
}

/**
 * Sets the size delta.
 *
 * @param d The Dimension representing the size delta.
 */
public void setSizeDelta(Dimension d){
	resizeDelta = d;
}

}