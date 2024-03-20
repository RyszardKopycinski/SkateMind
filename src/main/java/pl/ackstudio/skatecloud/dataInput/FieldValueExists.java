package pl.ackstudio.skatecloud.dataInput;

public interface FieldValueExists {

    boolean fieldValueExists(Object value, String fieldName) throws
                                                             UnsupportedOperationException;
}
