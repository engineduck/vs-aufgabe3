package mware_lib.protocol;

import com.sun.deploy.util.StringUtils;
import mware_lib.protocol.exceptions.InvalidMessageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MessageImpl implements Message {

    private String hostname;
    private final int port;
    private final Object object;
    private final int hashCode;
    private String methodCall;


    MessageImpl(String hostname, int port, Object object) {
        this.hostname = hostname;
        this.port = port;
        this.object = object;
        this.hashCode = Objects.hashCode(object);
    }

    MessageImpl(String message) throws InvalidMessageException {
        String[] split = message.split(Protocol.CALL_DELIMITER);

        if(split.length < 2)
            throw new InvalidMessageException("No Delimiter '" + Protocol.CALL_DELIMITER + "' found.");

        String objReference = split[0];
        methodCall = split[1];

        String[] objParts = objReference.split("\\|");
        hostname = objParts[0];
        port = Integer.valueOf(objParts[1]);
        object = objParts[2];
        hashCode = Integer.valueOf(objParts[3]);
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getObjectName() {
        return Objects.toString(object);
    }

    @Override
    public String getHashCode() {
        return Objects.toString(hashCode);
    }

    @Override
    public String getMethodCall() {
        return methodCall;
    }

    @Override
    public void setMethod(String methodName, Object... args) {
        List<String> strings = new ArrayList<>();
        strings.add(methodName);

        for(Object o: args) {
            strings.add(Objects.toString(o));
        }

        methodCall = StringUtils.join(strings, "|");
    }


}
