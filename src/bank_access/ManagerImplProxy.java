package bank_access;

import mware_lib.InvalidParamException;
import mware_lib.protocol.Protocol;
import mware_lib.protocol.ReturnValue;

/**
 * Created by janlepel on 02.12.14.
 */
public class ManagerImplProxy extends ManagerImplBase {
    private final String objectReference;

    public ManagerImplProxy(String rawObjRef) {
        this.objectReference = rawObjRef;
    }


    @Override
    public String createAccount(String owner, String branch) throws InvalidParamException {
     String message =  sendMessage(objectReference, "createAccount", owner, branch);
        return Protocol.returnValueFromMessage(message, String.class).getValue();
    }
}
