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

/**
 * 
 */
package org.mobicents.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.ss7.tcap.asn.comp.OperationCode;
import org.mobicents.protocols.ss7.tcap.asn.comp.OperationCodeType;

/**
 * @author baranowb
 * 
 */
public class OperationCodeImpl implements OperationCode {

	private Long localOperationCode;
	private long[] globalOperationCode;
	private OperationCodeType type;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.OperationCode#getOperationType()
	 */
	public OperationCodeType getOperationType() {

		return type;
	}


	public void setOperationType(OperationCodeType t) {
		this.type = t;

	}
	
	public void setOperationCodeType(OperationCodeType type) {
		this.type = type;
	}
	
	public void setLocalOperationCode(Long localOperationCode) {
		this.localOperationCode = localOperationCode;
		this.globalOperationCode = null;
		this.type = OperationCodeType.Local;
	}

	public void setGlobalOperationCode(long[] globalOperationCode) {
		this.localOperationCode = null;
		this.globalOperationCode = globalOperationCode;
		this.type = OperationCodeType.Global;
	}



	public Long getLocalOperationCode() {
		return this.localOperationCode;
	}
	
	public long[] getGlobalOperationCode() {
		return this.globalOperationCode;
	}
	

	
	public String toString() {
		if (this.localOperationCode != null)
			return "OperationCode[OperationType=Local, data=" + this.localOperationCode.toString() + "]";
		else if (this.globalOperationCode != null)
			return "OperationCode[OperationType=Global, data=" + Arrays.toString(this.globalOperationCode) + "]";
		else
			return "OperationCode[empty]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols
	 * .asn.AsnInputStream)
	 */
	public void decode(AsnInputStream ais) throws ParseException {

		try {
			if( this.type == OperationCodeType.Global ) {
				this.globalOperationCode = ais.readObjectIdentifier();
			} else if( this.type == OperationCodeType.Local ) {
				this.localOperationCode = ais.readInteger();
			} else
			{
				throw new ParseException();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ParseException("IOException while parsing OperationCode: " + e.getMessage(), e);
		} catch (AsnException e) {
			e.printStackTrace();
			throw new ParseException("AsnException while parsing OperationCode: " + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mobicents.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols
	 * .asn.AsnOutputStream)
	 */
	public void encode(AsnOutputStream aos) throws ParseException {
		
		if (this.localOperationCode == null && this.globalOperationCode == null)
			throw new ParseException("Operation code: No Operation code set!");
		
		try {
			if( this.type == OperationCodeType.Local ) {
				aos.writeInteger(this.localOperationCode);
			} else if( this.type == OperationCodeType.Global ) {
				aos.writeObjectIdentifier(this.globalOperationCode);
			} else
			{
				throw new ParseException();
			}

		} catch (IOException e) {
			throw new ParseException(e);
		} catch (AsnException e) {
			throw new ParseException(e);
		}
	}

}
