package com.ibm.etools.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.ibm.etools.draw2d.geometry.Point;

/**
 * A point used by a bendable Connection.
 */
public interface Bendpoint {

/**
 * Returns the location of the bendpoint.  This may return
 * the point by reference and modifying it could produce
 * unpredictable results.
 * 
 * @return the location of the bendpoint.
 */
public Point getLocation();

}