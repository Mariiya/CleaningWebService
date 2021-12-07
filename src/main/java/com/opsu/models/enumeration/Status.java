package com.opsu.models.enumeration;
/**
 * shows status of the order
 * @author group 183
 * @version 2.1
 */
public enum Status {
    /**no one is working on this order */
    STATUS_OPEN,
    /**someone is working on this order */
    STATUS_IN_PROGRES,
    /**order has been finished bu someone */
    STATUS_COMPLETED,
    /**order has been canceled by consumer */
    STATUS_CANCELED,
    /**order's deadline has been moved */
    STATUS_SUSPENDED,
    /**vendor declined order  */
    STATUS_REJECTED,
    /**order is empty */
    EMPTY
}
