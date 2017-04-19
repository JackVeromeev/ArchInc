package com.netcracker.veromeev.archinc.dao.util;

import com.netcracker.veromeev.archinc.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * fills PreparedStatement(" ... %fieldName% = ?, ...")
 * with concrete values. Used in AbstractDAO.execute
 */
public interface StatementFiller<Type extends Entity> {
    void fill(PreparedStatement statement) throws SQLException;
}
