package com.netcracker.veromeev.archinc.dao.util;

import com.netcracker.veromeev.archinc.entity.AbstractEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * fills PreparedStatement(" ... %fildName% = ?, ...")
 * with concreate values. Used in AbstractDAO::execute
 */
public interface StatementFiller<Type extends AbstractEntity> {
    List<Type> fill(PreparedStatement statement) throws SQLException;
}
