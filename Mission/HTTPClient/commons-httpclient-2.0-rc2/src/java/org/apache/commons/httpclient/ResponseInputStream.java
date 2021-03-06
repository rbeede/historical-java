/*
 * $Header: /home/cvs/jakarta-commons/httpclient/src/java/org/apache/commons/httpclient/ResponseInputStream.java,v 1.23 2003/02/12 13:21:27 olegk Exp $
 * $Revision: 1.23 $
 * $Date: 2003/02/12 13:21:27 $
 *
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
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
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

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * <p>{@link InputStream} wrapper supporting the chunked transfer encoding.</p>
 *
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @author Sean C. Sullivan
 * @author <a href="mailto:dion@apache.org">dIon Gillard</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @version $Revision: 1.23 $ $Date: 2003/02/12 13:21:27 $
 *
 * @deprecated Use new ChunkedInputStream(HttpConnecion#getResponseInputStream());
 *
 */
public class ResponseInputStream extends InputStream {

    // -------------------------------------------------------- Class Variables
    
    /** Log object for this class. */
    public static final Log LOG = LogFactory.getLog(ResponseInputStream.class);

    // ----------------------------------------------------------- Constructors

    /**
     *
     * @param stream Must be non-null.
     * @param chunked <code>true</code> if the input stream is chunked
     * @param contentLength content length
     *
     * @deprecated Use ChunkedInputStream;
     */
    public ResponseInputStream(InputStream stream, boolean chunked, int contentLength) {
        LOG.trace("enter ResponseInputStream(InputStream, boolean, int)");

        if (null == stream) {
            throw new NullPointerException("InputStream parameter is null");
        }
        closed = false;
        count = 0;
        this.chunk = chunked;
        this.contentLength = contentLength;
        this.stream = stream;
    }

    /**
     * Construct a servlet input stream associated with the specified Request.
     *
     * @param stream Must be non-null.
     * @param method Must be non-null.
     *
     * @deprecated Use ChunkedInputStream;
     *
     */
    public ResponseInputStream(InputStream stream, HttpMethod method) {
        super();
        LOG.trace("enter ResponseInputStream(InputStream, HttpMethod)");

        if (null == stream) {
            throw new NullPointerException("InputStream parameter is null");
        }

        if (null == method) {
            throw new NullPointerException("HttpMethod parameter is null");
        }

        closed = false;
        count = 0;

        // Retrieving transfer encoding header
        Header transferEncoding = method.getResponseHeader("transfer-encoding");
        if ((null != transferEncoding) && (transferEncoding.getValue().
                             toLowerCase().indexOf("chunked") != -1)) {
            chunk = true;
        }

        // Retrieving content length header
        Header contentLengthHeader = method.getResponseHeader("content-length");
        if (null != contentLengthHeader) {
            try {
                this.contentLength =
                    Integer.parseInt(contentLengthHeader.getValue());
            } catch (NumberFormatException ignored) {
                   // we are intentionally ignoring the NumberFormatException
            }
        }

        this.stream = stream;
    }


    // ----------------------------------------------------- Instance Variables

    /**
     * Has this stream been closed?
     */
    private boolean closed = false;

    /**
     * Use chunking ?
     */
    private boolean chunk = false;

    /**
     * True if the final chunk was found.
     */
    private boolean endChunk = false;

    /**
     * Chunk buffer.
     */
    private byte[] buffer = null;

    /**
     * Current chunk length.
     */
    private int length = 0;

    /**
     * Current chunk buffer position.
     */
    private int pos = 0;

    /**
     * The number of bytes which have already been returned by this stream.
     */
    private int count = 0;

    /**
     * The content length past which we will not read, or -1 if there is
     * no defined content length.
     */
    private int contentLength = -1;

    /**
     * The underlying input stream from which we should read data.
     */
    private InputStream stream = null;

    // --------------------------------------------------------- Public Methods

    /**
     * Close this input stream.  No physical level I-O is performed, but
     * any further attempt to read from this stream will throw an IOException.
     * If a content length has been set but not all of the bytes have yet been
     * consumed, the remaining bytes will be swallowed.
     * 
     * @throws IOException If an IO problem occurs.
     *
     * @deprecated Use ChunkedInputStream;
     */
    public void close() throws IOException {
        LOG.trace("enter ResponseInputStream.close()");
    /*
        // Xerces appears to doubly-close the input stream...
        if(closed) {
            throw new IOException("Stream is already closed");
        }
    */

        //TODO: This close code is not very robust
        if (!closed) {
            try {
                if (chunk) {
                    while (!endChunk) {
                        int b = read();
                        if (b < 0) {
                            break;
                        }
                    }
                } else {
                    if (length > 0) {
                        while (count < length) {
                            int b = read();
                            if (b < 0) {
                                break;
                            }
                        }
                    }
                }
            } catch (java.io.IOException ex) {
                throw ex;
            } finally {
                    closed = true;
            }
        }
    }


