package org.eclipse.draw2d;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import org.eclipse.swt.graphics.Font;

/**
 * Labeled Borders have a text message somewhere on them. The font for the text can be
 * set. Labeled borders should not change their Insets when the label changes, Therefore,
 * figures using this border should repaint() when updating the label, and revalidate()
 * when changing the Font.
 */
public interface LabeledBorder 
	extends Border
{

/**
 * Returns the label for this border.
 * @return The label for this border
 */
String getLabel();

/**
 * Sets the font for the label.
 * @param f The font to be set
 */
void setFont(Font f);

/**
 * Sets the text to be displayed as the label for this border.
 * @param l The text
 */
void setLabel(String l);

}