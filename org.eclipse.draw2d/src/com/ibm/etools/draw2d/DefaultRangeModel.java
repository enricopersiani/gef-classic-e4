package com.ibm.etools.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.ibm.etools.draw2d.*;
import java.beans.*;
import java.util.*;

/**
 * Generic implementation for a RangeModel.
 *
 *
 *<pre>
 *                    |<----extent--->|                     
 *    ----|-----------|---------------|---------------|----
 *       min          |                              max
 *                  value
 * </pre>
 */
public class DefaultRangeModel 
	implements RangeModel {

protected PropertyChangeSupport propertyListeners = new PropertyChangeSupport(this);
private int minimum = 0;
private int maximum = 100;
private int extent =  20;
private int value = 0;

/**
 * Registers the given listener as a PropertyChangeListener.
 * 
 * @since 2.0
 */
public void addPropertyChangeListener(PropertyChangeListener listener){
	propertyListeners.addPropertyChangeListener(listener);
}

/**
 * Notifies any listening PropertyChangeListeners that the property 
 * with the given id has changed.
 * 
 * @since 2.0
 */
protected void firePropertyChange(String string, int oldValue, int newValue){
	propertyListeners.firePropertyChange(string,oldValue,newValue);
}

public int getExtent(){
	return extent;
}

public int getMaximum(){
	return maximum;
}

public int getMinimum(){
	return minimum;
}

public int getValue(){
	return value;
}

public boolean isEnabled(){
	return (getMaximum() - getMinimum()) > getExtent();
}

public void removePropertyChangeListener(PropertyChangeListener listener){
	propertyListeners.removePropertyChangeListener(listener);
}

/*
 * Sets this RangeModel's extent and fires a property change.
 */
public void setExtent(int extent){
	if(this.extent == extent)
		return;
	int oldValue = this.extent;
	this.extent = extent;
	firePropertyChange(PROPERTY_EXTENT,oldValue,extent);
	setValue(getValue());
}

/*
 * Sets this RangeModel's maximum value and fires a property change.
 */
public void setMaximum(int maximum){
	if(this.maximum == maximum)
		return;
	int oldValue = this.maximum;
	this.maximum = maximum;
	firePropertyChange(PROPERTY_MAXIMUM,oldValue,maximum);
	setValue(getValue());
}

/*
 * Sets this RangeModel's minimum value and fires a property change.
 */
public void setMinimum(int minimum){
	if(this.minimum == minimum)
		return;
	int oldValue = this.minimum;
	this.minimum = minimum;
	firePropertyChange(PROPERTY_MINIMUM,oldValue,minimum);
	setValue(getValue());
}

/*
 * Sets this RangeModel's current value and fires a property change.
 */
public void setValue(int value){
	value = Math.max(getMinimum(), Math.min(getMaximum() - getExtent(), value));
	if(this.value==value)
		return;
	int oldValue = this.value;
	this.value = value;
	firePropertyChange(PROPERTY_VALUE,oldValue,value);
}

}