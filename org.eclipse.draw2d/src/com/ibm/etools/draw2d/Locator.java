package com.ibm.etools.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

/**
 * Controls the location of an IFigure.
 */
public interface Locator {

/**
 * Relocates the given IFigure.
 */
void relocate(IFigure target);

}