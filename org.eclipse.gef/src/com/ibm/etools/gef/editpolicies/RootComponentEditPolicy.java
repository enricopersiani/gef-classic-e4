package com.ibm.etools.gef.editpolicies;
/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import com.ibm.etools.common.command.*;
import com.ibm.etools.gef.requests.DeleteRequest;

/**
 * The root component is not deletable.  It represents the highest-level
 * object in the users model.
 */
public class RootComponentEditPolicy
	extends ComponentEditPolicy
{

protected Command createDeleteCommand(DeleteRequest request){
	return UnexecutableCommand.INSTANCE;
}

}