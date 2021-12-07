package com.opsu.controllers;

import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.Consumer;
import com.opsu.secutity.services.UserDetailsImpl;
import com.opsu.services.ConsumerService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigInteger;

/**
 * The controller provides end points for processing requests from the frontend for the object Consumer
 * @author group 183
 * @version 2.1
 */

@RestController
@Validated
@RequestMapping(value = "/consumer/")
public class ConsumerController {
    private static final Logger log = Logger.getLogger(ConsumerController.class.getName());
    private final ConsumerService consumerService;
    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /** search consumer by id
     * @param id consumer */
    @GetMapping("{id}")
    public Consumer getConsumerById(@PathVariable BigInteger id) throws NotFoundException {
        try {
            return consumerService.getConsumerById (id);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new NotFoundException(e.getMessage(),e);
        }
    }
    /**updating already existing consumer
     * @param consumer consumer
     * @param updater user details*/
    @PostMapping("/update")
    public Consumer update(@AuthenticationPrincipal UserDetailsImpl updater, @Valid @RequestBody Consumer consumer) throws NotFoundException, EmptyDataBaseException {
      return   consumerService.update(updater,consumer);
    }
}
