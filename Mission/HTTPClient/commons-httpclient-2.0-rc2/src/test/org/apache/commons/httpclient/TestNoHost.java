/*
 * $Header: /home/cvs/jakarta-commons/httpclient/src/test/org/apache/commons/httpclient/TestNoHost.java,v 1.22 2003/04/17 11:34:19 olegk Exp $
 * $Revision: 1.22 $
 * $Date: 2003/04/17 11:34:19 $
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
 * Tests that don't require any external host.
 * I.e., that run entirely within this JVM.
 *
 * (True unit tests, by some definitions.)
 *
 * @author Rodney Waldhoff
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 * @version $Revision: 1.22 $ $Date: 2003/04/17 11:34:19 $
 */
public class TestNoHost extends TestCase {

    public TestNoHost(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(TestHttpStatus.suite());
        suite.addTest(TestBase64.suite());
        suite.addTest(TestCookie.suite());
        suite.addTest(TestNVP.suite());
        suite.addTest(TestHeader.suite());
        suite.addTest(TestHeaderElement.suite());
        suite.addTest(TestChallengeParser.suite());
        suite.addTest(TestAuthenticator.suite());
        suite.addTest(TestHttpUrlMethod.suite());
        suite.addTest(TestURI.suite());
        suite.addTest(TestURIUtil.suite());
        suite.addTest(TestURIUtil2.suite());
        suite.addTest(TestMethodsNoHost.suite());
        suite.addTest(TestMethodsRedirectNoHost.suite());
        suite.addTest(TestHttpState.suite());
        suite.addTest(TestResponseHeaders.suite());
        suite.addTest(TestRequestHeaders.suite());
        suite.addTest(TestStreams.suite());
        suite.addTest(TestStatusLine.suite());
        suite.addTest(TestRequestLine.suite());
        suite.addTest(TestPartsNoHost.suite());
        suite.addTest(TestMethodCharEncoding.suite());
        return suite;
    }

    public static void main(String args[]) {
        String[] testCaseName = { TestNoHost.class.getName() };
        junit.textui.TestRunner.main(testCaseName);
    }

}
