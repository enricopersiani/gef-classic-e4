package com.ibm.etools.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.ibm.etools.draw2d.geometry.Point;

/**
 * An IFigure that can be rotated.
 */
public interface RotatableDecoration
	extends IFigure
{

/**
 * Sets the location.
 */
public void setLocation(Point p);

/**
 * Sets the reference point.
 */
public void setReferencePoint(Point p);

}