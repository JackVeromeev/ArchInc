package com.netcracker.veromeev.archinc.service;

import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.transaction.ReadingTransactionHandler;
import com.netcracker.veromeev.archinc.transaction.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jack on 20/04/17.
 *
 * @author Jack Veromeyev
 */
public class AdminService {

    private static Logger logger = LoggerFactory.getLogger(AdminService.class);

    private static AdminService instance = null;

    private AdminService() {

    }

    public static synchronized AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }


}
