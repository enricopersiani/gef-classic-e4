package com.ibm.etools.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.List;

import org.eclipse.swt.widgets.Control;

/**
 * This class is a helper to the {@link SWTEventDispatcher}. 
 * It handles the task of determining which Figure will gain 
 * focus upon a tab/shift-tab. It also keeps track of the 
 * Figure with current focus.
 * 
 * Note: When a Canvas with a {@link LightweightSystem} gains
 * focus, it gives focus to the child Figure who had focus when this 
 * Canvas lost focus. If the canvas is gaining focus for the first
 * time, focus is given to its first child Figure.
 * 
 */
public class FocusTraverseManager {

IFigure currentFocusOwner;

public FocusTraverseManager(){
}

private IFigure findDeepestRightmostChildOf(IFigure fig){
	while(fig.getChildren().size() != 0){
		fig = (IFigure)fig.getChildren().get(fig.getChildren().size()-1);
	}	
	return fig;
}

/**
 * Returns the IFigure that will receive focus upon
 * a 'tab' traverse event.
 * 
 * @param root The {@link LightweightSystem LightweightSystem's}
 *              root figure
 * @param focusOwner The IFigure who currently owns focus 
 */
public IFigure getNextFocusableFigure(IFigure root, IFigure focusOwner){
	boolean found = false;
	IFigure nextFocusOwner = focusOwner;
	
	/* If no Figure currently has focus,
	 * apply focus to root figure's first focusable child
	 */
	if(focusOwner == null){
		if(root.getChildren().size() != 0){
			nextFocusOwner = ((IFigure)root.getChildren().get(0));
			if(isFocusEligible(nextFocusOwner))
				return nextFocusOwner;
		}				
		else
			return null;
	}
	while(!found){
		IFigure parent = nextFocusOwner.getParent();
		
		/*
		 * Figure traversal is implemented using the pre-order left to right
		 * tree traversal algorithm.
		 *  
		 * If the focused sibling has children, traverse to its leftmost child.
		 * If the focused sibling has no children, traverse to the sibling
		 * to its right.
		 * If there is no sibling to the right, go up the tree until a node
		 * with untraversed siblings is found.
		 */
		List siblings = parent.getChildren();
		int siblingPos = siblings.indexOf(nextFocusOwner);		

		if(nextFocusOwner.getChildren().size() != 0){
			nextFocusOwner = ((IFigure)(nextFocusOwner.getChildren().get(0)));		
			if(isFocusEligible(nextFocusOwner))				
				found = true;	
		}
		else if(siblingPos < siblings.size()-1){
			nextFocusOwner = ((IFigure)(siblings.get(siblingPos+1)));
			if(isFocusEligible(nextFocusOwner))
				found = true;
		}
		else{
			boolean untraversedSiblingFound = false;			
			while(!untraversedSiblingFound){
				IFigure p = (IFigure)nextFocusOwner.getParent();	
				IFigure gp = (IFigure)p.getParent();
				
				if(gp != null){
					int parentSiblingCount = gp.getChildren().size();
					int parentIndex = gp.getChildren().indexOf(p);
					if(parentIndex < parentSiblingCount-1){
						nextFocusOwner = ((IFigure)p.getParent().getChildren().get(parentIndex+1));
						untraversedSiblingFound = true;
						if(isFocusEligible(nextFocusOwner))		
							found = true;
					}
					else
						nextFocusOwner = p;
				}
				else{
					nextFocusOwner = null;
					untraversedSiblingFound = true;
					found = true;
				}
			}
		}		
	}
	return nextFocusOwner;
}

/**
 * Returns the IFigure that will receive focus upon
 * a 'shift-tab' traverse event.
 * 
 * @param root The {@link LightweightSystem LightweightSystem's}
 *              root figure
 * @param focusOwner The IFigure who currently owns focus 
 */
public IFigure getPreviousFocusableFigure(IFigure root, IFigure focusOwner){
	if(focusOwner == null)
		return null;
	
	boolean found = false;
	IFigure nextFocusOwner = focusOwner;
 	while(!found){
 		IFigure parent = nextFocusOwner.getParent();
		
		/* 
		 * At root, return null to indicate traversal
		 * is complete.
		 */
		if(parent == null)
			return null;
		
		List siblings = parent.getChildren();
		int siblingPos = siblings.indexOf(nextFocusOwner);
		
		/*		
		 * Figure traversal is implemented using the post-order right to left 
		 * tree traversal algorithm.
		 * 
		 * Find the rightmost child.
		 * If this child is focusable, return it
		 * If not focusable, traverse to its sibling and repeat.
		 * If there is no sibling, traverse its parent.
		 */
		if(siblingPos != 0){
			IFigure child = findDeepestRightmostChildOf((IFigure)siblings.get(siblingPos-1));
			if(isFocusEligible(child)){
				found = true;
				nextFocusOwner = child;
			}
			else if(child.equals(nextFocusOwner)){
				if(isFocusEligible(nextFocusOwner))					
					found = true;
			}
			else
				nextFocusOwner = child;			
		}
		else{
			nextFocusOwner = parent;
				if(isFocusEligible(nextFocusOwner))
					found = true;
		}			
	}
	return nextFocusOwner;
}

public IFigure getCurrentFocusOwner(){
	return currentFocusOwner;
}	

private boolean isFocusEligible(IFigure fig){
	if(fig==null || !fig.isFocusTraversable() || !fig.isVisible())
		return false;
	// Check ancestors for visibility
	IFigure parent = fig.getParent();
	while(parent != null){
		if(!parent.isVisible())
			return false;
		parent = parent.getParent();
	}
	return true;
}

public void setCurrentFocusOwner(IFigure fig){
	currentFocusOwner = fig;
}

}