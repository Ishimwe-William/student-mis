package com.bunsen.studentmis.model.student;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class AdmittedStudentIdGenerator implements IdentifierGenerator {
    private static long counter = 0;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long incrementedValue = ++counter;
        String customId = generateStudId(incrementedValue);
        return customId;
    }
    private String generateStudId(long id) {
        String formattedStudId = String.format("%05d", id);

        if (formattedStudId.length() > 5) {
            return formattedStudId.substring(formattedStudId.length() - 5);
        } else {
            return formattedStudId;
        }
    }
}