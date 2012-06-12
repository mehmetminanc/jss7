/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.cap.api.errors;

/**
 * The factory of CAP ReturnError messages
 * 
 * @author sergey vetyutnev
 * 
 */
public interface CAPErrorMessageFactory {

	/**
	 * Generate the empty message depends of the error code (for incoming
	 * messages)
	 * 
	 * @param errorCode
	 * @return
	 */
	public CAPErrorMessage createMessageFromErrorCode(Long errorCode);

	public CAPErrorMessageParameterless createCAPErrorMessageParameterless(Long errorCode);

	public CAPErrorMessageCancelFailed createCAPErrorMessageCancelFailed(CancelProblem cancelProblem);

	public CAPErrorMessageRequestedInfoError createCAPErrorMessageRequestedInfoError(CAPErrorMessageRequestedInfoError capErrorMessageRequestedInfoError);

	public CAPErrorMessageSystemFailure createCAPErrorMessageSystemFailure(CAPErrorMessageSystemFailure capErrorMessageSystemFailure);

	public CAPErrorMessageTaskRefused createCAPErrorMessageTaskRefused(CAPErrorMessageTaskRefused capErrorMessageTaskRefused);

}
