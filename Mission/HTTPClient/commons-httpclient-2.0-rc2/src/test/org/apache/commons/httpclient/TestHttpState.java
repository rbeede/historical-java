/*
 * $Header: /home/cvs/jakarta-commons/httpclient/src/test/org/apache/commons/httpclient/TestHttpState.java,v 1.3 2003/01/23 22:48:27 jsdever Exp $
 * $Revision: 1.3 $
 * $Date: 2003/01/23 22:48:27 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */

package org.apache.commons.httpclient;

import junit.framework.*;

/**
 * 
 * Simple tests for {@link HttpState}.
 *
 * @author Rodney Waldhoff
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 * @author Sean C. Sullivan
 * 
 * @version $Id: TestHttpState.java,v 1.3 2003/01/23 22:48:27 jsdever Exp $
 * 
 */
public class TestHttpState extends TestCase {

    public final Credentials creds1 = new UsernamePasswordCredentials("user1", "pass1");
    public final Credentials creds2 = new UsernamePasswordCredentials("user2", "pass2");

    public final String realm1 = "realm1";
    public final String realm2 = "realm2";


    // ------------------------------------------------------------ Constructor
    public TestHttpState(String testName) {
        super(testName);
    }

    // ------------------------------------------------------------------- Main
    public static void main(String args[]) {
        String[] testCaseName = { TestHttpState.class.getName() };
        junit.textui.TestRunner.main(testCaseName);
    }

    // ------------------------------------------------------- TestCase Methods

    public static Test suite() {
        return new TestSuite(TestHttpState.class);
    }


    // ----------------------------------------------------------- Test Methods

    public void testHttpStateCredentials() {
        HttpState state = new HttpState();
	state.setCredentials(realm1, creds1);
	state.setCredentials(realm2, creds2);
        assertEquals(creds1, state.getCredentials(realm1));
        assertEquals(creds2, state.getCredentials(realm2));
    }

	public void testToString()
	{
        HttpState state = new HttpState();
        assertNotNull(state.toString());
        
        state.addCookie(new Cookie("foo", "bar", "yeah"));
        assertNotNull(state.toString());

        state.addCookie(new Cookie("flub", "duck", "yuck"));
        assertNotNull(state.toString());

		state.setCredentials(realm1, creds1);
        assertNotNull(state.toString());
        
		state.setProxyCredentials(realm2, creds2);
        assertNotNull(state.toString());
	}

    public void testHttpStateNoCredentials() {
        HttpState state = new HttpState();
        assertEquals(null, state.getCredentials("bogus"));
    }

    public void testHttpStateDefaultCredentials() {
        HttpState state = new HttpState();
	state.setCredentials(null, creds1);
	state.setCredentials(realm2, creds2);
        assertEquals(creds1, state.getCredentials("bogus"));
    }


    public void testHttpStateProxyCredentials() {
        HttpState state = new HttpState();
	state.setProxyCredentials(realm1, creds1);
	state.setProxyCredentials(realm2, creds2);
        assertEquals(creds1, state.getProxyCredentials(realm1));
        assertEquals(creds2, state.getProxyCredentials(realm2));
    }

    public void testHttpStateProxyNoCredentials() {
        HttpState state = new HttpState();
        assertEquals(null, state.getProxyCredentials("bogus"));
    }

    public void testHttpStateProxyDefaultCredentials() {
        HttpState state = new HttpState();
	state.setProxyCredentials(null, creds1);
	state.setProxyCredentials(realm2, creds2);
        assertEquals(creds1, state.getProxyCredentials("bogus"));
    }

}
