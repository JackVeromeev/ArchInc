package by.bsuir.rudko.archinc.dao.util;

import by.bsuir.rudko.archinc.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * fills PreparedStatement(" ... %fieldName% = ?, ...")
 * with concrete values. Used in AbstractDAO.execute
 */
@FunctionalInterface
public interface StatementFiller<Type extends Entity> {
    void fill(PreparedStatement statement) throws SQLException;
}
