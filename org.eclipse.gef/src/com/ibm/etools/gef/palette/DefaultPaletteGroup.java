package com.ibm.etools.gef.palette;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.List;

public class DefaultPaletteGroup 
	extends DefaultPaletteContainer {

public DefaultPaletteGroup(String label){
	super(label, PaletteContainer.PALETTE_TYPE_GROUP);
}

public DefaultPaletteGroup(String label, List children){
	super(label, children, PaletteContainer.PALETTE_TYPE_GROUP);
}


}