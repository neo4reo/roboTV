/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.xvdr.jniwrap;

public class SessionProxy extends Connection {
  private transient long swigCPtr;

  protected SessionProxy(long cPtr, boolean cMemoryOwn) {
    super(jniwrapJNI.SessionProxy_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SessionProxy obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        jniwrapJNI.delete_SessionProxy(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public SessionProxy() {
    this(jniwrapJNI.new_SessionProxy(), true);
  }

  public boolean open(String hostname, int port) {
    return jniwrapJNI.SessionProxy_open(swigCPtr, this, hostname, port);
  }

  public boolean close() {
    return jniwrapJNI.SessionProxy_close(swigCPtr, this);
  }

  public boolean terminate() {
    return jniwrapJNI.SessionProxy_terminate(swigCPtr, this);
  }

  public Packet transmitMessage(Packet message) {
    long cPtr = jniwrapJNI.SessionProxy_transmitMessage__SWIG_0(swigCPtr, this, Packet.getCPtr(message), message);
    return (cPtr == 0) ? null : new Packet(cPtr, true);
  }

  public boolean transmitMessage(Packet request, Packet response) {
    return jniwrapJNI.SessionProxy_transmitMessage__SWIG_1(swigCPtr, this, Packet.getCPtr(request), request, Packet.getCPtr(response), response);
  }

}
