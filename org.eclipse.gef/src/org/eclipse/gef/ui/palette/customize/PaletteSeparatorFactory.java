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
package org.eclipse.gef.ui.palette.customize;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.ui.palette.PaletteMessages;

/**
 * Factory to create {@link org.eclipse.gef.palette.PaletteSeparator
 * PaletteSeparators}.
 *
 * @author Pratik Shah
 */
public class PaletteSeparatorFactory extends PaletteEntryFactory {

	/**
	 * Constructor
	 */
	public PaletteSeparatorFactory() {
		setLabel(PaletteMessages.MODEL_TYPE_SEPARATOR);
	}

	/**
	 * @see org.eclipse.gef.ui.palette.customize.PaletteEntryFactory#createNewEntry(Shell)
	 */
	@Override
	public PaletteEntry createNewEntry(Shell shell) {
		PaletteSeparator separator = new PaletteSeparator();
		return separator;
	}

	/**
	 * @see org.eclipse.gef.ui.palette.customize.PaletteEntryFactory#determineTypeForNewEntry(org.eclipse.gef.palette.PaletteEntry)
	 */
	@Override
	protected Object determineTypeForNewEntry(PaletteEntry selected) {
		return PaletteSeparator.PALETTE_TYPE_SEPARATOR;
	}

}
