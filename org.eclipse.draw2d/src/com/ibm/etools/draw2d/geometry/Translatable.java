package com.ibm.etools.draw2d.geometry;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

/**
 * A translatable object can be translated (or moved) vertically
 * and/or horizontally.
 */
public interface Translatable {

/**
 * Translates this object horizontally by <code>dx</code> and 
 * vertically by <code>dy</code>.
 * 
 * @since 2.0
 */
public void performTranslate(int dx, int dy);

/**
 * Scales this object by the scale factor.
 * 
 * @since 2.0
 */
public void performScale(float factor);

}