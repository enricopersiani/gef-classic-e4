package com.ibm.etools.draw2d.parts;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.ibm.etools.draw2d.*;
import com.ibm.etools.draw2d.geometry.Rectangle;

class ThumbnailUpdateManager 
extends SubordinateUpdateManager 
{
	
private IFigure host;
private Thumbnail overview;

/**
 * Handles the details of updating a {@link Thumbnail Thumbnail's}
 * image. 
 */
public ThumbnailUpdateManager(IFigure host, Thumbnail overview){
	super();
	setHost(host);
	setOverview(overview);
	getHost().setUpdateManager(this);
}

public void addDirtyRegion(IFigure figure, int x, int y, int w, int h){
	overview.setSourceDirty(true);
	overview.repaint();
	super.addDirtyRegion(figure, x,y,w,h);
}

protected IFigure getHost(){
	return host;
}

public void setHost(IFigure host){
	this.host = host;
}

public void setOverview(Thumbnail overview){
	this.overview = overview;
}

}