package com.stefanini.internship.placemanagement.authorization;

import org.springframework.security.acls.domain.AclFormattingUtils;
import org.springframework.security.acls.model.Permission;

public class AclPermission implements Permission {

    private int mask;

    public AclPermission (int mask){
        this.mask = mask;
    }

    public AclPermission (String permission){
        permission = permission.toLowerCase();

        switch (permission){
            case "read":
                mask = 1;
                break;
            case "write":
                mask = 2;
                break;
            case "create":
                mask = 4;
                break;
            case "delete":
                mask = 8;
                break;
            case "administration":
                mask = 16;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + permission);
        }
    }

    /**
     * Returns the bits that represents the permission.
     *
     * @return the bits that represent the permission
     */
    @Override
    public int getMask() {
        return mask;
    }

    /**
     * Returns a 32-character long bit pattern <code>String</code> representing this
     * permission.
     * <p>
     * Implementations are free to format the pattern as they see fit, although under no
     * circumstances may {@link #RESERVED_OFF} or {@link #RESERVED_ON} be used within the
     * pattern. An exemption is in the case of {@link #RESERVED_OFF} which is used to
     * denote a bit that is off (clear). Implementations may also elect to use
     * {@link #RESERVED_ON} internally for computation purposes, although this method may
     * not return any <code>String</code> containing {@link #RESERVED_ON}.
     * <p>
     * The returned String must be 32 characters in length.
     * <p>
     * This method is only used for user interface and logging purposes. It is not used in
     * any permission calculations. Therefore, duplication of characters within the output
     * is permitted.
     *
     * @return a 32-character bit pattern
     */
    @Override
    public String getPattern() {
        return AclFormattingUtils.printBinary(mask, Permission.RESERVED_ON);
    }

    public final String toString() {
        return this.getClass().getSimpleName() + "[" + getPattern() + "=" + mask + "]";
    }
}
