package mware_lib.protocol;

import mware_lib.protocol.exceptions.InvalidMessageException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionValueTest {

    @Test(expected = InvalidMessageException.class)
    public void testEmptyMessage() throws Exception {
        Protocol.exceptionValueFromMessage("", Exception.class);
    }

    @Test
    public void testExceptionMessage() throws Exception {
        ExceptionValue exceptionValue =
                Protocol.exceptionValueFromMessage("exception|java.lang.Exception|hallo", Exception.class);
        assertEquals("hallo", exceptionValue.getValue().getMessage());
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_WrongKeyword() throws Exception {
        Protocol.exceptionValueFromMessage("retur|java.lang.Exception|asdf", Exception.class);
    }

    @Test(expected = InvalidMessageException.class)
    public void testInvalidFormat_HasNoDelimiter() throws Exception {
        Protocol.exceptionValueFromMessage("asdf", Exception.class);
    }

    @Test
    public void testExceptionMessageIsEmptyString() throws Exception {
        String message = "exception|java.lang.Exception|";
        ExceptionValue result =
                Protocol.exceptionValueFromMessage(message, Exception.class);
        assertEquals("", result.getValue().getMessage());
        assertEquals(message, result.asString());
    }

    @Test
    public void testExceptionMessageIsNull() throws Exception {
        ExceptionValue stringExceptionValue =
                Protocol.exceptionValueFromMessage("exception|java.lang.Exception|null", Exception.class);
        assertEquals("null", stringExceptionValue.getValue().getMessage());
    }

    @Test
    public void testString() throws Exception {
        Exception e = new Exception("hallo");
        ExceptionValue value = Protocol.exceptionValue(e, Exception.class);
        assertEquals("exception|java.lang.Exception|hallo", value.asString());
        assertEquals("hallo", value.getValue().getMessage());
    }

    @Test
    public void testEmptyString() throws Exception {
        Exception e = new Exception("");
        ExceptionValue value = Protocol.exceptionValue(e, Exception.class);
        assertEquals("exception|java.lang.Exception|", value.asString());
        assertEquals("", value.getValue().getMessage());
    }

    @Test
    public void testNull() throws Exception {
        Exception e = new Exception((String)null);
        ExceptionValue value = Protocol.exceptionValue(e, Exception.class);
        assertEquals("exception|java.lang.Exception|null", value.asString());
        assertEquals(null, value.getValue().getMessage());
    }
}