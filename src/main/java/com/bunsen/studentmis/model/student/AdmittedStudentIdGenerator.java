package com.bunsen.studentmis.model.student;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

import java.io.Serializable;

public class AdmittedStudentIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Query to retrieve the last saved studId from the database
        Query<String> query = session.createQuery("select max(a.studId) from AdmittedStudent a", String.class);
        String lastStudId = query.uniqueResult();

        if (lastStudId == null) {
            // No previous records found, start from "00001"
            return "00001";
        }

        // Remove leading zeros and parse to an integer
        int lastStudIdInt = Integer.parseInt(lastStudId);

        // Increment the lastStudIdInt
        lastStudIdInt++;

        // Ensure it doesn't exceed the maximum length of 5 digits
        if (lastStudIdInt > 99999) {
            throw new HibernateException("Maximum studId limit exceeded.");
        }

        // Format the new studId with leading zeros
        String newStudId = String.format("%05d", lastStudIdInt);

        return newStudId;
    }
}