    /**
     * Read up to <code>len</code> bytes of data from the input stream
     * into an array of bytes.  An attempt is made to read as many as
     * <code>len</code> bytes, but a smaller number may be read,
     * possibly zero.  The number of bytes actually read is returned as
     * an integer.  This method blocks until input data is available,
     * end of file is detected, or an exception is thrown.
     *
     * @param b The buffer into which the data is read
     * @param off The start offset into array <code>b</code> at which
     *  the data is written
     * @param len The maximum number of bytes to read
     * @return The number of bytes that were read.
     *
     * @exception IOException if an input/output error occurs
     *
     * @deprecated Use ChunkedInputStream;
     */
    public int read(byte b[], int off, int len)
    throws IOException {
        LOG.trace("enter ResponseInputStream.read(byte, int, int)");

        int avail = length - pos;
        if ((avail == 0) && (!fillBuffer())) {
            return (-1);
        }

        avail = length - pos;
        if (avail == 0) {
            return (-1);
        }

        int toCopy = avail;

        if (toCopy < 0) {
          return (-1);
        }

        if (avail > len) {
            toCopy = len;
        }
        System.arraycopy(buffer, pos, b, off, toCopy);
        pos += toCopy;
        return toCopy;
    }

    /**
     * Read and return a single byte from this input stream, or -1 if end of
     * file has been encountered.
     *
     * @return The next byte in the stream or -1.
     * @exception IOException if an input/output error occurs
     *
     * @deprecated Use ChunkedInputStream;
     */
    public int read() throws IOException {
        LOG.trace("enter ResponseInputStream.read()");

        if (pos == length) {
            if (!fillBuffer()) {
                return (-1);
            }
        }

        return (buffer[pos++] & 0xff);

    }

    // -------------------------------------------------------- Private Methods


    /**
     * Fill the chunk buffer.
     * @return true If successful
     * @throws IOException If an IO problem occurs.
     *
     * @deprecated Use ChunkedInputStream;
     */
    private boolean fillBuffer() throws IOException {
        LOG.trace("enter ResponseInputStream.fillBuffer()");

        // Has this stream been closed?
        if (closed) {
            return false;
        }
            //throw new IOException("Stream is closed");

        if (endChunk) {
            return false;
        }

        // Have we read the specified content length already?
        if ((contentLength >= 0) && (count >= contentLength)) {
            return false;    // End of file indicator
        }

        pos = 0;

        if (chunk) {

            try {
                String numberValue = readLineFromStream();
                if (numberValue == null) {
                  throw new NumberFormatException("unable to find chunk length");
                }

                length = Integer.parseInt(numberValue.trim(), 16);
            } catch (NumberFormatException e) {
                // Critical error, unable to parse the chunk length
                length = -1;
                chunk = false;
                endChunk = true;
                closed = true;
                return false;
            }

            if (length == 0) {

                // Skipping trailing headers, if any
                String trailingLine = readLineFromStream();
                while (!trailingLine.equals("")) {
                    trailingLine = readLineFromStream();
                }
                endChunk = true;
                return false;

            } else {

                if ((buffer == null) || (length > buffer.length)) {
                    buffer = new byte[length];
                }

                // Now read the whole chunk into the buffer

                int nbRead = 0;
                int currentRead = 0;

                while (nbRead < length) {
                    try {
                        currentRead = stream.read(buffer, nbRead,
                                        length - nbRead);
                    } catch (Throwable t) {
                        LOG.debug("Exception thrown reading chunk from response", t);
                        throw new IOException();
                    }
                    if (currentRead < 0) {
                        throw new IOException("Not enough bytes read");
                    }
                    nbRead += currentRead;
                }

                // Skipping the CRLF
                readLineFromStream();

            }

        } else { //not using chunking

            try {
                if (buffer == null) {
                    buffer = new byte[4096];
                }
                length = stream.read(buffer);
                count += length;
            } catch (Throwable t) {
                LOG.debug("Exception thrown reading from response", t);
                throw new IOException(t.getMessage());
            }

        }

        return true;

    }

    /**
     * Reads the input stream, one line at a time. Reads bytes into an array,
     * until it reads a certain number of bytes or reaches a newline character,
     * which it reads into the array as well.
     *
     * @return The line that was read, or <code>null</code> if end-of-file
     *  was encountered
     * @exception IOException   if an input or output exception has occurred
     *
     * @deprecated Use ChunkedInputStream;
     */
    private String readLineFromStream()
    throws IOException {
        LOG.trace("enter ResponseInputStream.ReadLineFromStream()");

        StringBuffer sb = new StringBuffer();
        while (true) {
            int ch = stream.read();
            if (ch < 0) {
                if (sb.length() == 0) {
                    return (null);
                } else {
                    break;
                }
            } else if (ch == '\r') {
                continue;
            } else if (ch == '\n') {
                break;
            }
            sb.append((char) ch);
        }
        return (sb.toString());
    }
}
