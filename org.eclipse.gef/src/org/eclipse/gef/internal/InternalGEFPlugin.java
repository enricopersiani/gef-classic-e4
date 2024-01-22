/*******************************************************************************
 * Copyright (c) 2006, 2010 IBM Corporation and others.
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

package org.eclipse.gef.internal;

import org.eclipse.swt.widgets.Display;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.draw2d.BasicColorProvider;
import org.eclipse.draw2d.ColorProvider;

import org.eclipse.gef.GEFColorProvider;
import org.eclipse.gef.resources.GEFResources;
import org.eclipse.gef.resources.IImageDescriptorFactory;

import org.osgi.framework.BundleContext;

public class InternalGEFPlugin extends AbstractUIPlugin {

	private static BundleContext context;
	private static AbstractUIPlugin singleton;

	public InternalGEFPlugin() {
		singleton = this;
	}

	@Override
	public void start(BundleContext bc) throws Exception {
		super.start(bc);
		context = bc;
		// Overloads the basic color provider with customizable one
		if (ColorProvider.SystemColorFactory.getColorProvider() instanceof BasicColorProvider
			&& PlatformUI.isWorkbenchRunning() && !PlatformUI.getWorkbench().isClosing()) {
			ColorProvider.SystemColorFactory.setColorProvider(new GEFColorProvider());
		}

		// skip GEF resources initialization in headless mode
		if (Display.getCurrent() == null) {
			return;
		}

		GEFResources.getInstance().setImageRegistry(getImageRegistry());
		IImageDescriptorFactory factory = new IImageDescriptorFactory() {
			@Override
			public ImageDescriptor createFolder() {
				ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
				return sharedImages.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
			}

			@Override
			public ImageDescriptor createDeleteDisabled() {
				ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
				return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
			}

			@Override
			public ImageDescriptor createDelete() {
				ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
				return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED);
			}
		};
		GEFResources.getInstance().setImageDescriptorFactory(factory);
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
	}

	public static BundleContext getContext() {
		return context;
	}

	public static AbstractUIPlugin getDefault() {
		return singleton;
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		savePluginPreferences();
		super.stop(context);
	}

}
