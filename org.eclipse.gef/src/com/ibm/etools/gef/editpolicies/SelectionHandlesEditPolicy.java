package com.ibm.etools.gef.editpolicies;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.*;

import org.eclipse.core.runtime.IAdaptable;

import com.ibm.etools.draw2d.*;

import com.ibm.etools.gef.*;

public abstract class SelectionHandlesEditPolicy
	extends SelectionEditPolicy
	implements IAdaptable
{

protected List handles;

void addHandle(IFigure handle){
	getLayer(LayerConstants.HANDLE_LAYER).add(handle);
}

protected void addSelectionHandles(){
	removeSelectionHandles();
	IFigure layer = getLayer(LayerConstants.HANDLE_LAYER);
	handles = createSelectionHandles();
	for (int i=0; i < handles.size(); i++)
		layer.add((IFigure)handles.get(i));
}

abstract protected List createSelectionHandles();

public Object getAdapter(Class key){
	if (key == AccessibleHandleProvider.class)
		return new AccessibleHandleProvider() {
			public List getAccessibleHandleLocations() {
				List result = new ArrayList();
				for (int i=0; i<handles.size(); i++)
					result.add(((Handle)handles.get(i)).getAccessibleLocation());
				return result;
			}
		};
	return null;
}

protected void hideSelection(){
	removeSelectionHandles();
}

protected void removeSelectionHandles(){
	if (handles == null)
		return;
	IFigure layer = getLayer(LayerConstants.HANDLE_LAYER);
	for (int i=0; i < handles.size(); i++)
		layer.remove((IFigure)handles.get(i));
	handles = null;
}

protected void showSelection(){
	addSelectionHandles();
}

}