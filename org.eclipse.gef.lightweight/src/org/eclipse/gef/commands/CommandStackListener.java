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
package org.eclipse.gef.commands;

import java.util.EventObject;

/**
 * A CommandStackListener is notified whenever the {@link CommandStack}'s state
 * has changed.
 *
 * @deprecated Use {@link CommandStackEventListener} instead and filter for
 *             post-events using {@link CommandStack#POST_MASK}.
 */
public interface CommandStackListener {

	/**
	 * Called when the {@link CommandStack}'s state has changed.
	 *
	 * @param event the event
	 */
	void commandStackChanged(EventObject event);

}
