package com.ibm.etools.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

/**
 * A listener interface for receiving changes in the 
 * ancestor hierarchy of the listening IFigure.
 */
public interface AncestorListener {

/**
 * Called when an ancestor has been added into the listening
 * figure's hierarchy.
 */
public void ancestorAdded(IFigure ancestor);

/**
 * Called when an ancestor has moved to a new location.
 */
public void ancestorMoved(IFigure ancestor);

/**
 * Called when an ancestor has been removed from the listening
 * figure's hierarchy.
 */
public void ancestorRemoved(IFigure ancestor);

class Stub {
	public void ancestorMoved(IFigure ancestor){}
	public void ancestorAdded(IFigure ancestor){}
	public void ancestorRemoved(IFigure ancestor){}
}

}