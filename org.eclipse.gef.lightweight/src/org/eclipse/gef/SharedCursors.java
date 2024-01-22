/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.gef;

import org.eclipse.swt.graphics.Cursor;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.draw2d.Cursors;

import org.eclipse.gef.internal.Internal;

/**
 * A shared collection of Cursors.
 *
 * @since 2.0
 */
public class SharedCursors extends Cursors {

	/**
	 * Cursor for valid connection
	 */
	public static final Cursor CURSOR_PLUG;
	/**
	 * Cursor for invalid connection
	 */
	public static final Cursor CURSOR_PLUG_NOT;
	/**
	 * Cursor for adding to a tree
	 */
	public static final Cursor CURSOR_TREE_ADD;
	/**
	 * Cursor for dragging in a tree
	 */
	public static final Cursor CURSOR_TREE_MOVE;

	private static int deviceZoom = -1;

	static {
		CURSOR_PLUG = createCursor("icons/plug-cursor.png"); //$NON-NLS-1$
		CURSOR_PLUG_NOT = createCursor("icons/plugnot-cursor.png"); //$NON-NLS-1$
		CURSOR_TREE_ADD = createCursor("icons/tree_add-cursor.png"); //$NON-NLS-1$
		CURSOR_TREE_MOVE = createCursor("icons/tree_move-cursor.png"); //$NON-NLS-1$
	}

	private static Cursor createCursor(String sourceName) {
		ImageDescriptor src = ImageDescriptor.createFromFile(Internal.class, sourceName);
		return new Cursor(null, src.getImageData(getDeviceZoom()), 0, 0);
	}

	private static int getDeviceZoom() {
		if (deviceZoom == -1) {
			deviceZoom = 100; // default value
			String deviceZoomProperty = System.getProperty("org.eclipse.swt.internal.deviceZoom"); //$NON-NLS-1$
			if (deviceZoomProperty != null) {
				try {
					deviceZoom = Integer.parseInt(deviceZoomProperty);
				} catch (NumberFormatException ex) {
					// if the property can not be parsed we keep the default 100% zoom level
				}
			}
		}
		return deviceZoom;
	}

}
