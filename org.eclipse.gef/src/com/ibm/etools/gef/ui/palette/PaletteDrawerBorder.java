package com.ibm.etools.gef.ui.palette;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import org.eclipse.swt.graphics.Color;

import org.eclipse.swt.custom.ViewForm;

import com.ibm.etools.draw2d.*;
import com.ibm.etools.draw2d.geometry.*;

/**
 * @deprecated Warning, the look and feel of this border is subject to change
 * without warning.  It is intended to be use with the palette's default
 * implementation or an implementation that preserves that look and feel.
 */
public final class PaletteDrawerBorder 
	extends AbstractBorder
{

private static final Insets insets = new Insets(1,1,3,3);

private static final Color
	dropshadow1 = ColorConstants.black,
	dropshadow2 = new Color(null, ViewForm.borderMiddleRGB),
      dropshadow3 = new Color(null, ViewForm.borderOutsideRGB);

/**
 * Returns the space used by the border for the 
 * figure provided as input. In this border all 
 * sides always have equal width.
 *
 * @param figure  Figure for which this is the border.
 * @return  Insets for this border.
 */
public Insets getInsets(IFigure figure){
	return insets;
}

public boolean isOpaque(){
	return true;
}

public void paint(IFigure figure, Graphics g, Insets insets){
	Rectangle r = getPaintRectangle(figure, insets);

	g.setLineWidth(1);

	r.width  -= 3;
	r.height -= 3;

	g.setForegroundColor(dropshadow1);
	g.drawRectangle(r);

	r.translate(1,1);
	g.setForegroundColor(dropshadow2);
	g.drawLine(r.x      , r.bottom(), r.right(), r.bottom());
	g.drawLine(r.right(), r.y       , r.right(), r.bottom());

	r.translate(1,1);
	g.setForegroundColor(dropshadow3);
	g.drawLine(r.x+1    , r.bottom(), r.right()-1, r.bottom());
	g.drawLine(r.right(), r.y+1     , r.right()  , r.bottom()-1);
}

}