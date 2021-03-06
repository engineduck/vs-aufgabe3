package bank_access;

import mware_lib.Proxy;

public abstract class ManagerImplBase extends Proxy{
    public static ManagerImplBase narrowCast(Object rawObjectRef) {
        if(rawObjectRef == null) return null;
        return new ManagerImplProxy((String) rawObjectRef);
    }

    public abstract String createAccount(String owner, String branch)
            throws InvalidParamException;
}
